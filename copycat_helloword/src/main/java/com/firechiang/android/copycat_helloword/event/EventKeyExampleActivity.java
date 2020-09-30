package com.firechiang.android.copycat_helloword.event;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 键盘事件简单使用（注意：不是监听软键盘而是实体键盘事件）
 */
public class EventKeyExampleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_key_example_activity);
    }

    /**
     * 是否要退出的标识
     */
    private boolean exitFlag = false;

    private int whatFlag = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == whatFlag) {
                exitFlag = false;
            }
            super.handleMessage(msg);
        }
    };

    /**
     * 2秒内连续点击两次退出
     * @param view
     */
    public void click2Exit(View view) {
        if(!exitFlag) {
            exitFlag = true;
            Toast.makeText(this,"再点一下退出应用",Toast.LENGTH_SHORT).show();
            // 发送一个延迟2秒执行的消息
            handler.sendEmptyMessageDelayed(whatFlag,2000);
        } else {
            // 退出
            finish();
        }
    }

    /**
     * 事件分发（注意：它默认是将事件分发给父视图，不会分发给子视图，要分发给子视图需要手动分发）
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    /**
     * 键盘按下
     * 返回false表示只是监听，下一次的动作不会再回调，而是交给父视图或Activity的onKeyUp()函数去处理。返回true表示处理该事件，下一次的动作还会继续回调，而父视图或Activity的onKeyUp()函数不会被调用
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i("TAG","keyCode="+event.getKeyCode()+",action="+event.getAction()+",键按下");
        return true;
        //return super.onKeyUp(keyCode, event);
    }
}
