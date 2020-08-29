package com.BadgerMarket.entity;

/**
 * This is the entity class of Item's Image
 * @ PrimaryKey{imageId}
 * @ ForeignKey{itemId} --> Item Table
 *
 */
public class ItemImage {
    private byte[] itemId; // itemId to which the image correspond(该图片所对应的ItemId)
    private byte[] imageId; // Id of Image
    private String diskUrl; // Disk Route to the Image Resource(获取该图片资源的硬盘路径)
    private String httpUrl; // Http Route to the Image Resource(获取该图片资源的HTTP路径)

    public byte[] getImageId() {
        return imageId;
    }

    public void setImageId(byte[] imageId) {
        this.imageId = imageId;
    }

    public byte[] getItemId() {
        return itemId;
    }

    public void setItemId(byte[] itemId) {
        this.itemId = itemId;
    }

    public String getDiskUrl() {
        return diskUrl;
    }

    public void setDiskUrl(String diskUrl) {
        this.diskUrl = diskUrl;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }


}
