package com.firechiang.android.copycat_helloword;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Avtivity4种状态
 * 运行状态：可见可操作
 * 暂停状态：可见不可操作（一般都是被Model层遮住了）
 * 停止状态：不可见，但对象还在
 * 死亡状态：不可见，对象不存在
 */
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

    /**
     * 界面启动时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 界面停止时调用
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 界面销毁时调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 横竖屏切换时调用
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 切换到横屏
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }
        // 切换到竖屏
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
        // 手动切换到横屏
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 手动切换到竖屏
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
