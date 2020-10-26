package org.enroll.service.interfaces;

import org.enroll.excel.pojo.ExcelMajor;
import org.enroll.pojo.Major;

import java.util.List;

public interface IMajorService {

    void updateMajorPlan(String majorId, int count);

    List<ExcelMajor> getMajorPlan();

    List<Major> getMajors();

    List<Major> getMajorsByDepartment(int departmentId);
}
