package org.enroll.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

public interface IExcelService {


    void ReadMajorExcel(MultipartFile file) throws IOException ;

    void ReadStudentExcel(MultipartFile file) throws IOException ;

    void doExport(OutputStream os);

    void exportExitStudent(OutputStream os);
}
