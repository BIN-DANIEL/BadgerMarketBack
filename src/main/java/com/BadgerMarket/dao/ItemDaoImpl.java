package com.BadgerMarket.dao;

import com.BadgerMarket.entity.ItemImage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{
    private static String itemTable = "Item";
    private static String itemImageTable = "ItemImage";

    @Override
    public String byteArr2HexString(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    @Override
    public byte[] hexString2ByteArray(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }

    @Override
    public List<ItemImage> getAllImages(byte[] itemId) {
        return null;
    }

    @Override
    public boolean addItemImage(byte[] itemId, ItemImage image) {
        return false;
    }

    @Override
    public boolean deleteItemImage(byte[] itemId, byte[] imageId) {
        return false;
    }

    @Override
    public boolean deleteAllItemImages(byte[] itemId) {
        return false;
    }

    @Override
    public boolean getItemImage(byte[] itemId, byte[] imageId) {
        return false;
    }
}
