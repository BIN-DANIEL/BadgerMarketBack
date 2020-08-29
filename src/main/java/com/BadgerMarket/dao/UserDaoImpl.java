package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;

import com.BadgerMarket.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Item(itemId(Binary), title, description, price, username, coverImageId(Binary))
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ItemDao itemDao;

    private static String itemTable = "Item";

    /**
     * For each Item added, the method will automatically generate an unique id.
     * @param username
     * @param item
     * @return true if success, false otherwise
     */
    @Override
    public boolean addItem(String username, Item item) {
        byte[] id = itemDao.hexString2ByteArray(UUID.randomUUID().toString().replace("-",""));
        item.setItemId(id);
        try {
            String sql = "Insert Into " + itemTable +
                    " values(?,?,?,?,?,?)";
            jdbcTemplate.update(sql, item.getItemId(), item.getTitle(), item.getDescription(),
                    item.getPrice(), item.getUsername(), item.getCoverImageId());

            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteItem(String username, byte[] itemId) {
        return false;
    }

    @Override
    public Item getItem(String username, byte[] itemId) {
        return null;
    }

    @Override
    public boolean updateItem(String username, Item item) {
        return false;
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return null;
    }

    @Override
    public boolean updateUserInfo(String username, UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean deleteUserInfo(String username) {
        return false;
    }
}
