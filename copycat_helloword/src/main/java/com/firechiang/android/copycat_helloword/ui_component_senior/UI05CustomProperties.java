package com.firechiang.android.copycat_helloword.ui_component_senior;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firechiang.android.copycat_helloword.R;

/**
 * 测试自定义视图属性的Activity
 */
public class UI05CustomProperties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.ui_comsenior05_custom_properties);
    }
}
