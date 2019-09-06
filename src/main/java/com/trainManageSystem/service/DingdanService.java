package com.trainManageSystem.service;

import com.trainManageSystem.model.Dingdan;

import java.util.Date;

public interface DingdanService {
    Dingdan[] selectByAccount(String account);
    void insertDD(Dingdan dingdan);
    int selectMax();
    void deleteDD(int order_id);
    void updateDate(int order_id, Date date);
    void deleteDD(String account);
    Dingdan[] selectDD(String account);
    Dingdan[] selectAll();
}
