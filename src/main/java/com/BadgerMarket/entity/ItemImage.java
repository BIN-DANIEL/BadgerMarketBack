package com.BadgerMarket.entity;

/**
 * This is the entity class of Item's Image
 * @ PrimaryKey{imageId}
 * @ ForeignKey{itemId} --> Item Table
 *
 */
public class ItemImage {
    private String itemId; // itemId to which the image correspond(该图片所对应的ItemId)
    private String imageId; // Id of Image
    private String DiskUrl; // Disk Route to the Image Resource(获取该图片资源的硬盘路径)
    private String httpUrl; // Http Route to the Image Resource(获取该图片资源的HTTP路径)

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDiskUrl() {
        return DiskUrl;
    }

    public void setDiskUrl(String diskUrl) {
        DiskUrl = diskUrl;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }


}
