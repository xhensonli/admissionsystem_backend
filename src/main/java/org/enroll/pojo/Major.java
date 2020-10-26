package org.enroll.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("major")
public class Major {

    private String majorId;

    private String majorName;
}
