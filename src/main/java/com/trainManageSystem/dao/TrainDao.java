package com.trainManageSystem.dao;

import com.trainManageSystem.model.Train;

public interface TrainDao {
    void deleteTrain(String train_no);
    Train[] selectAllTrain();
    int selectCount();
    Train[] selectBatchTrains(int start,int end);
}
