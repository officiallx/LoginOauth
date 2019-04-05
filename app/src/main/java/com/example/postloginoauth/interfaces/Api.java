package com.example.postloginoauth.interfaces;

import com.example.postloginoauth.models.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("oauth/token")
    Call<TokenResponse> getTokenAccess(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);
}
