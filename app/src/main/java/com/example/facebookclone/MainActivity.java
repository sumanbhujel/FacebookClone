package com.example.facebookclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookclone.api.UsersAPI;
import com.example.facebookclone.bll.LoginBLL;
import com.example.facebookclone.model.User;
import com.example.facebookclone.url.Url;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnLogin;
    EditText etEmail, etPassword;
    Retrofit retrofit;
    UsersAPI usersAPI;
    String mytoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.buttonCreate);
        btnLogin = findViewById(R.id.buttonLogin);
        etEmail = findViewById(R.id.email_phone);
        etPassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(etEmail.getText().toString(), etPassword.getText().toString());

                LoginBLL loginBLL = new LoginBLL();
                if(loginBLL.userLogin(user)){
                    //dashboard
                    Toast.makeText(MainActivity.this, Url.token, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }

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

    /*private void userLogin(User user) {
        Call<User> userApiCall = usersAPI.userLogin(user);

        userApiCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
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
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LoginEx:", t.getMessage());

            }
        });
    }*/

}
