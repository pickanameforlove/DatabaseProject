package com.trainManageSystem.service;

import com.trainManageSystem.model.train_seat;

public interface Train_seatService {
    train_seat[] selectTrain_seatByTrain(String train);
}
