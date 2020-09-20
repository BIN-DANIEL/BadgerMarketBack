package com.BadgerMarket;

import com.BadgerMarket.dao.DataBaseDao;
import com.BadgerMarket.dao.DataBaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;




/**
 * 标注一个主程序
 */
@SpringBootApplication
public class Application implements WebMvcConfigurer{
    @Value("${spring: resourceImg: itemimageUrl}")
    public String itemimageUrl;

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        return multipartResolver;
    }
    @GetMapping("/logout")
    public String logoutPage() {
        return "HELLO";
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Image/**").addResourceLocations(itemimageUrl);
    }
    public static void main(String[] args) {

        ApplicationContext app = SpringApplication.run(Application.class, args);
        /** DataBase Initialization **/
//        DataBaseDao dataBaseDao = (DataBaseDao) app.getBean("dataBaseDaoImpl");
////        dataBaseDao.createUserTable();
////        dataBaseDao.createUserInfoTable();
////        dataBaseDao.createItemTable();
////        dataBaseDao.createItemImageTable();
        /** Start the Application **/
    }
}
