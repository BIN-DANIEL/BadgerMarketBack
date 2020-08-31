package com.BadgerMarket;

import com.BadgerMarket.Application;

import com.BadgerMarket.dao.AdminDao;
import com.BadgerMarket.dao.ItemDao;
import com.BadgerMarket.dao.UserDao;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.User;
import com.BadgerMarket.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import java.util.UUID;

import static org.apache.naming.SelectorContext.prefix;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {
    @Autowired
    UserService userService;

    @Autowired
    ItemDao itemDao;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    public void runDataBase() throws Exception{
           String id = "4D937D9C049A4B5BA9DBA0D7419832FE";
           Item item = userService.getItem(id);
           item.setUsername("HELLO");
           item.setDescription("你好");
           userService.updateItem(item);

    }
}
