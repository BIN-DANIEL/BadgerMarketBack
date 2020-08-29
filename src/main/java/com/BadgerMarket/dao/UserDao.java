package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.UserInfo;

import java.util.List;

public interface UserDao {
    public boolean addItem(Item item);
    public boolean deleteItem(byte[] itemId);
    public Item getItem(byte[] itemId);
    public boolean updateItem(Item item);
    public UserInfo getUserInfo(String username);
    public boolean updateUserInfo(String username, UserInfo userInfo);
    public boolean deleteUserInfo(String username);
    public List<Item> getAllItemsOfUser(String username);
}
