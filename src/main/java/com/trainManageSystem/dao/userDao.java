package com.trainManageSystem.dao;

import com.trainManageSystem.model.user;

public interface userDao {
    String login(String account);
    user selectUserByacc(String acc);
    void register(String account,String password);
    void updateId(String account,String id);
    void updatePassword(String account,String password);
    user[] selectUser();
    void deleteUser(String account);
    void insert(user u);
}
