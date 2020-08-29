package com.BadgerMarket.service;

import com.BadgerMarket.dao.ItemDao;
import com.BadgerMarket.entity.ItemImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    @Qualifier("itemDaoImpl")
    private ItemDao itemDao;

    public List<ItemImage> getAllImages(byte[] itemId) {
        return itemDao.getAllImages(itemId);
    }


    public boolean addItemImage(byte[] itemId, ItemImage image) {
        return itemDao.addItemImage(itemId, image);
    }


    public boolean deleteItemImage(byte[] itemId, byte[] imageId) {
        return itemDao.deleteItemImage(itemId, imageId);
    }


    public boolean deleteAllItemImages(byte[] itemId) {
        return itemDao.deleteAllItemImages(itemId);
    }


    public boolean getItemImage(byte[] itemId, byte[] imageId) {
        return itemDao.getItemImage(itemId, imageId);
    }
}
