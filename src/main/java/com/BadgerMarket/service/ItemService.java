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

    public List<ItemImage> getAllImages(String itemId) {
        return itemDao.getAllImages(itemDao.hexString2ByteArray(itemId));
    }


    public boolean addItemImage(String itemId, ItemImage image) {

        return itemDao.addItemImage(itemDao.hexString2ByteArray(itemId), image);
    }


    public boolean deleteItemImage(String itemId, String imageId) {

        return itemDao.deleteItemImage(itemDao.hexString2ByteArray(itemId), itemDao.hexString2ByteArray(imageId));
    }


    public boolean deleteAllItemImages(String itemId) {

        return itemDao.deleteAllItemImages(itemDao.hexString2ByteArray(itemId));
    }


    public boolean getItemImage(String itemId, String imageId) {

        return itemDao.getItemImage(itemDao.hexString2ByteArray(itemId), itemDao.hexString2ByteArray(imageId));
    }
}
