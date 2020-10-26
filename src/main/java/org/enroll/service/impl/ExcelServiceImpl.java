package org.enroll.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.enroll.excel.listener.ReadMajorListener;
import org.enroll.excel.listener.ReadStudentListener;
import org.enroll.excel.pojo.ExcelMajor;
import org.enroll.excel.pojo.ExcelStudent;
import org.enroll.mapper.DepartmentMapper;
import org.enroll.mapper.MajorMapper;
import org.enroll.mapper.StatusMapper;
import org.enroll.mapper.StudentMapper;
import org.enroll.pojo.EnrollStatus;
import org.enroll.pojo.StudentResult;
import org.enroll.service.interfaces.IExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
public class ExcelServiceImpl implements IExcelService {

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StatusMapper statusMapper;

    public void ReadMajorExcel(MultipartFile file) throws IOException {
        Integer status = statusMapper.getStatus();
        majorMapper.resetTable();
        studentMapper.resetStudent();
        if (status != null && (status == EnrollStatus.PRE_ENROLL.ordinal() || status >= EnrollStatus.READY.ordinal())){
            throw new RuntimeException("现在不能导入文件");
        }
        EasyExcel.read(file.getInputStream(), ExcelMajor.class, new ReadMajorListener(majorMapper, departmentMapper)).sheet().doRead();
        if (status == null){
            statusMapper.addLog("导入专业招生计划文件", EnrollStatus.WITHOUT_STUDENT.ordinal());
        } else if (status == EnrollStatus.WITHOUT_MAJOR.ordinal()){
            statusMapper.addLog("导入专业招生计划文件", EnrollStatus.FILE_READY.ordinal());
        } else if (status == EnrollStatus.WITHOUT_STUDENT.ordinal()){
            statusMapper.addLog("重新导入专业招生计划文件", EnrollStatus.WITHOUT_STUDENT.ordinal());
        } else {
            statusMapper.addLog("重新导入专业招生计划文件", EnrollStatus.FILE_READY.ordinal());
        }
    }

    public void ReadStudentExcel(MultipartFile file) throws IOException {
        Integer status = statusMapper.getStatus();
        if (status != null && (status == EnrollStatus.PRE_ENROLL.ordinal() || status >= EnrollStatus.READY.ordinal())){
            throw new RuntimeException("现在不能导入文件");
        }
        studentMapper.resetTable();
        majorMapper.resetMajor();
        EasyExcel.read(file.getInputStream(), ExcelStudent.class,new ReadStudentListener(studentMapper)).sheet().doRead();
        if (status == null){
            statusMapper.addLog("导入考生志愿文件", EnrollStatus.WITHOUT_MAJOR.ordinal());
        } else if (status == EnrollStatus.WITHOUT_STUDENT.ordinal()){
            statusMapper.addLog("导入考生志愿文件", EnrollStatus.FILE_READY.ordinal());
        } else if (status == EnrollStatus.WITHOUT_MAJOR.ordinal()){
            statusMapper.addLog("重新导入考生志愿文件", EnrollStatus.WITHOUT_MAJOR.ordinal());
        } else {
            statusMapper.addLog("重新导入考生志愿文件", EnrollStatus.FILE_READY.ordinal());
        }
    }

    @Override
    public void doExport(OutputStream os){
        int start = 0, size = 200;
        ExcelWriter excelWriter = null;
        Integer status = statusMapper.getStatus();
        if (status != EnrollStatus.ADJUSTED.ordinal()){
            throw new RuntimeException("未结束流程不能导出结果");
        }
        try {
            excelWriter = EasyExcel.write(os, StudentResult.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("录取结果").build();
            while(true) {
                List<StudentResult> results = studentMapper.getStudentForExport(start,size);
                if(results.size() == 0)
                    break;
                excelWriter.write(results, writeSheet);
                start += size;
            }
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
