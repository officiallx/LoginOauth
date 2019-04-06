package com.example.postloginoauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.postloginoauth.interfaces.NoticeApi;
import com.example.postloginoauth.models.NoticeRequest;
import com.example.postloginoauth.models.NoticeResponse;
import com.example.postloginoauth.models.Token;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NoticeActivity extends AppCompatActivity {

    //Token token = new Token();
    String bearertok = "4cd78135-12aa-4c04-8ec7-c81e45e0acfe"; //ahile ko lagi etikai haleko
    NoticeRequest noticeRequest = new NoticeRequest(); // notice request gives us body
    TextView notice_title, notice_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_activty);

        this.notice_title = findViewById(R.id.notice_title);
        this.notice_desc = findViewById(R.id.notice_desc);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.233.254.53:8081/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NoticeApi service = retrofit.create(NoticeApi.class);

        Map<String, Object> data = new HashMap<>();
        data.put("page", String.valueOf(0));
        data.put("size", String.valueOf(10));

        // simplified call to request the news with already initialized service

        Call<NoticeResponse> call = service.getNotice("Bearer "+ bearertok, data, noticeRequest );
        call.enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {

                    NoticeResponse noticeResponse = response.body();

                    Toast.makeText(getApplicationContext(), "Response : " + noticeResponse, Toast.LENGTH_SHORT).show();
                    Log.d("Notice", "onResponse: " + noticeResponse);
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {

                Log.d("Notice", "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
