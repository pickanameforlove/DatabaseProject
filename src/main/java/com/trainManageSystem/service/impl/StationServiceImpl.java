package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.StationDao;
import com.trainManageSystem.model.Station;
import com.trainManageSystem.service.StationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("stationService")
public class StationServiceImpl implements StationService {
    @Resource
    private StationDao dao;
    @Override
    public Station[] selectStationByName(String name) {
        return this.dao.selectStationByName(name);
    }
}
