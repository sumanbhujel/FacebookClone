package com.example.facebookclone.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookclone.R;
import com.example.facebookclone.adapter.UserAdapter;
import com.example.facebookclone.api.UsersAPI;
import com.example.facebookclone.model.User;
import com.example.facebookclone.ui.post.PostFragment;
import com.example.facebookclone.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    TextView textView;
    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //recyclerView = root.findViewById(R.id.rvPost);
        textView = root.findViewById(R.id.tvUser);
        imageView = root.findViewById(R.id.imgIcon);


        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                UserAdapter userAdapter = new UserAdapter();
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(userAdapter);
                showUser();

            }
        });
        return root;
    }

    private void showUser(){
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<User>> userlistCall = usersAPI.showUser();

        userlistCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userList = response.body();
                int i=0;
                for(User usr: userList){
                    i++;
                    textView.append(i+"." +" Name: "+ usr.getFirst_name()+" "+usr.getLast_name()
                            +" Username: "+usr.getEmail_phone() +"\n");

                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }

}