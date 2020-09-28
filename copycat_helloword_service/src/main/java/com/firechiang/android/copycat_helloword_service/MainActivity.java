package com.firechiang.android.copycat_helloword_service;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 这个应用作为Service（服务端）提供接口供其他APP使用
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
