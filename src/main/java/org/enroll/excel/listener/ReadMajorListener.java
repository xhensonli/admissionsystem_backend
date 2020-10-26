package org.enroll.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.NoArgsConstructor;
import org.enroll.excel.pojo.ExcelMajor;
import org.enroll.mapper.DepartmentMapper;
import org.enroll.mapper.MajorMapper;
import org.enroll.pojo.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
public class ReadMajorListener extends AnalysisEventListener<ExcelMajor> {

    private static List<ExcelMajor> list = new ArrayList<>();

    private static final int BATCH_COUNT = 20;

    private MajorMapper majorMapper;

    private DepartmentMapper departmentMapper;

    private Map<String, Integer> departmentIds = new HashMap<>();

    public ReadMajorListener(MajorMapper mapper, DepartmentMapper departmentMapper){
        this.majorMapper = mapper;
        this.departmentMapper = departmentMapper;
        for (Department department : departmentMapper.getDepartments()) {
            departmentIds.put(department.getDepartmentName(),department.getDepartmentId());
        }
    }


    @Override
    public void invoke(ExcelMajor excelMajor, AnalysisContext analysisContext) {
        list.add(excelMajor);
        if (list.size() >= BATCH_COUNT){
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
        for (ExcelMajor excelMajor : list) {
            Integer id = departmentIds.get(excelMajor.getDepartmentName());
            if(id == null){
                Department newDept = new Department();
                newDept.setDepartmentName(excelMajor.getDepartmentName());
                departmentMapper.insertDepartment(newDept);
                departmentIds.put(newDept.getDepartmentName(),newDept.getDepartmentId());
                id = newDept.getDepartmentId();
            }
            excelMajor.setDepartmentId(id);
        }
        majorMapper.insertMajor(list);
    }
}
