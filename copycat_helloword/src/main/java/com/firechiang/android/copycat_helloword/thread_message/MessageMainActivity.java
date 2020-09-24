package com.firechiang.android.copycat_helloword.thread_message;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 消息机制（线程通信）相关主Activity
 */
public class MessageMainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_message_main_activity);
    }


    /**
     * 线程间消息处理Handler简单使用
     * @param view
     */
    public void testHandler(View view) {

    }
}
