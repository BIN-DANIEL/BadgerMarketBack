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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;

import java.util.List;
import java.util.UUID;

import static org.apache.naming.SelectorContext.prefix;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {
    private class Request {
        String title;
        Double price;
        String description;
        String username;
        MultipartFile coverImage;
        List<MultipartFile> otherImages;
        public Request(){}

        public List<MultipartFile> getOtherImages() {
            return otherImages;
        }

        public void setOtherImages(List<MultipartFile> otherImages) {
            this.otherImages = otherImages;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public MultipartFile getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(MultipartFile coverImage) {
            this.coverImage = coverImage;
        }

    }
    @Autowired
    UserService userService;
    @Autowired
    AdminDao adminDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void runDataBase() throws Exception{

    }
}
