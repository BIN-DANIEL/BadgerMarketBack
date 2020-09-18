package com.BadgerMarket.server;

import com.BadgerMarket.entity.Item;
import com.BadgerMarket.entity.UserInfo;
import com.BadgerMarket.service.AdminService;
import com.BadgerMarket.service.ItemService;
import com.BadgerMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FetchItemServer {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    private class ReplyItem {
        private String title; // title of the item
        private String description; // Item's description
        private double price; // Item's price(in $)
        private String qq;
        private String weChat;
        private String phone;
        private String mail;
        String coverImageHttpURL;
        List<String> otherImages;

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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeChat() {
            return weChat;
        }

        public void setWeChat(String weChat) {
            this.weChat = weChat;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getCoverImageHttpURL() {
            return coverImageHttpURL;
        }

        public void setCoverImageHttpURL(String coverImageHttpURL) {
            this.coverImageHttpURL = coverImageHttpURL;
        }

        public List<String> getOtherImages() {
            return otherImages;
        }

        public void setOtherImages(List<String> otherImages) {
            this.otherImages = otherImages;
        }
    }
    @CrossOrigin
    @RequestMapping(value="/searchItem", method = RequestMethod.GET)
    public List<Object> searchItem(@RequestParam(name="keyword", required = true) String keyword,
                                   @RequestParam(name="number", required = true) Integer number,
                                   @RequestParam(name="page", required = true)Integer page) {
        List<Item> items = adminService.getItemMatchKeyWord(keyword, number, page);
        Integer numOfPages = adminService.getNumMatchKeyWord(keyword);
        if (numOfPages % number == 0) {
            numOfPages /= number;
        } else {
            numOfPages /= number;
            numOfPages += 1;
        }
        List<ReplyItem> replyItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ReplyItem replyItem = new ReplyItem();
            Item item = items.get(i);
            UserInfo userInfo = userService.getUserInfo(item.getUsername());

            //Set up Item Info
            replyItem.setTitle(item.getTitle());
            replyItem.setDescription(item.getDescription());
            replyItem.setPrice(item.getPrice());
            //Set up Contact Info
            replyItem.setWeChat(userInfo.getWechat());
            replyItem.setQq(userInfo.getQq());
            replyItem.setMail(userInfo.getMail());
            replyItem.setPhone(userInfo.getPhone());
            //Set up image resource
            replyItem.setCoverImageHttpURL(itemService.getItemImageUrl(item.getCoverImageId()));
            replyItem.setOtherImages(itemService.getAllImagesUrlExcept(item.getItemId(), item.getCoverImageId()));
            replyItems.add(replyItem);
        }
        List<Object> rep = new ArrayList<>();
        List<Integer> numPages = new ArrayList<>();
        for (int i = 0; i < numOfPages; i++) {
            numPages.add(1);
        }
        rep.add(numPages);
        rep.add(replyItems);
        return rep;
    }
    @CrossOrigin
    @RequestMapping(value = "/fetchItem", method = RequestMethod.GET)
    public List<Object> fetchItemOfCat(@RequestParam(name="category", required = true)String category,
                                          @RequestParam(name="number", required = true) int number, @RequestParam(name="page", required = true) int page) {
        List<Item> items = adminService.getItemsOfCategory(category, number, page);
        Integer numOfPages = itemService.getNumberOfItems(category); //Total Number Of Pages Of this category
        if (numOfPages % number == 0) {
            numOfPages /= number;
        } else {
            numOfPages /= number;
            numOfPages += 1;
        }
        List<ReplyItem> replyItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
             ReplyItem replyItem = new ReplyItem();
             Item item = items.get(i);
             UserInfo userInfo = userService.getUserInfo(item.getUsername());

             //Set up Item Info
             replyItem.setTitle(item.getTitle());
             replyItem.setDescription(item.getDescription());
             replyItem.setPrice(item.getPrice());
             //Set up Contact Info
             replyItem.setWeChat(userInfo.getWechat());
             replyItem.setQq(userInfo.getQq());
             replyItem.setMail(userInfo.getMail());
             replyItem.setPhone(userInfo.getPhone());
             //Set up image resource
             replyItem.setCoverImageHttpURL(itemService.getItemImageUrl(item.getCoverImageId()));
             replyItem.setOtherImages(itemService.getAllImagesUrlExcept(item.getItemId(), item.getCoverImageId()));
             replyItems.add(replyItem);
        }
        List<Object> rep = new ArrayList<>();
        List<Integer> numPages = new ArrayList<>();
        for (int i = 0; i < numOfPages; i++) {
             numPages.add(1);
        }
        rep.add(numPages);
        rep.add(replyItems);
        return rep;
    }
}
