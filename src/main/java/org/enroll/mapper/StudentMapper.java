package org.enroll.mapper;

import org.apache.ibatis.annotations.Param;
import org.enroll.excel.pojo.ExcelStudent;

import org.enroll.pojo.Major;
import org.enroll.pojo.StatisticsResult;
import org.enroll.pojo.StudentResult;
import org.enroll.utils.QueryResultOption;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentMapper {

    void insertStudent(@Param("studentList") List<ExcelStudent> students);

    List<ExcelStudent> getStudentRaw();

    List<ExcelStudent> getAdjustStudentRaw();

    List<ExcelStudent> getExitStudentRaw();

    List<ExcelStudent> getStudentRawForEnroll(@Param("start") int start, @Param("size") int size);

    List<ExcelStudent> getStudentRawForAdjust(@Param("start") int start, @Param("size") int size);

    void updateAccepted(@Param("students") List<ExcelStudent> students);

    void updateAccepted2(@Param("students") List<ExcelStudent> students);

    void updateSingleAccepted(ExcelStudent student);

    void updateAdjust(@Param("students") List<ExcelStudent> students);

    List<StudentResult> getStudent(@Param("desc") boolean desc, @Param("option") QueryResultOption option);

    List<StudentResult> getStudentBeforeRank(@Param("rank") int rank);

    List<StudentResult> getStudentForExport(@Param("start") int start, @Param("size") int size);

    List<ExcelStudent> getExitStudentForExport(@Param("start") int start, @Param("size") int size);

    List<StudentResult> getStudentByDepartment(@Param("depId") int departmentId, @Param("desc") boolean desc);

    List<StudentResult> getStudentByMajor(@Param("majorId") String majorId, @Param("desc") boolean desc);

    List<StudentResult> searchStudent(@Param("keyword") String keyword);

    List<StudentResult> searchStudentByCandidate(@Param("candidate") String candidate);
//
//    StatisticsResult getResult();
//
//    StatisticsResult getResultByDepartment(@Param("depId") int departmentId);
//
//    StatisticsResult getResultByMajor(@Param("majorId") String majorId);

    List<StatisticsResult> getStatisticsResult();

    List<StatisticsResult> getStatisticsResultInDepartment();

    List<StatisticsResult> getStatisticsResultInMajor();

    List<Map<String, Object>> getDistribute();

    List<Map<String, Object>> getDistributeInProvince(@Param("province") String province);

    List<Map<String, Object>> getGradeDistribute();

    List<Map<String, Object>> getGradeDistributeByDepartment(@Param("departmentId") int departmentId);

    List<Map<String, Object>> getGradeDistributeByMajor(@Param("majorId") String majorId);

    List<Map<String, Object>> getCountDistributeInDepartment();

    List<Map<String, Object>> getCountDistributeInMajor();

    List<Map<String, Object>> getCountDistributeInMajorByDepartment(@Param("departmentId") int departmentId);

    void resetStudent();

    void resetTable();



}
