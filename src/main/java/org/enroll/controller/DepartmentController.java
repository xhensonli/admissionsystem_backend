package org.enroll.controller;

import org.enroll.service.interfaces.IDepartmentService;
import org.enroll.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;


    @RequestMapping("/getDepartments")
    public JsonResponse getDepartments(){
        return new JsonResponse(JsonResponse.OK, departmentService.getDepartments(), null);
    }
}
