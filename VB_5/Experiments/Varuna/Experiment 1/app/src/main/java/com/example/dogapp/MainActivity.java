package com.example.dogapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch fullName = findViewById(R.id.switch1);
        Button nextButton = findViewById(R.id.nextButton);

        fullName.setChecked(true);

        fullName.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Switch fullName = findViewById(R.id.switch1);
        TextView txt = findViewById(R.id.textView);

        if(v.getId() == R.id.nextButton)
            openNextActivity();

        else
        {
            if(fullName.isChecked())
                txt.setText("Brutus the Bulldog");

            else
                txt.setText("Brutus");
        }
    }

    public void openNextActivity()
    {
        Switch fullName = findViewById(R.id.switch1);
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("name", fullName.isChecked());
        startActivityForResult(intent, 0);
    }

    // This method is called when the second activity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        TextView txt = findViewById(R.id.textView);
        Switch fullName = findViewById(R.id.switch1);

        if (requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                boolean returnBool = data.getBooleanExtra("keyName", false);
                fullName.setChecked(returnBool);

                if(fullName.isChecked())
                    txt.setText("Brutus the Bulldog");

                else
                    txt.setText("Brutus");
            }
        }
    }
}
