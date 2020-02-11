
package com.techease.delivgive.models.plansListModels;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private String mId;
    @SerializedName("price")
    private String mPrice;

    public String getImage() {
        return mImage;
    }

    public void setImage(String Image) {
        this.mImage = Image;
    }

    @SerializedName("image")
    private String mImage;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
