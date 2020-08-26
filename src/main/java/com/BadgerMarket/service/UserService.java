package com.BadgerMarket.service;

import com.BadgerMarket.dao.UserDao;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public boolean addItem(String username, Item item) {
        return userDao.addItem(username, item);
    }

    public boolean deleteItem(String username, String itemId) {
        return userDao.deleteItem(username, itemId);
    }

    public Item getItem(String username, String itemId) {
        return userDao.getItem(username, itemId);
    }

    public boolean updateItem(String username, Item item) {
        return userDao.updateItem(username, item);
    }


    public UserInfo getUserInfo(String username) {
        return userDao.getUserInfo(username);
    }


    public boolean updateUserInfo(String username, UserInfo userInfo) {
        return userDao.updateUserInfo(username, userInfo);
    }

    public boolean deleteUserInfo(String username) {
        return userDao.deleteUserInfo(username);
    }
}
