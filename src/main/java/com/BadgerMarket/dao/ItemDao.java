package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.ItemImage;

import java.util.List;

public interface ItemDao {
    public List<ItemImage> getAllImages(byte[] itemId);
    public boolean addItemImage(byte[] itemId, ItemImage image);
    public boolean deleteItemImage(byte[] itemId, byte[] imageId);
    public boolean deleteAllItemImages(byte[] itemId);
    public boolean getItemImage(byte[] itemId, byte[] imageId);
    public String byteArr2HexString(byte[] byteArr);
    public byte[] hexString2ByteArray(String hexString);
}
