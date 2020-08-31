package com.BadgerMarket.server;


import com.BadgerMarket.ItemImageResource;
import com.BadgerMarket.ItemInfo;
import com.BadgerMarket.LoginReplyMessage;
import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.ItemImage;
import com.BadgerMarket.entity.UserInfo;
import com.BadgerMarket.service.AdminService;
import com.BadgerMarket.service.ItemService;
import com.BadgerMarket.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class LoginServer {
    /**
     * Reply message
     */
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    ItemService itemService;
    @CrossOrigin
    @RequestMapping("/login")
    public LoginReplyMessage method(@RequestParam(name="username", required = true) String username,
                                    @RequestParam(name="password") String password) {
        LoginReplyMessage msg = new LoginReplyMessage();
        if (adminService.hasUser(username)) {
            List<Item> items = userService.getAllItemsOfUser(username);
            List<ItemInfo> itemInfos = new ArrayList<>();
            List<ItemImageResource> itemImageResources = new ArrayList<>();
            // Fetch The User Information
            UserInfo info = userService.getUserInfo(username);
            if (info != null) {
                msg.setMail(info.getMail());
                msg.setPhone(info.getPhone());
                msg.setQq(info.getQq());
                msg.setWeChat(info.getWechat());
            }
            // Fetch User's Items and related Information
            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    Item item = items.get(i);
                    ItemImageResource imageResource = itemService.getItemImageResource(item);
                    ItemInfo itemInfo = itemService.getItemInfo(item);
                    itemInfos.add(itemInfo);
                    itemImageResources.add(imageResource);
                }
            }
            msg.setItemImageResource(itemImageResources);
            msg.setItemInfo(itemInfos);
            msg.setSuccess(true);
            return msg;
        } else {
            msg.setSuccess(false);
            return msg;
        }
    }

}
