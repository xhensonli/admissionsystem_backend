package org.enroll.controller;

import org.enroll.service.interfaces.IMajorService;
import org.enroll.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    private IMajorService majorService;

    @RequestMapping("/getMajorPlan")
    public JsonResponse getMajorPlan(){
        return new JsonResponse(JsonResponse.OK, majorService.getMajorPlan(), null);
    }

    @RequestMapping("/updateMajorPlan")
    public JsonResponse updateMajorPlan(String majorId, int count){
        majorService.updateMajorPlan(majorId, count );
        return new JsonResponse(JsonResponse.OK, null, null);
    }

    @RequestMapping("/getMajors")
    public JsonResponse getMajors(){
        return new JsonResponse(JsonResponse.OK, majorService.getMajors(), null);
    }

    @RequestMapping("/getMajorsByDepartment")
    public JsonResponse getMajorsByDepartment(int departmentId){
        return new JsonResponse(JsonResponse.OK, majorService.getMajorsByDepartment(departmentId), null);
    }
}
