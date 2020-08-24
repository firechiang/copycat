package com.firechiang.android.copycat_helloword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * 主界面创建的时候回调
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.activity_main);
    }
}
