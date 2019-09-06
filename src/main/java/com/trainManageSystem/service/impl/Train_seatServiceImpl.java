package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.Train_seatDao;
import com.trainManageSystem.model.train_seat;
import com.trainManageSystem.service.Train_seatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("train_seatService")
public class Train_seatServiceImpl implements Train_seatService {
    @Resource
    private Train_seatDao dao;
    @Override
    public train_seat[] selectTrain_seatByTrain(String train) {
        return this.dao.selectTrain_seatByTrain(train);
    }
}
