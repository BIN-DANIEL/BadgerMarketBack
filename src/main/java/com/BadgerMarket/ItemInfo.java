package com.BadgerMarket;

public class ItemInfo {
    private String itemId; // Unique Id that identify each item.
    private String title; // title of the item
    private String description; // Item's description
    private double price; // Item's price(in $)
    private String username; // the username to which the Item belongs.

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
