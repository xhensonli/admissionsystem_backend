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

    List<Map<String, Integer>> getDistribute();

    List<Map<String, Integer>> getDistributeInProvince(String province);

    List<Map<String, Integer>> getGradeDistribute();

    List<Map<String, Integer>> getGradeDistributeByDepartment( int departmentId);

    List<Map<String, Integer>> getGradeDistributeByMajor(String majorId);

    List<Map<String, Integer>> getCountDistributeInDepartment();

    List<Map<String, Integer>> getCountDistributeInMajor();

    List<Map<String, Integer>> getCountDistributeInMajorByDepartment(int departmentId);

    void reset();

    void formallyReady();
}
