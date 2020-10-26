package org.enroll.controller;

import org.enroll.service.interfaces.IExcelService;
import org.enroll.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    IExcelService excelService;


    @ResponseBody
    @RequestMapping("/uploadMajor")
    public JsonResponse uploadMajorExcel(MultipartFile file) throws IOException {
        excelService.ReadMajorExcel(file);
        return new JsonResponse(JsonResponse.OK,null,null);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @ResponseBody
    @RequestMapping("/uploadStudent")
    public JsonResponse uploadStudentExcel(MultipartFile file) throws IOException {
        excelService.ReadStudentExcel(file);
        return new JsonResponse(JsonResponse.OK,null,null);
    }

    @CrossOrigin(origins = "http://localhost:8000")
    @RequestMapping("/exportResult")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("录取结果", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        excelService.doExport(response.getOutputStream());
    }
}
