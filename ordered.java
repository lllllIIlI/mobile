package com.inhatc.project_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class ordered extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                finish();
            }
        }, 5000);// 0.6초 정도 딜레이를 준 후 시작
    }
}
