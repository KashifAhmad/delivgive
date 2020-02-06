
package com.techease.delivgive.models.getBouquetsModels;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("bucket_image")
    private String mBucketImage;
    @SerializedName("bucket_title")
    private String mBucketTitle;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getBucketImage() {
        return mBucketImage;
    }

    public void setBucketImage(String bucketImage) {
        mBucketImage = bucketImage;
    }

    public String getBucketTitle() {
        return mBucketTitle;
    }

    public void setBucketTitle(String bucketTitle) {
        mBucketTitle = bucketTitle;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
