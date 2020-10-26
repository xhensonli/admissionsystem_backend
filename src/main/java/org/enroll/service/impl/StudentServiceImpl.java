package org.enroll.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.enroll.excel.pojo.ExcelMajor;
import org.enroll.excel.pojo.ExcelStudent;
import org.enroll.mapper.MajorMapper;
import org.enroll.mapper.StatusMapper;
import org.enroll.mapper.StudentMapper;
import org.enroll.pojo.EnrollStatus;
import org.enroll.pojo.StatisticsResult;
import org.enroll.pojo.StudentResult;
import org.enroll.service.interfaces.IStudentService;
import org.enroll.utils.QueryResultOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private StatusMapper statusMapper;


    @Override
    public PageInfo getStudentRaw(int currentPage) {
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.getStudentRaw());
    }

    @Override
    public PageInfo getAdjustStudentRaw(int currentPage){
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.getAdjustStudentRaw());
    }

    @Override
    public PageInfo getExitStudentRaw(int currentPage){
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.getExitStudentRaw());
    }

    @Override
    public void doEnroll() {
        Integer status = statusMapper.getStatus();
        if (status == null || status != EnrollStatus.FILE_READY.ordinal() && status != EnrollStatus.READY.ordinal()){
            throw new RuntimeException("这个状态不能录取");
        }
        List<ExcelMajor> majors = majorMapper.getMajorPlanForEnroll();
        Map<String,ExcelMajor> map = new HashMap<>();
        for (ExcelMajor major : majors) {
            map.put(major.getMajorId(),major);
        }
        int current = 0, size = 200;
        while(true){
            List<ExcelStudent> students = studentMapper.getStudentRawForEnroll(current, size);
            if (students.size() == 0)
                break;
            for (ExcelStudent student : students) {
                if(doEnroll(map.get(student.getWill1()))){
                    student.setAcceptedType(1);
                    student.setAcceptedMajorId(student.getWill1());
                } else if(doEnroll(map.get(student.getWill2()))){
                    student.setAcceptedType(2);
                    student.setAcceptedMajorId(student.getWill2());
                } else if(doEnroll(map.get(student.getWill3()))){
                    student.setAcceptedType(3);
                    student.setAcceptedMajorId(student.getWill3());
                }else if(doEnroll(map.get(student.getWill4()))){
                    student.setAcceptedType(4);
                    student.setAcceptedMajorId(student.getWill4());
                }else if(doEnroll(map.get(student.getWill5()))){
                    student.setAcceptedType(5);
                    student.setAcceptedMajorId(student.getWill5());
                }else if(doEnroll(map.get(student.getWill6()))){
                    student.setAcceptedType(6);
                    student.setAcceptedMajorId(student.getWill6());
                } else {
                    if(student.getAdjust() != 1)
                        student.setAcceptedType(-1);
                    else {
                        student.setAcceptedType(0);
                    }
                }
            }
            studentMapper.updateAccepted(students);
            current = current + size;
        }
        majorMapper.updateStudentCount(majors);
        if (status == EnrollStatus.FILE_READY.ordinal()){
            statusMapper.addLog("预录取完成", EnrollStatus.PRE_ENROLL.ordinal());
        } else {
            statusMapper.addLog("录取完成", EnrollStatus.ENROLLED.ordinal());
        }
    }

    private boolean doEnroll(ExcelMajor major){
        if (major != null && major.getPlanStudentCount() > major.getRealisticStudentCount()){
            major.setRealisticStudentCount(major.getRealisticStudentCount()+1);
            return true;
        }
        return false;
    }


    private void updateSingleStudent(List<ExcelStudent> students){
        for (ExcelStudent student : students) {
            studentMapper.updateSingleAccepted(student);
        }
    }


    @Override
    public void doAdjust(){
        Integer status = statusMapper.getStatus();
        if (status == null || status != EnrollStatus.PRE_ENROLL.ordinal() && status != EnrollStatus.ENROLLED.ordinal()){
            throw new RuntimeException("这个状态不能调剂");
        }
        List<ExcelMajor> majors = majorMapper.getMajorPlanForAdjust();
        int start = 0, size = 100, index = 0;
        while(true){
            List<ExcelStudent> students = studentMapper.getStudentRawForAdjust(start, size);
            if(students.size() == 0)
                break;
            for (int i = 0;i<students.size();) {
                ExcelStudent student = students.get(i);
                if(index < majors.size()){
                    ExcelMajor major = majors.get(index);
                    if (major.getRealisticStudentCount() < major.getPlanStudentCount()){
                        student.setAcceptedType(7);
                        student.setAcceptedMajorId(major.getMajorId());
                        major.setRealisticStudentCount(major.getRealisticStudentCount()+1);
                        i++;
                    } else {
                        index++;
                    }
                } else {
                    student.setAcceptedType(-1);
                    i++;
                }
            }
            studentMapper.updateAdjust(students);
            //不能改变start
        }
        majorMapper.updateStudentCount(majors);
        if (status == EnrollStatus.PRE_ENROLL.ordinal()){
            statusMapper.addLog("预调剂完成", EnrollStatus.PRE_ADJUST.ordinal());
        } else {
            statusMapper.addLog("调剂完成", EnrollStatus.ADJUSTED.ordinal());
        }
    }



    @Override
    public PageInfo getResult(int currentPage, boolean desc, QueryResultOption option) {
        PageHelper.startPage(currentPage,50);
        return new PageInfo<>(studentMapper.getStudent(desc, option));
    }

    @Override
    public PageInfo getResultByDepartment(int departmentId, int currentPage, boolean desc) {
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.getStudentByDepartment(departmentId, desc));
    }

    @Override
    public PageInfo getResultByMajor(String majorId, int currentPage, boolean desc) {
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.getStudentByMajor(majorId, desc));
    }

    @Override
    public PageInfo searchStudent(int currentPage, String keyword){
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.searchStudent(keyword));
    }

    @Override
    public PageInfo searchStudentByCandidate(int currentPage, String keyword){
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.searchStudentByCandidate(keyword));
    }

    @Override
    public PageInfo getStudentBeforeRank(int currentPage, int rank){
        PageHelper.startPage(currentPage, 50);
        return new PageInfo<>(studentMapper.getStudentBeforeRank(rank));
    }

    @Override
    public List<StatisticsResult> getStatisticsResult(){
        return studentMapper.getStatisticsResult();
    }

    @Override
    public List<StatisticsResult> getStatisticsResultInDepartment() {
        return studentMapper.getStatisticsResultInDepartment();
    }

    @Override
    public List<StatisticsResult> getStatisticsResultInMajor() {
        return studentMapper.getStatisticsResultInMajor();
    }

    @Override
    public List<Map<String, Integer>> getDistribute() {
        return studentMapper.getDistribute();
    }

    @Override
    public List<Map<String, Integer>> getDistributeInProvince(String province) {
        return studentMapper.getDistributeInProvince(province);
    }

    @Override
    public List<Map<String, Integer>> getGradeDistribute() {
        return studentMapper.getGradeDistribute();
    }

    @Override
    public List<Map<String, Integer>> getGradeDistributeByDepartment(int departmentId) {
        return studentMapper.getGradeDistributeByDepartment(departmentId);
    }

    @Override
    public List<Map<String, Integer>> getGradeDistributeByMajor(String majorId) {
        return studentMapper.getGradeDistributeByMajor(majorId);
    }

    @Override
    public List<Map<String, Integer>> getCountDistributeInDepartment() {
        return studentMapper.getCountDistributeInDepartment();
    }

    @Override
    public List<Map<String, Integer>> getCountDistributeInMajor() {
        return studentMapper.getCountDistributeInMajor();
    }

    @Override
    public List<Map<String, Integer>> getCountDistributeInMajorByDepartment(int departmentId) {
        return studentMapper.getCountDistributeInMajorByDepartment(departmentId);
    }

    @Override
    public void reset(){
        Integer status = statusMapper.getStatus();
        if (status == null || status != EnrollStatus.PRE_ENROLL.ordinal() && status != EnrollStatus.PRE_ADJUST.ordinal()){
            throw new RuntimeException("这个状态不能重置");
        }
        majorMapper.resetMajor();
        studentMapper.resetStudent();
        statusMapper.addLog("重置成功", EnrollStatus.FILE_READY.ordinal());
    }

    @Override
    public void formallyReady(){
        Integer status = statusMapper.getStatus();
        if (status == null || status != EnrollStatus.PRE_ADJUST.ordinal()){
            throw new RuntimeException("这个状态不能准备录取");
        }
        majorMapper.resetMajor();
        studentMapper.resetStudent();
        statusMapper.addLog("准备录取", EnrollStatus.READY.ordinal());
    }
}
