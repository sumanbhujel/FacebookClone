package com.example.facebookclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookclone.bll.LoginBLL;
import com.example.facebookclone.model.User;
import com.example.facebookclone.url.Url;


public class LoginActivity extends AppCompatActivity {

    Button btnCreate, btnLogin;
    EditText etEmail, etPassword;
    String email_phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnCreate = findViewById(R.id.buttonCreate);
        btnLogin = findViewById(R.id.buttonLogin);
        etEmail = findViewById(R.id.email_phone);
        etPassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_phone = etEmail.getText().toString();
                password = etPassword.getText().toString();

                User user = new User(email_phone,password);

                LoginBLL loginBLL = new LoginBLL();
                if(validate()){
                    if(loginBLL.userLogin(user)){
                        //dashboard
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    public boolean validate() {

        if (TextUtils.isEmpty(email_phone)) {
            etEmail.setError("Enter your email or phone number");
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter your password");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    /*private void userLogin(User user) {
        Call<User> userApiCall = usersAPI.userLogin(user);

        userApiCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    mytoken = response.body().getToken();
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("token", mytoken);
                    startActivity(intent);
                    Log.d("MYToken", mytoken);
                } else {
                    Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("LoginEx:", t.getMessage());

            }
        });
    }*/

}
