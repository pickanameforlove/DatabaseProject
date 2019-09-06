package com.trainManageSystem.dao;

import com.trainManageSystem.model.train_seat;

public interface Train_seatDao {
    train_seat[] selectTrain_seatByTrain(String train);
}
