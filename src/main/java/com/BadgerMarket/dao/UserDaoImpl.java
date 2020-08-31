package com.BadgerMarket.dao;

import com.BadgerMarket.entity.Item;

import com.BadgerMarket.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    private static String userInfoTable = "UserInfo";
    /**
     * For each Item added, the method will automatically generate an unique id.
     * @param item
     * @return true if success, false otherwise
     */
    @Override
    public boolean addItem(Item item) {
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
    public boolean deleteItem(byte[] itemId) {
        String sql = "delete from " + itemTable + " where itemId=?";
        try {
            jdbcTemplate.update(sql, itemId);
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    /**
     * Fetching Item that matches the itemId
     * @param itemId
     * @return Item instance if success, null otherwise
     */
    @Override
    public Item getItem(byte[] itemId) {
        try{
            String sql = "select * from " + itemTable + " where itemId=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Item.class), itemId);
        }catch (IncorrectResultSizeDataAccessException exception) {
            return null;
        }catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Fetch all items that belongs to the given user
     * @param username username of the User
     * @return List of items belongs to the user, if there isn't match, the List will not be null but empty.(size == 0)
     */
    @Override
    public List<Item> getAllItemsOfUser(String username) {
        try {
            String sql = "Select * from " +  itemTable + " where username=?";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Item>(), username);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updateItem(Item item) {
        //Item(itemId(Binary), title, description, price, username, coverImageId(Binary))
        try {
            String sql = "update " +  itemTable + " set title=?, description=?, price=?, coverImageId=? where itemId=?";
            jdbcTemplate.update(sql, item.getTitle(), item.getDescription(), item.getPrice(), item.getCoverImageId(), item.getItemId());
            return true;
        }catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public UserInfo getUserInfo(String username) {
        try {
            String sql = "Select * from " + userInfoTable + " where username=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserInfo.class), username);
        }catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updateUserInfo(String username, UserInfo userInfo) {
        try {//UserInfo(username, qq, weChat, phone, mail)
            String sql = "update " + userInfo + " set qq=?, weChat=?, phone=?, mail=? where username=?";
            jdbcTemplate.update(sql, userInfo.getQq(), userInfo.getWechat(), userInfo.getPhone(), userInfo.getMail(), username);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUserInfo(String username) {
        try {
            String sql = "Delete from " + userInfoTable + " where username=?";
            jdbcTemplate.update(sql, username);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean addUserInfo(UserInfo info) {
        try {
            String sql = "Insert into " + userInfoTable + " values(?,?,?,?,?)";
            jdbcTemplate.update(sql, info.getUsername(), info.getQq(), info.getWechat(), info.getPhone(), info.getMail());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }
}
