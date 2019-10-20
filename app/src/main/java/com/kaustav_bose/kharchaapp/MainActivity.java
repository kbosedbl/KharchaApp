package com.kaustav_bose.kharchaapp;

//package com.kaustav_bose.kharcha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static int timer=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sharedPreferences=getSharedPreferences("user_data",MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                    startActivity(new Intent(MainActivity.this,Dashboard.class));
                    finish();

            }
        },timer);
    }
}