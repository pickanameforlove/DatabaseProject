package com.trainManageSystem.service;

import com.trainManageSystem.model.train_time;

public interface Train_timeService {
    int selectOne(String train_no,String station_name);
    void deleteTT(String train_no);
    train_time[] selectAll();
}
