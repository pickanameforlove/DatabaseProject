package com.trainManageSystem.service.impl;



import com.trainManageSystem.dao.TicketDao;
import com.trainManageSystem.dao.TrainDao;
import com.trainManageSystem.dao.Train_timeDao;
import com.trainManageSystem.dao.templateDao;
import com.trainManageSystem.model.Train;
import com.trainManageSystem.service.TrainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("trainService")
public class TrainServiceImpl implements TrainService {
    @Resource
    private TrainDao trainDao;


    @Override
    public Train[] selectAllTrain() {
        return this.trainDao.selectAllTrain();
    }

    @Override
    public int selectCount() {
        return this.trainDao.selectCount();
    }

    @Override
    public Train[] selectBatchTrains(int start, int end) {
        return this.trainDao.selectBatchTrains(start,end);
    }

    @Override
    public void deleteTrain(String train_no) {
        this.trainDao.deleteTrain(train_no);
    }
}
