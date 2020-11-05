package com.example.StudyBuddy.LoginSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudyBuddy.LocalData.LocalDataStorage;
import com.example.StudyBuddy.LocalData.User;
import com.example.StudyBuddy.MainMenu;
import com.example.StudyBuddy.R;
import com.google.gson.Gson;

import org.json.JSONObject;

public class LoginPage extends AppCompatActivity implements View.OnClickListener
{
    Button login_b;
    TextView register_tw;
    TextView error_tw;
    EditText username_et;
    EditText password_et;

    SharedPreferences details;

    private static final String sharedPreference = "userDetails";
    static final String URL = "http://coms-309-vb-5.cs.iastate.edu:8080/users";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        details = getSharedPreferences(sharedPreference, 0);
        if(details.getBoolean("status", false))
        {
            openDashboardActivity();
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_b = findViewById(R.id.Login_button);
        login_b.setOnClickListener(this);

        register_tw = findViewById(R.id.Register_textView);
        register_tw.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.Login_button: validateLoginDetails();
                                    break;

            case R.id.Register_textView: openRegistrationActivity();
                                    break;
        }
    }

    public void openRegistrationActivity()
    {
        startActivity(new Intent(this, RegisterPage.class));
    }

    public void openDashboardActivity()
    {
        startActivity(new Intent(this, MainMenu.class));
    }

    private void storeLocalData(User user)
    {
        LocalDataStorage data = new LocalDataStorage(this);
        data.setUserLoggedInStatus(true);
        data.storeUserData(user);
    }

    private void validateLoginDetails()
    {
        username_et = findViewById(R.id.Username_editText);
        password_et = findViewById(R.id.Password_editText);

        String username = username_et.getText().toString();
        final String password = password_et.getText().toString();

        if(username.isEmpty() || password.isEmpty())
        {
            error_tw = findViewById(R.id.Confirmation_textView);
            error_tw.setText("Login Failed: EMPTY FIELDS");
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL + "/" + username , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                Gson gson = new Gson();
                User user = gson.fromJson(response.toString(), User.class);

                if(user.getSalt().equals("salt"))
                {
                    if(user.getPassword() == password.hashCode())
                    {
                        storeLocalData(user);
                        openDashboardActivity();
                        finish();
                    }

                    else
                    {
                        error_tw = findViewById(R.id.Confirmation_textView);
                        error_tw.setText("Login Failed: INVALID PASSWORD");
                    }
                }

                else
                {
                    error_tw = findViewById(R.id.Confirmation_textView);
                    error_tw.setText("Login Failed");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error_tw = findViewById(R.id.Confirmation_textView);
                error_tw.setText("Login Failed: USER DOESN'T EXIST");
            }
        });

        queue.add(request);
    }
}
