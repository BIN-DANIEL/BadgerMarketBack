package com.BadgerMarket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginServer {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin
    @RequestMapping("/login")
    public boolean method(@RequestParam(name="username", required = true) String username,
    @RequestParam(name="password") String password) {
        
        return jdbcTemplate == null;
    }

}
