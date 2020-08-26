package com.BadgerMarket.entity;

import java.util.ArrayList;

/**
 * Entity class for Item
 * @ PrimaryKey{ItemId}
 * @ ForeignKey{coverImageId} --> ItemImage Table
 * @ ForeignKey{username} --> User Table; UserInfo Table
 *
 */
public class Item {
    private String ItemId; // Unique Id that identify each item.
    private String title; // title of the item
    private String description; // Item's description
    private double price; // Item's price(in $)
    private String username; // the username to which the Item belongs.
    private String coverImageId; // Id of the cover Image

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(String coverImageId) {
        this.coverImageId = coverImageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
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


}
