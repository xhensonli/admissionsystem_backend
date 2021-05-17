package org.enroll.pojo;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;


@Setter
@Getter
@Alias("statisticsResult")
@Data
public class StatisticsResult {

    private Integer topRank;

    private Integer bottomRank;

    private Integer maxGrade;

    private Integer minGrade;

    private Integer averageGrade;

    private String groupName;
}
