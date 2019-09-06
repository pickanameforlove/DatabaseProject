package com.trainManageSystem.service;

import com.trainManageSystem.model.TwoTrains;

public interface TwoTrainsService {
    TwoTrains[] selectAll(String start,String end,int index);
}
