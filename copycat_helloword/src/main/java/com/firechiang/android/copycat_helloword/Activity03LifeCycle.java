package com.firechiang.android.copycat_helloword;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Avtivity生命周期相关以及4种状态
 * 运行状态：可见可操作
 * 暂停状态：可见不可操作（一般都是被Model层遮住了）
 * 停止状态：不可见，但对象还在
 * 死亡状态：不可见，对象不存在
 */
public class Activity03LifeCycle extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.activity_main);
        Log.e("info","Activity创建了");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("info","Activity启动了");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("info","Activity运行了");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("info","Activity暂停了");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("info","Activity停止了");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("info","Activity销毁了");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("info","Activity重新启动了");
    }
}
