
package com.techease.delivgive.models.getUserPaymentCard;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("userCardCsv")
    private String mUserCardCsv;
    @SerializedName("userCardExpiry")
    private String mUserCardExpiry;
    @SerializedName("userCardHolderName")
    private String mUserCardHolderName;
    @SerializedName("userCardNumber")
    private String mUserCardNumber;
    @SerializedName("user_id")
    private String mUserId;

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

    public String getUserCardCsv() {
        return mUserCardCsv;
    }

    public void setUserCardCsv(String userCardCsv) {
        mUserCardCsv = userCardCsv;
    }

    public String getUserCardExpiry() {
        return mUserCardExpiry;
    }

    public void setUserCardExpiry(String userCardExpiry) {
        mUserCardExpiry = userCardExpiry;
    }

    public String getUserCardHolderName() {
        return mUserCardHolderName;
    }

    public void setUserCardHolderName(String userCardHolderName) {
        mUserCardHolderName = userCardHolderName;
    }

    public String getUserCardNumber() {
        return mUserCardNumber;
    }

    public void setUserCardNumber(String userCardNumber) {
        mUserCardNumber = userCardNumber;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

}
