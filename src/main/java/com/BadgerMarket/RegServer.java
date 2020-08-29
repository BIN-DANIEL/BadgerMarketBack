package com.BadgerMarket;

import com.BadgerMarket.entity.User;
import com.BadgerMarket.service.AdminService;
import com.BadgerMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the server handling Register event
 */
@RestController
public class RegServer {
    @Autowired
    AdminService adminService;

    private class RegReplyMessage {
        private boolean regSuccess;
        private boolean hasUser;

        public boolean getRegSuccess() {
            return regSuccess;
        }

        public void setRegSuccess(boolean regSuccess) {
            this.regSuccess = regSuccess;
        }

        public boolean getHasUser() {
            return hasUser;
        }

        public void setHasUser(boolean hasUser) {
            this.hasUser = hasUser;
        }
    }
    @CrossOrigin
    @RequestMapping("/register")
    public RegReplyMessage handleRegister(@RequestParam(value = "username") String username,
                                          @RequestParam(value = "password") String password) {
        RegReplyMessage msg = new RegReplyMessage();
        if (adminService.hasUser(username)) {
            msg.setHasUser(true);
            msg.setRegSuccess(false);
            return msg;
        }
        //verify that there isn't such user
        msg.setHasUser(false);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (adminService.addUser(user)) {
            msg.setRegSuccess(true);
            return msg;
        } else {
            // something is wrong with Register
            msg.setRegSuccess(false);
            return msg;
        }
    }
}
