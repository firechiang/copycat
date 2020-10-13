package com.firechiang.android.copycat_helloword.canvas;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * Selector+Shape或Selector+Drawable（就是类似于鼠标按下一个样式和鼠标抬起一个样式简单使用）
 */
public class GraphicalImageActivity04Selector extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_image_activity_04_selector);
    }
}
