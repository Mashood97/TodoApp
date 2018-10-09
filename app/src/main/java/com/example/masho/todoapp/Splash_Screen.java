package com.example.masho.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(3000);
                    Intent i = new Intent(Splash_Screen.this,LoginScreen.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
