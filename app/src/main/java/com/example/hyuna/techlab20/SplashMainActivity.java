package com.example.hyuna.techlab20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashMainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            //4초동안 보여주기
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
