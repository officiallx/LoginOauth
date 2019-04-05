package com.example.postloginoauth.interfaces;

import com.example.postloginoauth.models.NoticeResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Bleeding Rain on 4/5/2019.
 */

public interface NoticeApi {

    @Headers({
            "Content-Type: application/www-x-form-urlencoded"
    })

    @POST("institution/notice/get")
    Call<NoticeResponse> getNotice (
            @Header("Authorization") String Authorization,
            @QueryMap Map<String, Object> options );
}
