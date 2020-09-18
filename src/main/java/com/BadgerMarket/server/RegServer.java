package com.BadgerMarket.server;

import com.BadgerMarket.ItemInfo;
import com.BadgerMarket.RegInfo;
import com.BadgerMarket.entity.User;
import com.BadgerMarket.entity.UserInfo;
import com.BadgerMarket.service.AdminService;
import com.BadgerMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the server handling Register event
 */
@RestController
public class RegServer {
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    private class RegReplyMessage {
        private List<ItemInfo> itemInfo;
        private boolean regSuccess;
        public RegReplyMessage() {
            itemInfo = new ArrayList<>();
        }

        public List<ItemInfo> getItemInfo() {
            return itemInfo;
        }

        public boolean getRegSuccess() {
            return regSuccess;
        }
        public void setRegSuccess(boolean regSuccess) {
            this.regSuccess = regSuccess;
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  RegReplyMessage handleRegister(@RequestBody RegInfo regInfo) {
        RegReplyMessage msg = new RegReplyMessage();
        // Set Up User
        User newUser = new User();
        newUser.setUsername(regInfo.getUsername());
        newUser.setPassword(regInfo.getPassword());
        // Set Up User Information
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(regInfo.getUsername());
        userInfo.setMail(regInfo.getMail());
        userInfo.setQq(regInfo.getQq());
        userInfo.setPhone(regInfo.getPhone());
        userInfo.setWechat(regInfo.getWeChat());
        if (adminService.addUser(newUser)) {
            if (userService.addUserInfo(userInfo)) {
                // Success
                msg.setRegSuccess(true);
            } else {
                // Delete User as we fail to add UserInfo.
                adminService.deleteUser(newUser.getUsername());
                msg.setRegSuccess(false);
            }
        } else {
                msg.setRegSuccess(false);
        }
        return msg;
    }

    @CrossOrigin
    @RequestMapping("/hasUser")
    public boolean hasUser(@RequestParam(value="username") String username) {
       return adminService.hasUser(username);
    }
}
