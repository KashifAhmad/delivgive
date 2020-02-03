
package com.techease.delivgive.models.getUserProfileModel;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Datum {

    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("dob")
    private String mDob;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("fullname")
    private String mFullname;
    @SerializedName("id")
    private Long mId;
    @SerializedName("phone_number")
    private String mPhoneNumber;
    @SerializedName("profile_picture")
    private String mProfilePicture;
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFullname() {
        return mFullname;
    }

    public void setFullname(String fullname) {
        mFullname = fullname;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        mProfilePicture = profilePicture;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

}
