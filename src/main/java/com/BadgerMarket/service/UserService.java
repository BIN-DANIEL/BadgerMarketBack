package com.BadgerMarket.service;

import com.BadgerMarket.dao.ItemDao;
import com.BadgerMarket.dao.UserDao;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService{
    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;
    @Autowired
    private ItemDao itemDao;
    public boolean addItem(String username, Item item) {
        // Generate random
        item.setItemId(itemDao.hexString2ByteArray(UUID.randomUUID().toString().replace("-","")));
        return userDao.addItem(username, item);
    }

    public boolean deleteItem(String username, byte[] itemId) {
        return userDao.deleteItem(username, itemId);
    }

    public Item getItem(String username, byte[] itemId) {
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
