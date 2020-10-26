package org.enroll.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Data
@Getter
@Setter
@Alias("department")
public class Department {

    private int departmentId;

    private String departmentName;
}
