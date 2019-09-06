package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.Train_timeDao;
import com.trainManageSystem.model.train_time;
import com.trainManageSystem.service.Train_timeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("train_timeService")
public class Train_timeServiceImpl implements Train_timeService {
    @Resource
    private Train_timeDao dao;
    @Override
    public int selectOne(String train_no, String station_name) {
        return this.dao.selectOne(train_no, station_name);
    }

    @Override
    public void deleteTT(String train_no) {
        this.dao.deleteTT(train_no);
    }

    @Override
    public train_time[] selectAll() {
        return this.dao.selectAll();
    }
}
