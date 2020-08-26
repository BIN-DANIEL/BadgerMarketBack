package com.BadgerMarket;

import com.BadgerMarket.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.apache.naming.SelectorContext.prefix;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {
    @Autowired
    DataSource dataSource;

    @Test
    public void runDataBase() throws Exception{
        dataSource.getConnection();
    }

}
