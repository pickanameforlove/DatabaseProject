package com.trainManageSystem.service;

import com.trainManageSystem.model.passenger;

public interface PassengerService {
    void insertP(passenger p);
    String selectNameById(String id);
}
