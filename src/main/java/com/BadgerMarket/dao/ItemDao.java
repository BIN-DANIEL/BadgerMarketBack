package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.ItemImage;

import java.util.List;

public interface ItemDao {
    public List<ItemImage> getAllImages(byte[] itemId);
    public boolean addItemImage(ItemImage image);
    public boolean deleteItemImage(byte[] imageId);
    public boolean deleteAllItemImages(byte[] itemId);
    public ItemImage getItemImage(byte[] imageId);
    public String byteArr2HexString(byte[] byteArr);
    public byte[] hexString2ByteArray(String hexString);
    public List<ItemImage> getAllImagesExcept(byte[] itemId, byte[] except);
    public List<String> getAllImagesUrlExcept(byte[] itemId, byte[] except);
    public String getItemImageUrl(byte[] imageId);
    public Integer getNumberOfItems(String category);
}
