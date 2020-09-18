package com.BadgerMarket.dao;

import com.BadgerMarket.ItemImageResource;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * schemas:
 * Item(itemId(Binary), title, description, price, username, coverImageId(Binary))
 * ItemImage(imageId(Binary), itemId(Binary), diskUrl, httpUrl)
 * User(username, password);
 * UserInfo(username, qq, weChat, phone, mail)
 */
@Repository
public class AdminDaoImpl implements AdminDao {
    private static String userTable = "User";

    private static String userInfoTable = "UserInfo";
    private static String itemTable = "Item";
    private static String itemImageTable = "ItemImage";
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ItemDao itemDao;

    @Override
    public List<Item> getItemOfCategory(String category, int number, int page) {
        // fetching elements from start to end
        int start = (page - 1) * number;
        int end = start + number;
        String sql = "Select * from " + itemTable + " where category=? limit ?,?";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Item.class), category, start, end);
    }

    @Override
    public List<Item> getItemMatchKeyWord(String keyword, int number, int page) {
        int start = (page - 1) * number;
        int end = start + number;
        String sql = "Select * from " + itemTable + " where title like CONCAT('%', ?, '%') limit ?,?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Item.class), keyword, start, end);
    }

    @Override
    public Integer getNumMatchKeyWord(String keyword) {
        String sql = "select count(*) from " + itemTable + " where title like CONCAT('%', ?, '%')";
        return jdbcTemplate.queryForObject(sql, Integer.class, keyword);
    }

    @Override
    public List<ItemImageResource> getItemResource(List<Item> items) {
//        List<ItemImageResource> ans = new ArrayList<>();
//        for (int i = 0; i < items.size(); i++) {
//            Item item = items.get(i);
//            ItemImageResource resource = new ItemImageResource();
//            resource.setOtherImages();
//
//        }
        return null;
    }

    @Override
    public boolean hasUser(String username) {
        String sql = "Select count(*) from " + userTable + " where username=?";
        Integer num = jdbcTemplate.queryForObject(sql, Integer.class, username);
        try{
            if (num == 1) {
                return true;
            } else {
                return false;
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            return false;
        } catch (DataAccessException e) {
            return false;
        }
    }
    @Override
    public boolean addUser(User user){
        String sql = "Insert into " + userTable + " values(?, ?)";
        try{
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
            return true;
        }catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUser(String username){
        try {
            String sql = "Delete from " + userTable + " where username=?";
            jdbcTemplate.update(sql, username);
            return true;
        } catch(DataAccessException e) {
            return false;
        }
    }


    @Override
    public User getUser(String username) {
        String sql = "Select * from " + userTable + " where username=?";
        try{
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
