package com.firechiang.android.copycat_helloword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.firechiang.android.copycat_helloword.layout.Layout01Frame;
import com.firechiang.android.copycat_helloword.layout.Layout01Linear;
import com.firechiang.android.copycat_helloword.layout.Layout01Relative;

/**
 * 布局相关使用
 */
public class Layout01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.layout01);
    }

    /**
     * 跳转到线性布局示列
     * @param v
     */
    public void toLinearLayoutView(View v) {
        startActivity(new Intent(this, Layout01Linear.class));
    }

    /**
     * 跳转到相对布局示列
     * @param v
     */
    public void toRelativeLayoutView(View v) {
        startActivity(new Intent(this, Layout01Relative.class));
    }

    /**
     * 跳转到帧布局示列
     * @param v
     */
    public void toFrameLayoutView(View v) {
        startActivity(new Intent(this, Layout01Frame.class));
    }

}
