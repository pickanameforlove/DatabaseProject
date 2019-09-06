package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.TwoTrainsDao;
import com.trainManageSystem.model.TwoTrains;
import com.trainManageSystem.service.TwoTrainsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("twoTrainsService")
public class TwoTrainsServiceImpl implements TwoTrainsService {
    @Resource
    private TwoTrainsDao dao;
    @Override
    public TwoTrains[] selectAll(String start, String end,int index) {
        int b = 10*(index-1);
        int e = 10* index;
        return this.dao.selectAll(start,end,b,e);
    }
}
