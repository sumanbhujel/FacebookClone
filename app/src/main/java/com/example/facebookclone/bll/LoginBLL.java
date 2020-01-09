package com.example.facebookclone.bll;

import com.example.facebookclone.api.UsersAPI;
import com.example.facebookclone.model.User;
import com.example.facebookclone.strictmode.StrictModeClass;
import com.example.facebookclone.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
    boolean isloggedIn = false;

    public boolean userLogin(User user) {
        Call<User> userCall = usersAPI.userLogin(user);
        StrictModeClass.StrictMode();

        try{
            Response<User> loginResponse = userCall.execute();
            if(loginResponse.isSuccessful()){
                isloggedIn = true;
                Url.token += loginResponse.body().getToken();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return isloggedIn;
    }

}
