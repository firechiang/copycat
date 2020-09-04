package com.firechiang.android.copycat_helloword.layout;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 线性布局简单使用
 */
public class Layout01Linear extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout01_linear);
    }
}
