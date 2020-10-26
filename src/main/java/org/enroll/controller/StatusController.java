package org.enroll.controller;

import org.enroll.service.interfaces.IStatusService;
import org.enroll.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private IStatusService statusService;


    @RequestMapping("/getStatus")
    public JsonResponse getStatus(){
        return new JsonResponse(JsonResponse.OK, statusService.getStatus(), null);
    }


    @RequestMapping("/getLogList")
    public JsonResponse getLogList(){
        return new JsonResponse(JsonResponse.OK, statusService.getLogList(), null);
    }
}
