package com.trainManageSystem.dao;

import com.trainManageSystem.model.Station;

public interface StationDao {
    Station[] selectStationByName(String name);
}
