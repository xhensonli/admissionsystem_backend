package org.enroll.service.interfaces;

import org.enroll.pojo.Log;

import java.util.List;

public interface IStatusService {

    Integer getStatus();

    List<Log> getLogList();
}
