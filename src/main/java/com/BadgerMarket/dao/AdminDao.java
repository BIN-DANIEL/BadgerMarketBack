package com.BadgerMarket.dao;

import com.BadgerMarket.ItemImageResource;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.ItemImage;
import com.BadgerMarket.entity.User;

import java.util.List;

public interface AdminDao {
    public boolean hasUser(String username);
    public boolean addUser(User user);
    public boolean deleteUser(String username);
    public User getUser(String username);
    public List<Item> getItemOfCategory(String category, int number, int page);
    public List<ItemImageResource> getItemResource(List<Item> items);
    public List<Item> getItemMatchKeyWord (String keyword, int number, int page);
    public Integer getNumMatchKeyWord(String keyword);
}
