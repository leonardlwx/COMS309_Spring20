package com.example.StudyBuddy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudyBuddy.LoginSystem.LoginPage;
import com.example.StudyBuddy.LoginSystem.Logout;

/**
 * Implements the Settings page of the app.
 * Currently has options to delete a user or to log a user out.
 */
public class settings extends AppCompatActivity {
    private Button toDel;
    private Button logout;
    private Button friends;

    private AlertDialog confirmDeletion;
    private AlertDialog.Builder builder;
    private RequestQueue requestQueue;

    CreateUserPage temp = new CreateUserPage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toDel = (Button) findViewById(R.id.toDelete);
        logout = findViewById(R.id.logoutSETTINGS);
        friends = findViewById(R.id.friendsSETTINGS);

        toDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDialog();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
                backToLogin();
            }
        });
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFriendsPage();
            }
        });
    }


    /**
     * Displays a pop-up dialog box to confirm with the user if they truly want to delete the account.
     * Has options YES or CANCEL.
     */
    public void displayDialog(){
        builder = new AlertDialog.Builder(settings.this);
        builder.setTitle("Are you sure you want to delete this account?");


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteThisUser();

            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }
        });

        confirmDeletion = builder.create();
        confirmDeletion.show();
    }

    /**
     * Uses a DELETE request to delete the current user from the database.
     * Is called by displayDialog upon the selection of YES.
     */
    public void deleteThisUser(){
        String url = "http://coms-309-vb-5.cs.iastate.edu:8080/users/";
        url = url.concat(temp.getUserInfo());
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest strReq = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                openDeletePage();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(strReq);
    }

    /**
     * Sends user to the deletedUser page.
     */
    public void openDeletePage(){
        Intent toStart = new Intent(this, deletedUser.class);
        startActivity(toStart);
    }

    /**
     * Logs user out. User will have to re-login the next time they open the app.
     */
    public void logoutUser(){
        Logout.out(this);
    }

    /**
     * Sends user back to login page.
     */
    public void backToLogin(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    /**
     * opens the friends page.
     */
    public void toFriendsPage(){
        Intent toFriends = new Intent(this, Friends.class);
        startActivity(toFriends);
    }
}

