package com.example.facebookclone.ui.post;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookclone.R;
import com.example.facebookclone.adapter.PostAdapter;
import com.example.facebookclone.adapter.UserAdapter;
import com.example.facebookclone.api.PostAPI;
import com.example.facebookclone.api.UsersAPI;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.User;
import com.example.facebookclone.url.Url;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {

    private ImageView imageView;
    private Button button;
    private EditText editText;
    private String status, image;
    private Uri uri;
    private MultipartBody.Part mbImage;
//    private RecyclerView recyclerView;
//    private List<Post> postList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PostViewModel postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_post, container, false);

        imageView = root.findViewById(R.id.img);
        editText = root.findViewById(R.id.etStatus);
        button = root.findViewById(R.id.btnPost);
        //recyclerView = root.findViewById(R.id.rvPost);


        postViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

//                PostAdapter postAdapter = new PostAdapter(getContext(),postList);
//                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//                recyclerView.setLayoutManager(layoutManager);
//                recyclerView.setAdapter(postAdapter);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.
                                        Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        status = editText.getText().toString();
                        Post newPost = new Post(status, image);
                        addPost(newPost);

                    }
                });

            }
        });
        return root;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        postList = new ArrayList<>();
//        showPost();
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getImgReady();
            } else {
                Toast.makeText(getContext(), "No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            uri = data.getData();
            imageView.setImageURI(uri);
            askPermission();
        }
    }

    private void askPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.
                WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission
                            .WRITE_EXTERNAL_STORAGE},
                    2);
        } else {
            getImgReady();
            uploadImage(mbImage);
        }
    }


    private void getImgReady() {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn,
                null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String imgPath = cursor.getString(columnIndex);
        File file = new File(imgPath);
        RequestBody requestBody =
                RequestBody.create(MediaType.parse("image/*"), file);
        mbImage = MultipartBody.Part.createFormData("image",
                file.getName(), requestBody);
    }

    private void uploadImage(MultipartBody.Part img) {

        PostAPI postAPI = Url.getInstance().create(PostAPI.class);
        Call<String> imgUpload = postAPI.uploadImage(img);

        imgUpload.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getActivity(), response.body() + "Uploaded",
                        Toast.LENGTH_SHORT).show();
                image = response.body();
                Toast.makeText(getActivity(), "image name " + image, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("Error:", t.getMessage());
            }
        });
    }

//    private void showPost() {
//        PostAPI postApi = Url.getInstance().create(PostAPI.class);
//        Call<List<Post>> postListCall = postApi.showPost();
//
//        try {
//            Response<List<Post>> response = postListCall.execute();
//            postList = response.body();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void addPost(Post post) {

        PostAPI postAPI = Url.getInstance().create(PostAPI.class);
        Call<Void> postAdd = postAPI.addPost(post);

        postAdd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Post added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Post Error:", t.getMessage());
                Toast.makeText(getContext(), "Failed to add", Toast.LENGTH_SHORT).show();

            }
        });

    }


}