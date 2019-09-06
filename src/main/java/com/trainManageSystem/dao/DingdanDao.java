package com.trainManageSystem.dao;

import com.trainManageSystem.model.Dingdan;

import java.util.Date;

public interface DingdanDao {
    Dingdan[] selectByAccount(String account);
    void insertDD(Dingdan dingdan);
    int selectMax();
    void deleteDD(int order_id);
    void updateDate(int order_id, Date date);
    void deleteD(String account);
    Dingdan[] selectDD(String account);
    Dingdan[] selectAll();
}
