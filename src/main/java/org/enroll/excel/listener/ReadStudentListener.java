package org.enroll.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.enroll.excel.pojo.ExcelStudent;
import org.enroll.mapper.StudentMapper;

import java.util.ArrayList;
import java.util.List;

public class ReadStudentListener extends AnalysisEventListener<ExcelStudent> {
    private static List<ExcelStudent> list = new ArrayList<>();
    private static final int BATCH_COUNT = 20;

    private StudentMapper studentMapper;

    public ReadStudentListener(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }


    @Override
    public void invoke(ExcelStudent excelStudent, AnalysisContext analysisContext) {
        list.add(excelStudent);
        if (list.size() >= BATCH_COUNT) {
            this.save();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        this.save();
        list.clear();
    }

    private void save(){
       this.studentMapper.insertStudent(list);
    }


}
