package com.example.StudyBuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Is the first page a new user will see in this app.
 */
public class CreateUserPage extends AppCompatActivity {
    private RequestQueue requestQueue;
    private RequestQueue reqGet;
    private EditText userName;
    private Button btnCreateUser;
    private static String userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_page);

        userName = (EditText)findViewById(R.id.userInput);
        btnCreateUser = (Button) findViewById(R.id.butUserCreation);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserInfo(userName.getText().toString());
                Submit(getUserInfo());
                openMainPage();
            }
        });
    }

    /**
     * Sends a POST request to the server in order to submit the new user information.
     * @param info User chosen string to be the username of this account.
     */
    private void Submit(String info){
        final String userChosenName = info;
        String url = "http://coms-309-vb-5.cs.iastate.edu:8080/users/";
        url = url.concat(info);

        reqGet = Volley.newRequestQueue(getApplicationContext());

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), obj.toString(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(strReq);
    }

    /**
     * Sends user to mainPage.
     */
    public void openMainPage(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    /**
     * Sets userInfo
     * @param info Input string to set userInfo
     */
    public void setUserInfo(String info){
        userInfo = info;

    }

    /**
     * Shows userInfo
     * @return userInfo
     */
    public String getUserInfo(){
        return userInfo;
    }
}
