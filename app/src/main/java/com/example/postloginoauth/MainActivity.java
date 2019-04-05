package com.example.postloginoauth;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postloginoauth.interfaces.Api;
import com.example.postloginoauth.models.Token;
import com.example.postloginoauth.models.TokenRequest;
import com.example.postloginoauth.models.TokenResponse;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername, txtPassword;
    Api token;
    private String credentials = Credentials.basic("kinder", "kinder019");
    String username, password, grant_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        token != null ? token.toString() : credentials);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.233.254.53:8081/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api service = retrofit.create(Api.class);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TokenRequest tokenRequest = new TokenRequest();

                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();

                if(username!=null && password!=null) {

                    tokenRequest.setUsername(username);
                    tokenRequest.setPassword(password);
                    tokenRequest.setGrant_type(grant_type = "password");

                    Call<TokenResponse> tokenResponseCall = service.getTokenAccess(username, password, grant_type);

                    tokenResponseCall.enqueue(new Callback<TokenResponse>() {

                        @Override
                        public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                            if (response.isSuccessful()) {
                                //int statusCode = response.code();

                                TokenResponse tokenResponse = response.body();

                                //check if token is null or not and save it to token class
                                Token save_token = new Token();
                                assert tokenResponse != null;
                                save_token.setToken(tokenResponse.getAccess_token());

                                Log.d("token", "Token: " + save_token);
                                Log.d("token", "Token: " + tokenResponse);

                                //Log.d("LoginActivity", "onResponse: " + statusCode);

                                //Alert Dialog show
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                                builder1.setMessage("Token: " + tokenResponse);
                                builder1.setCancelable(true);

                                builder1.setPositiveButton(
                                        "Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alert11 = builder1.create();
                                alert11.show();

                                Intent i = new Intent(MainActivity.this,NoticeActivity.class);
                                startActivity(i);

                                Toast.makeText(getApplicationContext(), "Token : " + tokenResponse, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Check your Username or Password ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<TokenResponse> call, Throwable t) {

                            Log.d("LoginActivity", "onFailure: " + t.getMessage());
                            Toast.makeText(getApplicationContext(), "Failed : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(), "Empty Fields ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
