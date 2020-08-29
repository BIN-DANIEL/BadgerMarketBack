package com.BadgerMarket;

import com.BadgerMarket.dao.DataBaseDao;
import com.BadgerMarket.dao.DataBaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;




/**
 * 标注一个主程序
 */
@Configuration
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        ApplicationContext app = SpringApplication.run(Application.class, args);
        /** DataBase Initialization **/
//        DataBaseDao dataBaseDao = (DataBaseDao) app.getBean("dataBaseDaoImpl");
//        dataBaseDao.createUserTable();
//        dataBaseDao.createUserInfoTable();
//        dataBaseDao.createItemTable();
//        dataBaseDao.createItemImageTable();
        /** Start the Application **/
    }
}
