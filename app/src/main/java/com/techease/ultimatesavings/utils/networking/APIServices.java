package com.techease.ultimatesavings.utils.networking;

import com.techease.ultimatesavings.models.changePasswordModels.ChangePasswordResponse;
import com.techease.ultimatesavings.models.loginModels.LoginResponse;
import com.techease.ultimatesavings.models.genericResponseModel.GenericResponse;
import com.techease.ultimatesavings.models.signUpModels.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIServices {
    @FormUrlEncoded
    @POST("SignUp")
    Call<SignUpResponse> signUp(@Field("fullName") String fullName,
                                @Field("dob") String dob,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("retype_password") String retypePassword);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("reset")
    Call<GenericResponse> resetPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("checkCode")
    Call<GenericResponse> verifyCode(@Field("code") String code);

    @FormUrlEncoded
    @POST("ChangePassword")
    Call<ChangePasswordResponse> changePassword(@Field("email") String email,
                                                @Field("password") String password);


}

