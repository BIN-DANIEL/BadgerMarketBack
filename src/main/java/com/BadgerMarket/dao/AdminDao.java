package com.BadgerMarket.dao;

import com.BadgerMarket.entity.User;

public interface AdminDao {
    public boolean hasUser(String username);
    public boolean addUser(User user);
    public boolean deleteUser(String username);
    public User getUser(String username);
}
