package com.BadgerMarket;


import com.BadgerMarket.service.AdminService;
import com.BadgerMarket.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginServer {
    /**
     * Reply message
     */
    private class LoginReplyMessage {
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        private boolean success;
    }
    @Autowired
    AdminService adminService;

    @CrossOrigin
    @RequestMapping("/login")
    public LoginReplyMessage method(@RequestParam(name="username", required = true) String username,
    @RequestParam(name="password") String password) {
        LoginReplyMessage msg = new LoginReplyMessage();
        if (adminService.hasUser(username)) {
            msg.setSuccess(true);
            return msg;
        } else {
            msg.setSuccess(false);
            return msg;
        }
    }

}
