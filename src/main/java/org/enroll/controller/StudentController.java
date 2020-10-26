package org.enroll.controller;

import org.enroll.pojo.StatisticsResult;
import org.enroll.service.interfaces.IStudentService;
import org.enroll.utils.JsonResponse;
import org.enroll.utils.QueryResultOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    IStudentService studentService;

    @RequestMapping("/getStudentRaw")
    public JsonResponse getStudentRaw(@RequestParam(required = false, defaultValue = "1") Integer currentPage){
        if(currentPage == null || currentPage<=0)
            return new JsonResponse(JsonResponse.INVALID_REQUEST,null, null);
        return new JsonResponse(JsonResponse.OK, studentService.getStudentRaw(currentPage), null);
    }

    @RequestMapping("/getAdjustStudentRaw")
    public JsonResponse getAdjustStudentRaw(@RequestParam(required = false, defaultValue = "1") int currentPage){
        return new JsonResponse(JsonResponse.OK, studentService.getAdjustStudentRaw(currentPage), null);
    }

    @RequestMapping("/getExitStudentRaw")
    public JsonResponse getExitStudentRaw(@RequestParam(required = false, defaultValue = "1") int currentPage){
        return new JsonResponse(JsonResponse.OK, studentService.getExitStudentRaw(currentPage), null);
    }

    @RequestMapping("/doEnroll")
    public JsonResponse doEnroll(){
        studentService.doEnroll();
        return new JsonResponse(JsonResponse.OK, null, null);
    }

    @RequestMapping("/doAdjust")
    public JsonResponse doAdjust(){
        studentService.doAdjust();
        return new JsonResponse(JsonResponse.OK, null, null);
    }


//    StatisticsResult getResult(int currentPage, boolean desc);
    @RequestMapping("/getResult")
    public JsonResponse getResult(@RequestParam(required = false, defaultValue = "1") int currentPage,
                                  @RequestParam(required = false, defaultValue = "false") boolean desc,
                                  QueryResultOption option){
        return new JsonResponse(JsonResponse.OK, studentService.getResult(currentPage, desc, option), null);
    }
//    StatisticsResult getResultByDepartment( int departmentId, int currentPage, boolean desc);
    @RequestMapping("/getResultByDepartment")
    public JsonResponse getResultByDepartment(int departmentId, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false, defaultValue = "false") boolean desc){
        return new JsonResponse(JsonResponse.OK, studentService.getResultByDepartment(departmentId, currentPage, desc), null);
    }
//    StatisticsResult getResultByMajor( String majorId, int currentPage, boolean desc);
    @RequestMapping("/getResultByMajor")
    public JsonResponse getResultByMajor(String majorId, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false, defaultValue = "false") boolean desc){
        return new JsonResponse(JsonResponse.OK, studentService.getResultByMajor(majorId, currentPage, desc), null);
    }

    @RequestMapping("/searchStudent")
    public JsonResponse searchStudent(@RequestParam(required = false, defaultValue = "1") int currentPage,String keyword){
        return new JsonResponse(JsonResponse.OK, studentService.searchStudent(currentPage,keyword), null);
    }


    @RequestMapping("/searchStudentByCandidate")
    public JsonResponse searchStudentByCandidate(@RequestParam(required = false, defaultValue = "1") int currentPage,String keyword){
        return new JsonResponse(JsonResponse.OK, studentService.searchStudentByCandidate(currentPage,keyword), null);
    }

    @RequestMapping("/getStudentBeforeRank")
    public JsonResponse getStudentBeforeRank(@RequestParam(required = false, defaultValue = "1") int currentPage, int rank){
        return new JsonResponse(JsonResponse.OK, studentService.getStudentBeforeRank(currentPage, rank), null);
    }


    @RequestMapping("/getStatisticsResult")
    public JsonResponse getStatisticsResult(){
        return new JsonResponse(JsonResponse.OK, studentService.getStatisticsResult(), null);
    }
//    List<Map<String, Object>> getResultInDepartment(int departmentId);
    @RequestMapping("/getStatisticsResultInDepartment")
    public JsonResponse getStatisticsResultInDepartment(){
        return new JsonResponse(JsonResponse.OK, studentService.getStatisticsResultInDepartment(), null);
    }
//    List<Map<String, Object>> getResultInMajor(String majorId);
    @RequestMapping("/getStatisticsResultInMajor")
    public JsonResponse getStatisticsResultInMajor(){
        return new JsonResponse(JsonResponse.OK, studentService.getStatisticsResultInMajor(), null);
    }
    //    Map<String, Integer> getDistribute();
    @RequestMapping("/getDistribute")
    public JsonResponse getDistribute(){
        return new JsonResponse(JsonResponse.OK, studentService.getDistribute(), null);
    }
    //    Map<String, Integer> getDistributeInProvince(String province);
    @RequestMapping("/getDistributeInProvince")
    public JsonResponse getDistributeInProvince(String province){
        return new JsonResponse(JsonResponse.OK, studentService.getDistributeInProvince(province), null);
    }
    //    Map<String, Integer> getGradeDistribute();
    @RequestMapping("/getGradeDistribute")
    public JsonResponse getGradeDistribute(){
        return new JsonResponse(JsonResponse.OK, studentService.getGradeDistribute(), null);
    }
    //    Map<String, Integer> getGradeDistributeByDepartment( int departmentId);
    @RequestMapping("/getGradeDistributeByDepartment")
    public JsonResponse getGradeDistributeByDepartment(int departmentId){
        return new JsonResponse(JsonResponse.OK, studentService.getGradeDistributeByDepartment(departmentId), null);
    }
    //    Map<String, Integer> getGradeDistributeByMajor(String majorId);
    @RequestMapping("/getGradeDistributeByMajor")
    public JsonResponse getGradeDistributeByMajor(String majorId){
        return new JsonResponse(JsonResponse.OK, studentService.getGradeDistributeByMajor(majorId), null);
    }
    //    Map<String, Integer> getCountDistributeInDepartment();
    @RequestMapping("/getCountDistributeInDepartment")
    public JsonResponse getCountDistributeInDepartment(){
        return new JsonResponse(JsonResponse.OK, studentService.getCountDistributeInDepartment(), null);
    }
    //    Map<String, Integer> getCountDistributeInMajor();
    @RequestMapping("/getCountDistributeInMajor")
    public JsonResponse getCountDistributeInMajor(){
        return new JsonResponse(JsonResponse.OK, studentService.getCountDistributeInMajor(), null);
    }
    //    Map<String, Integer> getCountDistributeInMajorByDepartment(int departmentId);
    @RequestMapping("/getCountDistributeInMajorByDepartment")
    public JsonResponse getCountDistributeInMajorByDepartment(int departmentId){
        return new JsonResponse(JsonResponse.OK, studentService.getCountDistributeInMajorByDepartment(departmentId), null);
    }

    @RequestMapping("/reset")
    public JsonResponse reset(){
        studentService.reset();
        return new JsonResponse(JsonResponse.OK, null, null);
    }

    @RequestMapping("/formalReady")
    public JsonResponse formalReady(){
        studentService.formallyReady();
        return new JsonResponse(JsonResponse.OK, null, null);
    }
}
