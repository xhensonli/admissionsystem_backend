package org.enroll.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("log")
public class Log {

    private String logContent;

    private Date logTime;
}
