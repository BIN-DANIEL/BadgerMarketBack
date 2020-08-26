package com.BadgerMarket.dao;

import com.BadgerMarket.entity.ItemImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{
    @Override
    public List<ItemImage> getAllImages(String itemId) {
        return null;
    }

    @Override
    public boolean addItemImage(String itemId, ItemImage image) {
        return false;
    }

    @Override
    public boolean deleteItemImage(String itemId, String imageId) {
        return false;
    }

    @Override
    public boolean deleteAllItemImages(String itemId) {
        return false;
    }

    @Override
    public boolean getItemImage(String itemId, String imageId) {
        return false;
    }
}
