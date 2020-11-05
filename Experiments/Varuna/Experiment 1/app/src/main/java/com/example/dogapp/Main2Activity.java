package com.example.dogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txt = findViewById(R.id.textView2);

        Intent incomingIntent = getIntent();
        boolean FN = incomingIntent.getBooleanExtra("name", false);

        Switch fullName = findViewById(R.id.switch2);
        Button prevButton = findViewById(R.id.prevButton);

        fullName.setChecked(FN);

        if (fullName.isChecked())
            txt.setText("Helga the Husky");

        else
            txt.setText("Helga");

        fullName.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Switch fullName = findViewById(R.id.switch2);
        TextView txt = findViewById(R.id.textView2);

        if (v.getId() == R.id.prevButton)
        {
            Intent intent = new Intent();
            intent.putExtra("keyName", fullName.isChecked());
            setResult(RESULT_OK, intent);
            super.finish();
        }

        else
        {
            if (fullName.isChecked())
                txt.setText("Helga the Husky");

            else
                txt.setText("Helga");
        }
    }
}
