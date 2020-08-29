package com.BadgerMarket.service;

import com.BadgerMarket.dao.AdminDao;
import com.BadgerMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

@Service
public class AdminService{
    @Autowired
    AdminDao adminDao;

    public boolean hasUser(String username) {
        return adminDao.hasUser(username);
    }


    public boolean addUser(User user) {
        return adminDao.addUser(user);
    }


    public boolean deleteUser(String username) {
        return adminDao.deleteUser(username);
    }

    public User getUser(String username) {
        return adminDao.getUser(username);
    }
}
