package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run()
//            {
//                Intent in = new Intent(SplashScreen.this, RecyclerDemo.class);
//                startActivity(in);
//                finish();
//            }
//        };
//
//        handler.postDelayed(runnable, 3000);

        Thread splashThread = new Thread()
        {
            @Override
            public void run() {
                try
                {
                    sleep(3000);
                    Intent in = new Intent(SplashScreen.this, RecyclerDemo.class);
                    startActivity(in);
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                super.run();
            }
        };

        splashThread.start();
    }
}