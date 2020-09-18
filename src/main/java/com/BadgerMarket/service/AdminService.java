package com.BadgerMarket.service;

import com.BadgerMarket.dao.AdminDao;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Item> getItemMatchKeyWord(String keyword, int number, int page) {
        return adminDao.getItemMatchKeyWord(keyword, number, page);
    }
    public Integer getNumMatchKeyWord(String keyword) {
        return adminDao.getNumMatchKeyWord(keyword);
    }
    public List<Item> getItemsOfCategory(String category, int number, int page) {
        return adminDao.getItemOfCategory(category, number, page);
    }

    public boolean deleteUser(String username) {
        return adminDao.deleteUser(username);
    }

    public User getUser(String username) {
        return adminDao.getUser(username);
    }
}
