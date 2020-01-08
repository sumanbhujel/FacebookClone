package com.example.facebookclone.api;

import com.example.facebookclone.model.UserApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FacebookInterface {

    @POST("login")
    Call<UserApi> userLogin(@Body UserApi userApi);

    @GET("users")
    Call<List<UserApi>> showUser();

    @POST("signup")
    Call<Void> addUser(@Body UserApi userApi);


//    @GET("tasks")
//    Call<List<Task>> getUsers(@Header("Authorization") String authHeader);


}
