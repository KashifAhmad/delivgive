package com.techease.ultimatesavings.utils.networking;

import com.techease.ultimatesavings.models.changePasswordModels.ChangePasswordResponse;
import com.techease.ultimatesavings.models.freeFlowersModels.FreeFlowersResponse;
import com.techease.ultimatesavings.models.genericResponseModel.GenericResponse;
import com.techease.ultimatesavings.models.loginModels.LoginResponse;
import com.techease.ultimatesavings.models.premiumFlowers.PremiumResponse;
import com.techease.ultimatesavings.models.sendBouquetModels.SendBouquetResponse;
import com.techease.ultimatesavings.models.signUpModels.SignUpResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
                                                @Field("newPassword") String password);

    @GET("free_flowers")
    Call<FreeFlowersResponse> freeFlowers();

    @GET("premium_flowers")
    Call<PremiumResponse> premiumFlowers();

    @Multipart
    @POST("send")
    Call<SendBouquetResponse> sendBouquet(@Part("user_id") RequestBody userID,
                                          @Part MultipartBody.Part photo,
                                          @Part("image") RequestBody profilePicture,
                                          @Part("image_from") RequestBody imageFrom,
                                          @Part("image_to") RequestBody imageTo,
                                          @Part("from_phone_number") RequestBody phoneNumberFrom,
                                          @Part("to_phone_number") RequestBody phoneNumberTo,
                                          @Part("description") RequestBody description);

}

