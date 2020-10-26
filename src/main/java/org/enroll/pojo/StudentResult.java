package org.enroll.pojo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.apache.poi.ss.usermodel.FillPatternType;

@Alias("studentResult")
@Getter
@Setter
@HeadStyle(fillPatternType = FillPatternType.SOLID_FOREGROUND, fillForegroundColor = 9)
@HeadFontStyle(fontHeightInPoints = 10)
@ExcelIgnoreUnannotated
public class StudentResult {

    private int studentId;

    @ExcelProperty("考号")
    private String candidate;

    @ExcelProperty("姓名")
    private String studentName;

    @ExcelProperty("总分")
    private int totalGrade;

    @ExcelProperty("排名")
    private int rank;

    @ExcelProperty("省份")
    private String province;

    @ExcelProperty("城市")
    private String city;

    @ExcelProperty("专业")
    private String majorName;

    @ExcelProperty("学院")
    private String departmentName;

    private int acceptedType;
}
