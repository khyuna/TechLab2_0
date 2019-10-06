package com.example.hyuna.techlab20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_login);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //현재는 무조건 MainActivity로 넘어가게 됨.
            case R.id.image_google_btn:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            //X표시 누르면 앱 종료
            //LoginActivity.this.finish();
        }
    }
}
