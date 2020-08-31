package com.BadgerMarket.dao;

import com.BadgerMarket.entity.ItemImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao{
    private static String itemTable = "Item";
    private static String itemImageTable = "ItemImage";
    @Autowired
    JdbcTemplate jdbcTemplate;
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
        try {
            String sql = "select * from " + itemImageTable + " where itemId=?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ItemImage.class), itemId);
        } catch (DataAccessException e) {
            return null;
        }
    }
    @Override
    public boolean deleteAllItemImages(byte[] itemId) {
        try {
            String sql = "delete from " + itemImageTable + " where itemId=?";
            jdbcTemplate.update(sql, itemId);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean addItemImage(ItemImage image) {
        try {
            String sql = "Insert into " +  itemImageTable + " values(?,?,?,?)";
            jdbcTemplate.update(sql, image.getImageId(), image.getItemId(), image.getDiskUrl(), image.getHttpUrl());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean deleteItemImage(byte[] imageId) {
        try {
            String sql = "Delete from " + itemImageTable + " where imageId=?";
            jdbcTemplate.update(sql, imageId);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public ItemImage getItemImage(byte[] imageId) {
        try {
            String sql = "Select * from " + itemImageTable + " where imageId=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ItemImage.class), imageId);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<ItemImage> getAllImagesExcept(byte[] itemId, byte[] except) {
        try {
            String sql = "select * from " + itemImageTable + " where itemId=? and imageId != ?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ItemImage.class), itemId, except);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
