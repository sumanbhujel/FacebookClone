package com.example.facebookclone.model;

public class User {
    private String first_name;
    private String last_name;
    private String email_phone;
    private String password;
    private String birthday;
    private String gender;
    private String token;

    public User(String first_name, String last_name, String email_phone, String password, String birthday, String gender) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email_phone = email_phone;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }

    public User(String email_phone, String password) {
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

    public String getGender() {
        return gender;
    }

    public String getToken() {
        return token;
    }
}
