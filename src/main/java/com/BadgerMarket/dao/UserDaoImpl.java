package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;

import com.BadgerMarket.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public boolean addItem(String username, Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(String username, String itemId) {
        return false;
    }

    @Override
    public Item getItem(String username, String itemId) {
        return null;
    }

    @Override
    public boolean updateItem(String username, Item item) {
        return false;
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return null;
    }

    @Override
    public boolean updateUserInfo(String username, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean deleteUserInfo(String username) {
        return false;
    }
}
