package com.BadgerMarket.service;

import com.BadgerMarket.ItemImageResource;
import com.BadgerMarket.ItemInfo;
import com.BadgerMarket.dao.ItemDao;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.ItemImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService{
    @Autowired
    @Qualifier("itemDaoImpl")
    private ItemDao itemDao;

    public Integer getNumberOfItems(String category) {
        return itemDao.getNumberOfItems(category);
    }

    public List<String> getAllImagesUrlExcept(byte[] itemId, byte[] except) {
        return itemDao.getAllImagesUrlExcept(itemId, except);
    }

    public String getItemImageUrl(byte[] imageId) {
        return itemDao.getItemImageUrl(imageId);
    }

    public ItemInfo getItemInfo(Item item) {
        if (item == null) {
            return null;
        }
        ItemInfo info = new ItemInfo();
        info.setDescription(item.getDescription());
        info.setItemId(itemDao.byteArr2HexString(item.getItemId()));
        info.setPrice(item.getPrice());
        info.setTitle(item.getTitle());
        info.setUsername(item.getUsername());
        return info;
    }
    public ItemImageResource getItemImageResource(Item item) {
        if (item == null) {
            return null;
        }
        ItemImage coverImage = getItemImage(item.getCoverImageId());
        List<ItemImage> otherImages = getAllImagesExcept(item.getItemId(), item.getCoverImageId());
        List<String> imagesURL = new ArrayList<>();
        if (otherImages != null) {
            for (int i = 0; i < otherImages.size(); i++) {
                 imagesURL.add(otherImages.get(i).getHttpUrl());
            }
        }
        ItemImageResource resource = new ItemImageResource();
        if (coverImage != null) {
            resource.setCoverImageHttpURL(coverImage.getHttpUrl());
        }
        resource.setOtherImages(imagesURL);
        return resource;
    }
    public List<ItemImage> getAllImagesExcept(byte[] itemId, byte[] excludedImageId) {
            return itemDao.getAllImagesExcept(itemId, excludedImageId);
    }
    public List<ItemImage> getAllImages(byte[] itemId) {
        if (itemId == null || itemId.length == 0) {
            return new ArrayList<>();
        }
        return itemDao.getAllImages(itemId);
    }
    public List<ItemImage> getAllImages(String itemId) {
        if (itemId == null || itemId.length() == 0) {
            return new ArrayList<>();
        }
        return itemDao.getAllImages(itemDao.hexString2ByteArray(itemId));
    }

    public boolean addItemImage(ItemImage image) {
        if (image == null) {
            return false;
        }
        return itemDao.addItemImage(image);
    }
    public boolean deleteItemImage(byte[] imageId) {
        return itemDao.deleteItemImage(imageId);
    }
    public boolean deleteItemImage(String imageId) {

        return itemDao.deleteItemImage(itemDao.hexString2ByteArray(imageId));
    }

    public boolean deleteAllItemImages(byte[] itemId) {
        return itemDao.deleteAllItemImages(itemId);
    }
    public boolean deleteAllItemImages(String itemId) {

        return itemDao.deleteAllItemImages(itemDao.hexString2ByteArray(itemId));
    }

    public ItemImage getItemImage(byte[] imageId) {
        if (imageId == null || imageId.length == 0) {
            return null;
        }
        return itemDao.getItemImage(imageId);
    }
    public ItemImage getItemImage(String imageId) {
        if (imageId == null || imageId.length() == 0) {
            return null;
        }
        return itemDao.getItemImage(itemDao.hexString2ByteArray(imageId));
    }
}
