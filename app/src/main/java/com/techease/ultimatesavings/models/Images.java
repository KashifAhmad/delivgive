package com.techease.ultimatesavings.models;

public class Images {
    public int imageId;
    public String txt;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected;

    public Images(int imageId, String text) {

        this.imageId = imageId;
        this.txt = text;
    }
}
