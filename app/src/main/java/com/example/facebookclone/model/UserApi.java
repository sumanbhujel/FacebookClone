package com.example.facebookclone.model;

public class UserApi {
    private String first_name;
    private String last_name;
    private String password;
    private String birthday;
    private String email_phone;
    private String token;

    public UserApi(String first_name, String last_name, String password, String birthday, String email_phone, String token) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.birthday = birthday;
        this.email_phone = email_phone;
        this.token = token;
    }

    public UserApi(String email_phone, String password){
        this.email_phone = email_phone;
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail_phone() {
        return email_phone;
    }

    public String getToken() {
        return token;
    }
}
