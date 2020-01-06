package com.example.facebookclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookclone.api.FacebookInterface;
import com.example.facebookclone.model.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnLogin;
    EditText etEmail, etPassword;
    Retrofit retrofit;
    FacebookInterface facebookInterface;
    String mytoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.buttonCreate);
        btnLogin = findViewById(R.id.buttonLogin);
        etEmail = findViewById(R.id.email_phone);
        etPassword = findViewById(R.id.password);
        getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApi userApi = new UserApi(etEmail.getText().toString(), etPassword.getText().toString());
                userLogin(userApi);

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getInstance() {
        retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:7000/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        facebookInterface = retrofit.create(FacebookInterface.class);
    }

    private void userLogin(UserApi user) {
        Call<UserApi> userApiCall = facebookInterface.userLogin(user);

        userApiCall.enqueue(new Callback<UserApi>() {
            @Override
            public void onResponse(Call<UserApi> call, Response<UserApi> response) {
                if (response.code() == 200) {
                    mytoken = response.body().getToken();
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    intent.putExtra("token", mytoken);
                    startActivity(intent);
                    Log.d("MYToken", mytoken);
                } else {
                    Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserApi> call, Throwable t) {
                Log.d("LoginEx:", t.getMessage());

            }
        });
    }

}
