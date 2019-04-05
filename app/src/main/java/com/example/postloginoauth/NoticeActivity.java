package com.example.postloginoauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.postloginoauth.interfaces.NoticeApi;
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

    Token token = new Token();
    String access_token;
    TextView notice_title, notice_desc;

    OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            Request.Builder builder = originalRequest.newBuilder().header("Bearer",
                    token != null ? token.toString() : access_token);

            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }
    }).build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://13.233.254.53:8081/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_activty);

        notice_title = findViewById(R.id.notice_title);
        notice_desc = findViewById(R.id.notice_desc);

        access_token = token.getToken();
        fetchNotice();

    }

    private void fetchNotice() {

        final NoticeApi service = retrofit.create(NoticeApi.class);

        Map<String, Object> data = new HashMap<>();
        data.put("pageId", String.valueOf(1));
        data.put("sizeId", String.valueOf(10));

        // simplified call to request the news with already initialized service

        Call<NoticeResponse> call = service.getNotice(access_token, data);
        call.enqueue(new Callback<NoticeResponse>() {
            @Override
            public void onResponse(Call<NoticeResponse> call, Response<NoticeResponse> response) {
                if (response.isSuccessful()) {
                    NoticeResponse noticeResponse = response.body();
                    Toast.makeText(getApplicationContext(), "Response : " + noticeResponse, Toast.LENGTH_SHORT).show();
                    Log.d("Notice", "onResponse: " + noticeResponse);
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NoticeResponse> call, Throwable t) {

                Log.d("Notice", "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
