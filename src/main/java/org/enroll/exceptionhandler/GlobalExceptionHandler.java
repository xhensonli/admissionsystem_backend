package org.enroll.exceptionhandler;

import org.enroll.utils.JsonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResponse handle(Exception e){
        if(e instanceof  IllegalStateException){
            return new JsonResponse(JsonResponse.INVALID_REQUEST, null, e.toString());
        }
        return new JsonResponse(JsonResponse.SYSTEM_ERROR, null, e.toString());
    }
}
