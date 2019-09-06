package com.trainManageSystem.service.impl;

import com.trainManageSystem.dao.DingdanDao;
import com.trainManageSystem.dao.TicketDao;
import com.trainManageSystem.dao.userDao;
import com.trainManageSystem.model.Dingdan;
import com.trainManageSystem.model.user;
import com.trainManageSystem.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private com.trainManageSystem.dao.userDao userDao;
    @Resource
    private DingdanDao ddao;
    @Resource
    private TicketDao tdao;
    @Override
    public String login(String account) {
        return this.userDao.login(account);
    }

    @Override
    public user selectUserByacc(String acc) {
        return this.userDao.selectUserByacc(acc);
    }

    @Override
    public void register(String account, String password) {
        this.userDao.register(account, password);
    }

    @Override
    public void updateId(String account, String id) {
        this.userDao.updateId(account, id);
    }

    @Override
    public void updatePassword(String account, String password) {
        this.userDao.updatePassword(account, password);
    }

    @Override
    public user[] selectUser() {
        return this.userDao.selectUser();
    }

    @Override
    public void deleteUser(String account) {
        this.userDao.deleteUser(account);

    }

    @Override
    public void insert(user u) {
        this.userDao.insert(u);
    }
}
