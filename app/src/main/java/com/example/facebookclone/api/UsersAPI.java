package com.example.facebookclone.api;

import com.example.facebookclone.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersAPI {

    @POST("login")
    Call<User> userLogin(@Body User user);

    @GET("users")
    Call<List<User>> showUser();

    @POST("signup")
    Call<Void> addUser(@Body User user);


}
