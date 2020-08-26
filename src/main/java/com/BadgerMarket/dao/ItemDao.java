package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.ItemImage;

import java.util.List;

public interface ItemDao {
    public List<ItemImage> getAllImages(String itemId);
    public boolean addItemImage(String itemId, ItemImage image);
    public boolean deleteItemImage(String itemId, String imageId);
    public boolean deleteAllItemImages(String itemId);
    public boolean getItemImage(String itemId, String imageId);
}
