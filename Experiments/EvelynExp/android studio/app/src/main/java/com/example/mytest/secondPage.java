package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class secondPage extends AppCompatActivity {

    private Button pgbutton;
    private Intent pgintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        pgbutton = findViewById(R.id.button);
        pgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openSecondPage(); }
        });

    }

    public void openSecondPage(){
        pgintent = new Intent(this, MainActivity.class);
        startActivity(pgintent);
    }
}
