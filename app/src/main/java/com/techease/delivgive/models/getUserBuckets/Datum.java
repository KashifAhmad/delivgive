
package com.techease.delivgive.models.getUserBuckets;


import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("description")
    private Object mDescription;
    @SerializedName("from_phone_number")
    private String mFromPhoneNumber;
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("image_from")
    private String mImageFrom;
    @SerializedName("image_to")
    private String mImageTo;
    @SerializedName("to_phone_number")
    private String mToPhoneNumber;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("user_id")
    private String mUserId;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Object getDescription() {
        return mDescription;
    }

    public void setDescription(Object description) {
        mDescription = description;
    }

    public String getFromPhoneNumber() {
        return mFromPhoneNumber;
    }

    public void setFromPhoneNumber(String fromPhoneNumber) {
        mFromPhoneNumber = fromPhoneNumber;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getImageFrom() {
        return mImageFrom;
    }

    public void setImageFrom(String imageFrom) {
        mImageFrom = imageFrom;
    }

    public String getImageTo() {
        return mImageTo;
    }

    public void setImageTo(String imageTo) {
        mImageTo = imageTo;
    }

    public String getToPhoneNumber() {
        return mToPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        mToPhoneNumber = toPhoneNumber;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
