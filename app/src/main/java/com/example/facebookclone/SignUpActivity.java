package com.example.facebookclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facebookclone.api.UsersAPI;
import com.example.facebookclone.model.User;
import com.example.facebookclone.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etPassword, etBirthday, etEmailPhone;
    TextView textView;
    RadioGroup radioGroup;
    Button btnSignup;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName = findViewById(R.id.first_name);
        etLastName = findViewById(R.id.last_name);
        etEmailPhone = findViewById(R.id.email_phone);
        etPassword = findViewById(R.id.password);
        etBirthday = findViewById(R.id.birthday);
        btnSignup = findViewById(R.id.buttonSignUp);
        radioGroup= findViewById(R.id.rgGender);
        textView = findViewById(R.id.account);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbMale) {
                    gender= "Male";
                }
                if (checkedId == R.id.rbFemale) {
                    gender= "Female";
                }
                if (checkedId == R.id.rbCustom) {
                    gender= "Custom";
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first_name = etFirstName.getText().toString();
                String last_name = etLastName.getText().toString();
                String email_phone = etEmailPhone.getText().toString();
                String password = etPassword.getText().toString();
                String birthday = etBirthday.getText().toString();

                User user = new User(first_name,last_name,email_phone,password,birthday,gender);
                addUser(user);

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void addUser(User user){

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Void> userAdd = usersAPI.addUser(user);

        userAdd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Failed"+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
