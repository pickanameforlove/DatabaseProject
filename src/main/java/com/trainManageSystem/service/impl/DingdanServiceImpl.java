package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.DingdanDao;
import com.trainManageSystem.model.Dingdan;
import com.trainManageSystem.service.DingdanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service("dingdanService")
public class DingdanServiceImpl implements DingdanService {
    @Resource
    private DingdanDao dao;
    @Override
    public Dingdan[] selectByAccount(String account) {
        return this.dao.selectByAccount(account);
    }

    @Override
    public void insertDD(Dingdan dingdan) {
        this.dao.insertDD(dingdan);
    }

    @Override
    public int selectMax() {
        return this.dao.selectMax();
    }

    @Override
    public void deleteDD(int order_id) {
        this.dao.deleteDD(order_id);
    }

    @Override
    public void updateDate(int order_id, Date date) {
            this.dao.updateDate(order_id, date);
    }

    @Override
    public void deleteDD(String account) {
this.dao.deleteD(account);
    }

    @Override
    public Dingdan[] selectDD(String account) {
        return this.dao.selectDD(account);
    }

    @Override
    public Dingdan[] selectAll() {
        return this.dao.selectAll();
    }
}
