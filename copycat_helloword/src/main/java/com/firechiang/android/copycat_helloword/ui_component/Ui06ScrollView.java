package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 可滚动或带滚动条的视图
 */
public class Ui06ScrollView extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui06_scrollview);
    }
}
