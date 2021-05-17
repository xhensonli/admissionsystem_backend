package org.enroll.exceptionhandler;

import com.alibaba.excel.exception.ExcelDataConvertException;
import org.enroll.exception.ReadExcelException;
import org.enroll.utils.JsonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({ReadExcelException.class})
    public JsonResponse handleExcelException(ReadExcelException e) {
        return new JsonResponse(JsonResponse.INVALID_REQUEST, null, "导入Excel失败，请检查文件格式");
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResponse handle(Exception e){
        if(e instanceof  IllegalStateException){
            return new JsonResponse(JsonResponse.INVALID_REQUEST, null, e.getMessage());
        }
        return new JsonResponse(JsonResponse.SYSTEM_ERROR, null, e.getMessage());
    }


}
