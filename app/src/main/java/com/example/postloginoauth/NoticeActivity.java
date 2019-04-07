package com.example.postloginoauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.postloginoauth.adapter.NoticeAdapter;
import com.example.postloginoauth.interfaces.NoticeApi;
import com.example.postloginoauth.models.Content;
import com.example.postloginoauth.models.Notice;
import com.example.postloginoauth.models.NoticeRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeActivity extends AppCompatActivity {

    //Token token = new Token();
    String bearertok = "02acb643-c62c-4075-83c9-9a517786cf03"; //ahile ko lagi etikai haleko
    NoticeRequest noticeRequest = new NoticeRequest(); // notice request gives us body
    Notice notice = new Notice();
    List<Notice> contents = new ArrayList<>();
    RecyclerView rv_notice;
    RecyclerView.LayoutManager layoutManager;
    NoticeAdapter noticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_activty);

        rv_notice = findViewById(R.id.rv_notice);
        layoutManager = new LinearLayoutManager(this);
        rv_notice.setLayoutManager(layoutManager);

        noticeAdapter = new NoticeAdapter(contents);
        rv_notice.setAdapter(noticeAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.233.254.53:8081/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NoticeApi service = retrofit.create(NoticeApi.class);

        Map<String, Object> data = new HashMap<>();
        data.put("size", String.valueOf(10));
        data.put("page", String.valueOf(0));

        // simplified call to request the news with already initialized service

        Call<Notice> noticeCall = service.getNotice("Bearer"+bearertok,data,noticeRequest);
        noticeCall.enqueue(new Callback<Notice>() {
            @Override
            public void onResponse(Call<Notice> call, Response<Notice> response) {

                contents = (List<Notice>) response.body();
                noticeAdapter = new NoticeAdapter(contents);
                rv_notice.setAdapter(noticeAdapter);
                Log.d("notice", "onResponse: "+notice);
                Toast.makeText(getApplicationContext(),"Notice"+notice.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Notice> call, Throwable t) {
                Log.d("notice", "onFailure: "+t.getMessage());
            }
        });

    }
}
