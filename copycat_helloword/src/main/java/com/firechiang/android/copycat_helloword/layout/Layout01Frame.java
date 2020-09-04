package com.firechiang.android.copycat_helloword.layout;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * FrameLayout（帧布局，所有的组件会叠在一起）
 */
public class Layout01Frame extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout01_frame);
    }
}
