package org.enroll.service.impl;

import org.enroll.mapper.StatusMapper;
import org.enroll.pojo.Log;
import org.enroll.service.interfaces.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatusServiceImpl implements IStatusService {

    @Autowired
    private StatusMapper statusMapper;


    @Override
    public Integer getStatus() {
        return statusMapper.getStatus();
    }


    @Override
    public List<Log> getLogList(){
        return statusMapper.getLogList();
    }
}
