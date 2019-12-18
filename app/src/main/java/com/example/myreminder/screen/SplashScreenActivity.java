package com.example.myreminder.screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myreminder.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread ssThread = new Thread(){
            public void run(){
                try{
                    sleep(3*1000);
                    Intent i = new Intent(getBaseContext(), PasscodeActivity.class);
                    startActivity(i);
                    finish();
                }catch (Exception e){
                }
            }
        };
        ssThread.start();
    }
}
