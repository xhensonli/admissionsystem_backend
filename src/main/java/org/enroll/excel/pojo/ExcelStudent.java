package org.enroll.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ExcelStudent {


    private int studentId;

    @ExcelProperty("准考证号")
    private String candidate;

    @ExcelProperty("姓名")
    private String studentName;

    @ExcelProperty("总分")
    private int totalGrade;

    @ExcelProperty("志愿1")
    private String will1;

    @ExcelProperty("志愿2")
    private String will2;

    @ExcelProperty("志愿3")
    private String will3;

    @ExcelProperty("志愿4")
    private String will4;

    @ExcelProperty("志愿5")
    private String will5;

    @ExcelProperty("志愿6")
    private String will6;

    @ExcelProperty("调剂")
    private int adjust;

    @ExcelProperty("排位")
    private int rank;

    @ExcelProperty("省份")
    private String province;

    @ExcelProperty("城市")
    private String city;

    @ExcelProperty("科类")
    private String subjectType;

    private String acceptedMajorId;

    private int acceptedType;
}
