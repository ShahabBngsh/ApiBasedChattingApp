package com.shahab.i180731_i180650;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                counter++;

            }

            @Override
            public void onFinish() {
//                NOTE:ADD login page on "LoginPageActivity.class"
                Intent StartIntent = new Intent(MainActivity.this, CallingActivity.class);
                startActivity(StartIntent);
            }
        }.start();
    }

}