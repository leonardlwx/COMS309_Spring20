package com.example.StudyBuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TimerV2 extends AppCompatActivity {

    private EditText userInput;
    private Button userButtonSet;

    private long userStartTime;

    private TextView textCountDown;

    private Button startPause;
    private Button resetButton;

    private long timeLeft;
    private boolean running;
    private CountDownTimer countDownTimer;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_v2);

        textCountDown = findViewById(R.id.textTimer);
        startPause = findViewById(R.id.buttonTimer);
        resetButton = findViewById(R.id.buttonReset);

        userInput = findViewById(R.id.timerSet);
        userButtonSet = findViewById(R.id.buttonSet);

        userButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = userInput.getText().toString();
                if (input.length() == 0){
                    Toast.makeText(TimerV2.this, "Please enter a time", Toast.LENGTH_SHORT).show();
                    return;
                }
                long mInput = Long.parseLong(input) * 60000;
                if (mInput < 0){
                    Toast.makeText(TimerV2.this, "Please enter positive time", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(mInput);
                userInput.setText("");
            }
        });


        startPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running){
                    pauseTimer();
                }
                else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

    }
    private void resetTimer() {
        timeLeft = userStartTime;;
        updateCountdown();
        updateButton();
    }

    private void startTimer() {
        endTime = System.currentTimeMillis() + timeLeft;
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountdown();
            }

            @Override
            public void onFinish() {
                running = false;
                updateButton();
            }
        }.start();

        running = true;
        updateButton();
    }

    private void updateCountdown() {
        int hours = (int) (timeLeft / 1000) / 3600;
        int minutes = (int) ((timeLeft / 1000) % 3600) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;
        String timeString;
        if (hours > 0){
            timeString = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, seconds);
        }
        else {
            timeString = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        }

        textCountDown.setText(timeString);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        running = false;
        updateButton();

    }

    private void setTime(long mSeconds){
        userStartTime = mSeconds;
        resetTimer();
    }

    private void updateButton(){
        if (running){
            userInput.setVisibility(View.INVISIBLE);
            userButtonSet.setVisibility(View.INVISIBLE);

            resetButton.setVisibility(View.INVISIBLE);
            startPause.setText("PAUSE");
        }
        else {
            userInput.setVisibility(View.VISIBLE);
            userButtonSet.setVisibility(View.VISIBLE);
            startPause.setText("START");
            if (timeLeft < 1000){
                startPause.setVisibility(View.INVISIBLE);
            }
            else {
                startPause.setVisibility(View.VISIBLE);
            }
            if (timeLeft < userStartTime){
                resetButton.setVisibility(View.VISIBLE);
            }
            else {
                resetButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("mStartTime", userStartTime);
        editor.putLong("timeLeft", timeLeft);
        editor.putBoolean("isRunning", running);
        editor.putLong("endingTime", endTime);
        editor.apply();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        userStartTime = prefs.getLong("mStartTime", 180000);
        timeLeft = prefs.getLong("timeLeft", userStartTime);
        running = prefs.getBoolean("isRunning", false);
        updateCountdown();
        updateButton();

        if (running){
            endTime = prefs.getLong("endingTime", 0);
            timeLeft = endTime - System.currentTimeMillis();

            if (timeLeft < 0){
                timeLeft = 0;
                running = false;
                updateCountdown();
                updateButton();
            }
            else {
                startTimer();
            }
        }

    }
    /**
     * Opens up Main Menu activity from Timer class
     */
    public void goBack() {
        startActivity(new Intent(this, MainMenu.class));
    }}