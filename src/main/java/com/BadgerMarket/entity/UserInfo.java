package com.BadgerMarket.entity;

/**
 * Entity class of User's Contact Information
 */
public class UserInfo {
    private String username;
    private String qq;
    private String weChat;
    private String phone;
    private String mail;

    public String getUsername() {
        return username;
    }

    /**
     * Setting the username. Before Setting need to check
     * if there is duplicate.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return weChat;
    }

    public void setWechat(String wechat) {
        this.weChat = wechat;
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
}
