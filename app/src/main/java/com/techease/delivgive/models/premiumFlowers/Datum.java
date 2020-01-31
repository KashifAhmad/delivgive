
package com.techease.delivgive.models.premiumFlowers;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("created_at")
    private Object mCreatedAt;
    @SerializedName("flower_image")
    private String mFlowerImage;
    @SerializedName("flower_name")
    private String mFlowerName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("updated_at")
    private Object mUpdatedAt;

    public Object getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Object createdAt) {
        mCreatedAt = createdAt;
    }

    public String getFlowerImage() {
        return mFlowerImage;
    }

    public void setFlowerImage(String flowerImage) {
        mFlowerImage = flowerImage;
    }

    public String getFlowerName() {
        return mFlowerName;
    }

    public void setFlowerName(String flowerName) {
        mFlowerName = flowerName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Object getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
