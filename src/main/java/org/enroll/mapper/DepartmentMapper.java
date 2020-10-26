package org.enroll.mapper;

import org.apache.ibatis.annotations.Param;
import org.enroll.pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    void insertDepartment(Department department);

    List<Department> getDepartments();
}
