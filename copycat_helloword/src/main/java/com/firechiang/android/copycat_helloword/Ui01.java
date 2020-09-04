package com.firechiang.android.copycat_helloword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.firechiang.android.copycat_helloword.ui_component.Ui01SimpleComponent;
import com.firechiang.android.copycat_helloword.ui_component.Ui02MenuComponent;
import com.firechiang.android.copycat_helloword.ui_component.Ui03ProgressComponent;
import com.firechiang.android.copycat_helloword.ui_component.Ui04DialogComponent;

/**
 * UI组件
 */
public class Ui01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.ui01);
    }

    /**
     * 测试常用简单的组件
     * @param view
     */
    public void simpleComponent(View view) {
        startActivity(new Intent(this, Ui01SimpleComponent.class));
    }

    /**
     * 测试菜单组件
     * @param view
     */
    public void menuComponent(View view) {
        startActivity(new Intent(this, Ui02MenuComponent.class));
    }

    /**
     * 测试进度条组件
     * @param view
     */
    public void progressComponent(View view) {
        startActivity(new Intent(this, Ui03ProgressComponent.class));
    }

    /**
     * 测试弹出框组件
     * @param view
     */
    public void dialogComponent(View view) {
        startActivity(new Intent(this, Ui04DialogComponent.class));
    }
}
