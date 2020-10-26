package org.enroll.pojo;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;


@Setter
@Getter
@Alias("statisticsResult")
public class StatisticsResult {

    private int topRank;

    private int bottomRank;

    private int maxGrade;

    private int minGrade;

    private int averageGrade;

    private String groupName;
}
