package com.trainManageSystem.dao;
import com.trainManageSystem.model.TwoTrains;
public interface TwoTrainsDao {
    TwoTrains[] selectAll(String begin,String end,int b,int e);
}
