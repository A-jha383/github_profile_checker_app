package com.example.android.github_search_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import javax.sql.DataSource;

public class profile extends AppCompatActivity {
String user;
TextView bio,follower,following;
RecyclerView mRecyclerView;
ImageView profile;
String url="https://api.github.com/users/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Log.d("user",getIntent().getStringExtra("username"));
        user =getIntent().getStringExtra("username");
        url =url+user;
        bio =(TextView)findViewById(R.id.bio);
        follower =(TextView)findViewById(R.id.followers);
        following=(TextView)findViewById(R.id.following);
        profile=(ImageView)findViewById(R.id.image);
        mRecyclerView =(RecyclerView)findViewById(R.id.repolist);
loaddata(url);
loadrepository(url);

    }

    public void loaddata(String url){
// Instantiate the RequestQueue.


        JsonObjectRequest jsonObjectRequest =new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imageurl = response.getString("avatar_url");
                            String followers = response.getString("followers");
                            String followings = response.getString("following");
                            String bios = response.getString("bio");
                            bio.setText("BIO: "+bios);
                            follower.setText(("FOLLOWERS:"+followers));
                            following.setText("FOLLOWING:"+followings);
                            Log.d("url",imageurl);
                            Glide.with(profile.this).load(imageurl).into(profile);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profile.this,"unable",Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }



    public void loadrepository(String url)
    {
        url=url+"/repos?per_page=100&sort=created";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        GsonBuilder gsonBuilder=new GsonBuilder();
                        Gson gson=gsonBuilder.create();
                        Repository[] repoarray=gson.fromJson(response,Repository[].class);
                        RepositoryAdapter repositoryAdapter=new RepositoryAdapter(repoarray,profile.this);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(profile.this));
                        mRecyclerView.setAdapter(repositoryAdapter);
                        mRecyclerView.setHasFixedSize(true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(profile.this, "Unable to fetch the data", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(profile.this).addToRequestQueue(stringRequest);

    }
}