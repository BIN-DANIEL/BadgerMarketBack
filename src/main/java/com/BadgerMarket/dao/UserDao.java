package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.UserInfo;

public interface UserDao {
    public boolean addItem(String username, Item item);
    public boolean deleteItem(String username, byte[] itemId);
    public Item getItem(String username, byte[] itemId);
    public boolean updateItem(String username, Item item);
    public UserInfo getUserInfo(String username);
    public boolean updateUserInfo(String username, UserInfo userInfo);
    public boolean deleteUserInfo(String username);
}
