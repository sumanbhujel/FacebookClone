package com.example.facebookclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class SignUpActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etPassword, etBirthday, etEmailPhone;
    RadioButton rbtnMale, rbtnFemale, rbtnCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirstName = findViewById(R.id.first_name);
        etLastName = findViewById(R.id.last_name);
        etEmailPhone = findViewById(R.id.email_phone);
        etPassword = findViewById(R.id.password);
        etBirthday = findViewById(R.id.birthday);

        rbtnMale = findViewById(R.id.rbMale);
        rbtnFemale = findViewById(R.id.rbFemale);
        rbtnCustom = findViewById(R.id.rbCustom);



    }
}
