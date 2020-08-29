package com.BadgerMarket.dao;

import com.BadgerMarket.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    @Autowired
    JdbcTemplate jdbcTemplate;
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