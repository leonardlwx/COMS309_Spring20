package com.example.StudyBuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudyBuddy.LocalData.LocalDataStorage;
import com.example.StudyBuddy.LocalData.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Friends extends AppCompatActivity implements addFriendsDialog.addFriendListener{
    private Button addFriends;
    private Button friendList;

    private LocalDataStorage data;
    private User user;
    private String friendUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        data = new LocalDataStorage(getApplicationContext());
        user = data.getUserData();

        addFriends = findViewById(R.id.addFRIENDS);
        friendList = findViewById(R.id.viewFriendList);

        addFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) { openDialog(); }
        });

        friendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openFriendList(); }
        });
    }

    /**
     * Displays pop-up and allows user to add a friend through username
     */
    public void openDialog(){
        addFriendsDialog addNewFriend = new addFriendsDialog();
        addNewFriend.show(getSupportFragmentManager(), "Add friend");
    }

    /**
     * opens the friendList activity
     */
    public void openFriendList(){
        Intent intent = new Intent(this, friendList.class);
        startActivity(intent);
    }

    /**
     * sets friendUsername to the name given
     * @param name
     */
    @Override
    public void applyUsername(String name) {
        friendUsername = name;

        String URL = "http://coms-309-vb-5.cs.iastate.edu:8080/users";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL + "/" + friendUsername , null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                addNewFriend();
            }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No such user!", Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(request);
    }

    /**
     * Makes a POST request to add a new friend to this user
     */
    public void addNewFriend(){
        String URL = "http://coms-309-vb-5.cs.iastate.edu:8080/friends/";
        URL = URL.concat(user.getId());

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), friendUsername + " added as friend!", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                Gson gson = new Gson();
                String json = gson.toJson(friendUsername);

                try{ return (json).getBytes("utf-8"); }
                catch (UnsupportedEncodingException e) { return null; }
            }
        };
        rq.add(request);
    }
}
