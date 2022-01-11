package com.example.Minim2DSA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Minim2DSA.API.APIinterface;
import com.example.Minim2DSA.API.User;
import com.example.Minim2DSA.API.Repository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        
        ImageView profileImage = (ImageView) findViewById(R.id.profileimageView);
        TextView userNameTxt = (TextView) findViewById(R.id.userName_Text);
        TextView followersNumTxt = findViewById(R.id.followersNumView);
        TextView followingNumTxt = findViewById(R.id.followingNum);

        SharedPreferences sharedPrefer = getSharedPreferences("userName", Context.MODE_PRIVATE);
        String userName = sharedPrefer.getString("User", null);
        
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIinterface.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        APIinterface apiInterface = retrofit.create(APIinterface.class);
        Call<User> call = apiInterface.getInfoUser(userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(), Error.class);
                    startActivity(intent);
                }
                User user = response.body();
                userNameTxt.setText(user.getLogin());
                Picasso.get().load(user.getAvatar_url()).into(profileImage);
                userNameTxt.setText(userName);
                followersNumTxt.setText(String.valueOf(user.getFollowers()));
                followingNumTxt.setText(String.valueOf(user.getFollowing()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Intent intent = new Intent(getApplicationContext(), Error.class);
                startActivity(intent);
            }
        });

        Gson gson2 = new GsonBuilder().setLenient().create();
        Retrofit retrofit2 = new Retrofit.Builder().baseUrl(APIinterface.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson2)).build();
        APIinterface gerritAPI2 = retrofit2.create(APIinterface.class);
        Call<List<Repository>> call2 = gerritAPI2.getRepos(userName);
        call2.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call2, Response<List<Repository>> response) {
                if(!response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), Error.class);
                    startActivity(intent);
                }
                List<Repository> reposList = response.body();
                ListAdapter listAdapter = new ListAdapter(reposList, ReposActivity.this);
                RecyclerView recyclerView = findViewById(R.id.RecyclerViewList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(ReposActivity.this));
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Intent intent = new Intent (getApplicationContext(), Error.class);
                startActivity(intent);
            }
        });
    }



}