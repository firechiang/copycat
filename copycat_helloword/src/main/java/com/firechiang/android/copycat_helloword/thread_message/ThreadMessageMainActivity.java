package com.firechiang.android.copycat_helloword.thread_message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 消息机制（线程通信）以及AsyncTask（异步任务）相关主Activity
 */
public class ThreadMessageMainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_message_main_activity);
    }


    /**
     * 使用Handler消息机制和不使用Handler简单对比
     * @param view
     */
    public void useNotHandlerX(View view) {
        startActivity(new Intent(this,ThreadMessageMainActivity01.class));
    }

    /**
     * 使用Handler消息机制实现对数值的手动和自动增加
     * @param view
     */
    public void addAndReduction(View view) {
        startActivity(new Intent(this,ThreadMessageMainActivity02.class));
    }

    /**
     * AsyncTask 异步任务简单使用（以后刷新UI视图可以使用这个来做，因为它其实就是一个线程池）
     * @param view
     */
    public void asyncTask(View view) {
        startActivity(new Intent(this,ThreadMessageMainActivity03.class));
    }
}
