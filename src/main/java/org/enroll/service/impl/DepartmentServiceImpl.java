package org.enroll.service.impl;

import org.enroll.mapper.DepartmentMapper;
import org.enroll.pojo.Department;
import org.enroll.service.interfaces.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments();
    }
}
