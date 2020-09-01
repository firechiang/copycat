package com.firechiang.android.copycat_helloword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试点击按钮弹出Dialog(注意：Dialog Activity在AndroidManifest.xml配置文件里面是需要配置样式的，详情请查看AndroidManifest.xml文件)
 */
public class Activity04Dialog01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity04_dialog01);
    }

    /**
     * 启动界面二按钮点击事件回调
     * @param view
     */
    public void onClick(View view) {
        // 启动另一个Activity
        this.startActivity(new Intent(this, Activity04Dialog02.class));
    }
}
