package com.firechiang.android.copycat_helloword.event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 触摸事件简单说明（注意：触摸事件包括3步：按下屏幕，移动，离开屏幕）
 */
public class EventMotionExampleActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_motion_example_activity);
        // 获取到图片视图
        MyImageView myImageView = findViewById(R.id.event_motion_example_activity_image);
        // 设置图片视图的触摸事件
        myImageView.setOnTouchListener(new View.OnTouchListener(){
            /**
             * 返回false表示只是监听，下一次的动作不会再回调，而是交给父视图或Activity的onTouchEvent()函数去处理。返回true表示处理该事件，下一次的动作还会继续回调，而父视图或Activity的onTouchEvent()函数不会被调用
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 动作名称
                String actionName = null;
                int action = event.getAction();
                if(MotionEvent.ACTION_DOWN == action) {
                    actionName = "屏幕按下";
                }
                if(MotionEvent.ACTION_MOVE == action) {
                    actionName = "移动";
                }
                if(MotionEvent.ACTION_UP == action) {
                    actionName = "离开屏幕";
                }
                Log.i("TAG","MyImageView图片视图的触摸事件已触发，正在"+actionName);
                return true;
            }
        });
    }

    /**
     * 分发事件（注意：它默认是将事件分发给父视图，不会分发给子视图，要分发给子视图需要手动分发）
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("TAG","EventMotionMainActivity 正在分发事件");
        return super.dispatchTouchEvent(event);
    }

    /**
     * 返回false表示只是监听，下一次的动作不会再回调，而是交给父视图或Activity的onTouchEvent()函数去处理。返回true表示处理该事件，下一次的动作还会继续回调，而父视图或Activity的onTouchEvent()函数不会被调用
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 动作名称
        String actionName = null;
        int action = event.getAction();
        if(MotionEvent.ACTION_DOWN == action) {
            actionName = "屏幕按下";
        }
        if(MotionEvent.ACTION_MOVE == action) {
            actionName = "移动";
        }
        if(MotionEvent.ACTION_UP == action) {
            actionName = "离开屏幕";
        }
        Log.i("TAG","EventMotionMainActivity的触摸事件已出发，正在"+actionName);
        return super.onTouchEvent(event);
    }
}
