package com.BadgerMarket.entity;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Entity class for Item
 * @ PrimaryKey{ItemId}
 * @ ForeignKey{coverImageId} --> ItemImage Table
 * @ ForeignKey{username} --> User Table; UserInfo Table
 *
 */
public class Item {
    private byte[] itemId; // Unique Id that identify each item.
    private String title; // title of the item
    private String description; // Item's description
    private double price; // Item's price(in $)
    private String username; // the username to which the Item belongs.
    private byte[] coverImageId; // Id of the cover Image
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getItemId() {
        return itemId;
    }

    public void setItemId(byte[] itemId) {
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

    public byte[] getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(byte[] coverImageId) {
        this.coverImageId = coverImageId;
    }
}
