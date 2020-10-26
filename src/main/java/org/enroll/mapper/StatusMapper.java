package org.enroll.mapper;

import org.apache.ibatis.annotations.Param;
import org.enroll.pojo.Log;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StatusMapper {

    void addLog(@Param("content") String content, @Param("status") int status);

    Integer getStatus();

    List<Log> getLogList();
}
