package com.firechiang.android.copycat_helloword.thread_message;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 使用Handler消息机制实现对数值的手动和自动增加（注意：这里包含消息移除和禁用Button）
 */
public class ThreadMessageMainActivity02 extends Activity {

    // 手动增加
    private static final int WHAT_MANUAL_ADD = 1;
    // 手动减少
    private static final int WHAT_MANUAL_REDUCTION = 2;
    // 自动增加
    private static final int WHAT_AUTO_ADD = 3;
    // 自动减少
    private static final int WHAT_AUTO_REDUCTION = 4;


    private TextView textView;

    private Button button01;

    private Button button02;

    /**
     * 消息处理器
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            int what = msg.what;
            Integer value = Integer.parseInt(textView.getText().toString());
            // 手动增加
            if(WHAT_MANUAL_ADD == what) {
                textView.setText((++value).toString());
            }
            // 手动减少
            if(WHAT_MANUAL_REDUCTION == what) {
                textView.setText((--value).toString());
            }
            // 自动增加
            if(WHAT_AUTO_ADD == what) {
                textView.setText((++value).toString());
                // 移除自动减少的延迟消息
                this.removeMessages(WHAT_AUTO_REDUCTION);
                // 删除所有未处理的消息
                //handler.removeCallbacksAndMessages(null);
                // 再发送一个延迟执行的消息以达到自动增加的效果
                this.sendEmptyMessageDelayed(WHAT_AUTO_ADD,1000);
            }
            // 自动减少
            if(WHAT_AUTO_REDUCTION == what) {
                textView.setText((--value).toString());
                // 移除自动增加的延迟消息
                this.removeMessages(WHAT_AUTO_ADD);
                // 删除所有未处理的消息
                //handler.removeCallbacksAndMessages(null);
                // 再发送一个延迟执行的消息以达到自动减少的效果
                this.sendEmptyMessageDelayed(WHAT_AUTO_REDUCTION,1000);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_message_main_activity_02);

        this.button01 = findViewById(R.id.thread_message_main_activity_02_button01);
        this.button02 = findViewById(R.id.thread_message_main_activity_02_button02);
        this.textView = findViewById(R.id.thread_message_main_activity_02_textview);
    }

    /**
     * 手动增加
     * @param view
     */
    public void manualAdd(View view) {
        handler.sendEmptyMessage(WHAT_MANUAL_ADD);
    }

    /**
     * 手动减少
     * @param view
     */
    public void manualReduction(View view) {
        handler.sendEmptyMessage(WHAT_MANUAL_REDUCTION);
    }

    /**
     * 自动增加
     * @param view
     */
    public void autoAdd(View view) {
        // 禁用视图
        this.button01.setEnabled(false);
        this.button02.setEnabled(false);
        // 发送自动增加的消息
        handler.sendEmptyMessage(WHAT_AUTO_ADD);
    }

    /**
     * 自动减少
     * @param view
     */
    public void autoReduction(View view) {
        // 禁用视图
        this.button01.setEnabled(false);
        this.button02.setEnabled(false);
        // 发送自动减少的消息
        handler.sendEmptyMessage(WHAT_AUTO_REDUCTION);
    }

    /**
     * 停止自动增加或减少
     * @param view
     */
    public void stop(View view) {
        // 删除自动增加的消息
        handler.removeMessages(WHAT_AUTO_ADD);
        // 删除所有未处理的消息
        //handler.removeCallbacksAndMessages(null);
        // 删除自动减少的消息
        handler.removeMessages(WHAT_AUTO_REDUCTION);
        button01.setEnabled(true);
        button02.setEnabled(true);
    }
}
