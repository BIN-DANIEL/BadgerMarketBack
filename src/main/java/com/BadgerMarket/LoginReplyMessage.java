package com.BadgerMarket;

import com.BadgerMarket.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class LoginReplyMessage {
    private boolean success;
    private String qq;
    private String weChat;
    private String mail;
    private String phone;
    /**
     * The relationship between itemResource and itemImageResource is 1-to-1.
     * itemImageResource.get(i) corresponds to the image Resources of itemResource.get(i)
     */
    private List<ItemInfo> itemInfo; // Key Information Of Items
    private List<ItemImageResource> itemImageResource; // URLs to Images of Item

    public LoginReplyMessage() {
        itemInfo = new ArrayList<>();
        itemImageResource = new ArrayList<>();
    }
    public List<ItemInfo> getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(List<ItemInfo> itemInfo) {
        this.itemInfo = itemInfo;
    }

    public List<ItemImageResource> getItemImageResource() {
        return itemImageResource;
    }

    public void setItemImageResource(List<ItemImageResource> itemImageResource) {
        this.itemImageResource = itemImageResource;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
