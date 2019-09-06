package com.trainManageSystem.dao;

import com.trainManageSystem.model.passenger;

public interface PassengerDao {
    void insertP(passenger p);
    String selectNameById(String id);

}
