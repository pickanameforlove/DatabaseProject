package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.PassengerDao;
import com.trainManageSystem.model.passenger;
import com.trainManageSystem.service.PassengerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {

    @Resource
    private PassengerDao dao;
    @Override
    public void insertP(passenger p) {
        this.dao.insertP(p);
    }

    @Override
    public String selectNameById(String id) {
        return this.dao.selectNameById(id);
    }
}
