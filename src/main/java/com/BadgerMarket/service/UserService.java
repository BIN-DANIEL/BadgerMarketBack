package com.BadgerMarket.service;

import com.BadgerMarket.dao.ItemDao;
import com.BadgerMarket.dao.UserDao;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private ItemDao itemDao;

    /**
     * Item Id will be automatically generated
     * @param item
     * @return
     */
    public boolean addItem(Item item) {
        // Generate random
        return userDao.addItem(item);
    }
    public String byteArr2HexString(byte[] byteArray) {
        return itemDao.byteArr2HexString(byteArray);
    }
    public byte[] hexString2ByteArray(String str) {
        return itemDao.hexString2ByteArray(str);
    }
    public boolean deleteItem(byte[] itemId) {
        return userDao.deleteItem(itemId);
    }
    public boolean deleteItem(String itemId) {
        return userDao.deleteItem(itemDao.hexString2ByteArray(itemId));
    }
    public List<Item> getAllItemsOfUser(String username) {
        return userDao.getAllItemsOfUser(username);
    }
    public Item getItem (byte[] itemId) {
        return userDao.getItem(itemId);
    }
    public Item getItem(String itemId) {

        return userDao.getItem(itemDao.hexString2ByteArray(itemId));
    }

    public boolean updateItem(Item item) {

        return userDao.updateItem(item);
    }

    public boolean addUserInfo(UserInfo info) {
        return userDao.addUserInfo(info);
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
