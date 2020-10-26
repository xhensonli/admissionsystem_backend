package org.enroll.service.impl;

import org.enroll.excel.pojo.ExcelMajor;
import org.enroll.mapper.MajorMapper;
import org.enroll.mapper.StatusMapper;
import org.enroll.pojo.EnrollStatus;
import org.enroll.pojo.Major;
import org.enroll.service.interfaces.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements IMajorService {


    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private StatusMapper statusMapper;


    @Override
    public void updateMajorPlan(String majorId, int count) {
        Integer status = statusMapper.getStatus();
        if (status == null || status != EnrollStatus.FILE_READY.ordinal() && status != EnrollStatus.WITHOUT_STUDENT.ordinal()){
            throw new RuntimeException("此时不能修改招生计划");
        }
        majorMapper.updatePlanStudentCount(majorId, count);
    }


    @Override
    public List<ExcelMajor> getMajorPlan(){
        return majorMapper.getMajorPlan();
    }

    @Override
    public List<Major> getMajors() {
        return majorMapper.getMajors();
    }

    @Override
    public List<Major> getMajorsByDepartment(int departmentId) {
        return majorMapper.getMajorsByDepartment(departmentId);
    }
}
