package com.example.postloginoauth.interfaces;

import com.example.postloginoauth.models.Notice;
import com.example.postloginoauth.models.NoticeRequest;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Bleeding Rain on 4/5/2019.
 */

public interface NoticeApi {

    @Headers({
            "Content-Type: application/json"
    })

    @POST("institution/notice/get")
    Call<Notice> getNotice (
            @Header("Authorization") String authorization,
            @QueryMap Map<String, Object> options,
            @Body NoticeRequest noticeRequest);
}
