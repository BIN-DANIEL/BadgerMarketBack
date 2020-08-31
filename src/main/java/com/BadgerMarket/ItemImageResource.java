package com.BadgerMarket;

import java.util.List;

public class ItemImageResource {
    String coverImageHttpURL;
    List<String> otherImages;

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
