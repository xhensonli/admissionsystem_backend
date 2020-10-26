package org.enroll.mapper;

import org.apache.ibatis.annotations.Param;
import org.enroll.excel.pojo.ExcelMajor;
import org.enroll.pojo.Major;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorMapper {

    void insertMajor( @Param("majorList") List<ExcelMajor> list);

    List<ExcelMajor> getMajorPlan();

    List<ExcelMajor> getMajorPlanForEnroll();

    List<ExcelMajor> getMajorPlanForAdjust();

    void updateStudentCount(@Param("majors") List<ExcelMajor> majors);

    void updatePlanStudentCount(@Param("majorId") String majorId, @Param("count") int count);

    void resetMajor();

    void resetTable();

    List<Major> getMajors();

    List<Major> getMajorsByDepartment(@Param("departmentId") int departmentId);
}
