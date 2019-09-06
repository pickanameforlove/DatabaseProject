package com.trainManageSystem.service;

import com.trainManageSystem.model.Station;

public interface StationService {
    Station[] selectStationByName(String name);
}
