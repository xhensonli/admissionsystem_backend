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

    List<Map<String, Integer>> getDistribute();

    List<Map<String, Integer>> getDistributeInProvince(@Param("province") String province);

    List<Map<String, Integer>> getGradeDistribute();

    List<Map<String, Integer>> getGradeDistributeByDepartment(@Param("departmentId") int departmentId);

    List<Map<String, Integer>> getGradeDistributeByMajor(@Param("majorId") String majorId);

    List<Map<String, Integer>> getCountDistributeInDepartment();

    List<Map<String, Integer>> getCountDistributeInMajor();

    List<Map<String, Integer>> getCountDistributeInMajorByDepartment(@Param("departmentId") int departmentId);

    void resetStudent();

    void resetTable();



}
