package com.BadgerMarket.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseDaoImpl implements DataBaseDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createItemTable() {
        //insert into users values(unhex(replace(uuid(),'-','')), 'Andromeda');
//        String sql = "Drop Table If Exists Item";
//        jdbcTemplate.execute(sql);
        String sql = "CREATE TABLE Item(itemId BINARY(16) PRIMARY KEY , title CHAR(255), description TEXT(65535), price DOUBLE," +
                "username CHAR(255), coverImageId BINARY(16), category CHAR(255), " +
                "FOREIGN KEY(username) " +
                "REFERENCES User (username) ON DELETE CASCADE)";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void createItemImageTable() {
//        String sql = "Drop Table If Exists ItemImage";
//        jdbcTemplate.execute(sql);
        String sql = "CREATE TABLE ItemImage(imageId BINARY(16) PRIMARY KEY, itemId BINARY(16), " +
                "diskUrl CHAR(255), httpUrl CHAR(255), FOREIGN KEY (itemId) REFERENCES Item (itemId) ON DELETE CASCADE)";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void createUserTable() {
//        String sql = "Drop Table If Exists User";
//        jdbcTemplate.execute(sql);
        String sql = "CREATE TABLE User(username CHAR(255) PRIMARY KEY," +
                "password CHAR(255))";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void createUserInfoTable() {
//        String sql = "Drop Table If Exists UserInfo";
//        jdbcTemplate.execute(sql);
        String sql = "CREATE TABLE UserInfo(username CHAR(255) PRIMARY KEY, " +
                "qq CHAR(20), weChat CHAR(25), phone CHAR(15), mail VARCHAR(255)," +
                "FOREIGN KEY (username) REFERENCES User (username) ON DELETE CASCADE)";
        jdbcTemplate.execute(sql);
    }
}
