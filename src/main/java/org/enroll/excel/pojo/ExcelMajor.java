package org.enroll.excel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadFontStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.apache.poi.ss.usermodel.FillPatternType;

@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExcelMajor {

    @ExcelProperty("专业代号")
    private String majorId;

    @ExcelProperty("专业代码")
    private String majorCode;

    private int departmentId;

    @ExcelProperty("学院")
    private String departmentName;

    @ExcelProperty("专业名称")
    private String majorName;

    @ExcelProperty("备注")
    private String comment;

    @ExcelProperty("学制年限")
    private int period;

    @ExcelProperty("招生计划数")
    private int planStudentCount;

    private int realisticStudentCount;
}
