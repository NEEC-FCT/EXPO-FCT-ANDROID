package com.neec.expo.fct;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Loading extends AppCompatActivity {

    private static int LOGO_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // Intent homeIntent = new Intent(Loading.this, MainActivity.class);
                Intent homeIntent = new Intent(Loading.this, FirstPageActivity.class);
                startActivity(homeIntent);
                finish();
            }


        }, LOGO_TIME_OUT);
    }
}