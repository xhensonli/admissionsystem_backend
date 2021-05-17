package org.enroll.service.interfaces;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.enroll.excel.pojo.ExcelStudent;
import org.enroll.pojo.StatisticsResult;
import org.enroll.utils.QueryResultOption;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface IStudentService {

    PageInfo getStudentRaw(int currentPage);

    PageInfo getAdjustStudentRaw(int currentPage);

    PageInfo getExitStudentRaw(int currentPage);

    void doEnroll();

    void doAdjust();

    PageInfo getResult(int currentPage, boolean desc, QueryResultOption option);

    PageInfo getResultByDepartment( int departmentId, int currentPage, boolean desc);

    PageInfo getResultByMajor( String majorId, int currentPage, boolean desc);

    PageInfo searchStudent(int currentPage, String keyword);

    PageInfo searchStudentByCandidate(int currentPage, String keyword);

    PageInfo getStudentBeforeRank(int currentPage, int rank);

    List<StatisticsResult> getStatisticsResult();

    List<StatisticsResult> getStatisticsResultInDepartment();

    List<StatisticsResult> getStatisticsResultInMajor();

    List<Map<String, Object>> getDistribute();

    List<Map<String, Object>> getDistributeInProvince(String province);

    List<Map<String, Object>> getGradeDistribute();

    List<Map<String, Object>> getGradeDistributeByDepartment( int departmentId);

    List<Map<String, Object>> getGradeDistributeByMajor(String majorId);

    List<Map<String, Object>> getCountDistributeInDepartment();

    List<Map<String, Object>> getCountDistributeInMajor();

    List<Map<String, Object>> getCountDistributeInMajorByDepartment(int departmentId);

    void reset();

    void formallyReady();
}
