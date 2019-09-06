package com.trainManageSystem.service;

import com.trainManageSystem.model.Train;

public interface TrainService {
    Train[] selectAllTrain();
    int selectCount();
    Train[] selectBatchTrains(int start,int end);
    void deleteTrain(String train_no);
}
