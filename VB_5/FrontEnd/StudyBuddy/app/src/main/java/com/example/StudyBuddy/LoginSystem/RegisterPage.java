package com.example.StudyBuddy.LoginSystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.StudyBuddy.R;

import java.io.UnsupportedEncodingException;

//TODO FIX MAJOR BUG: REGISTERING WITH SAME USERNAME OVERWRITES THE OLD USERS DATA
public class RegisterPage extends AppCompatActivity implements View.OnClickListener
{
    static final String URL = "http://coms-309-vb-5.cs.iastate.edu:8080/users";

    Button register_b;
    TextView error_tw;
    TextView error2_tw;
    EditText username_et;
    EditText password_et;
    EditText confirm_password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        register_b = findViewById(R.id.register_Register_button);
        register_b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.register_Register_button: if(checkRegistration(view))
                                                    finish();
                                                break;
        }
    }

    private boolean checkRegistration (View view)
    {
        username_et = findViewById(R.id.register_Username_editText);
        String username = username_et.getText().toString();

        password_et = findViewById(R.id.register_Password_editText);
        final String password = password_et.getText().toString();

        confirm_password_et = findViewById(R.id.register_ConfirmPassword_editText);
        String confirmPassword = confirm_password_et.getText().toString();

        error_tw = findViewById(R.id.register_error_textView);
        error2_tw = findViewById(R.id.register_error2_textView);

        if (password.equals(confirmPassword))
        {
            error2_tw.setText("");
            error_tw.setText("");

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, URL + "/" + username + "/salt", new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {

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
                    try
                    {
                        return ("" + password.hashCode()).getBytes("utf-8");
                    }
                    catch (UnsupportedEncodingException e)
                    {
                        return null;
                    }
                }
            };

            queue.add(request);

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

            return true;
        }

        else
        {
            error2_tw.setText("Passwords don't match");
            return false;
        }
    }
}
