package com.firechiang.android.copycat_helloword.event;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 触摸事件拖动图片简单使用
 */
public class EventMotionDragImageActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_motion_drag_image_activity);
        // 获取到图片视图
        ImageView imageView = findViewById(R.id.event_motion_drag_image_activity_image);
        // 设置图片视图的触摸事件
        imageView.setOnTouchListener(new View.OnTouchListener() {
            /**
             * 最后触摸事件所在位置
             */
            private int lastX;

            private int lastY;

            /**
             * 返回false表示只是监听，下一次的动作不会再回调，而是交给父视图或Activity的onTouchEvent()函数去处理。返回true表示处理该事件，下一次的动作还会继续回调，而父视图或Activity的onTouchEvent()函数不会被调用
             */
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 获取当前触摸的位置
                int rawX = (int)event.getRawX();
                int rawY = (int)event.getRawY();
                Log.i("TAG","EventMotionDragImageActivity 正在移动 X="+rawX+",Y="+rawY);
                // 正在移动
                if(MotionEvent.ACTION_MOVE == event.getAction()) {
                    // 计算事件的偏移
                    int dx = rawX - lastX;
                    int dy = rawY - lastY;
                    int left = v.getLeft() + dx;
                    int top = v.getTop() + dy;
                    int right = v.getRight() + dx;
                    int bottom = v.getBottom() + dy;
                    /**
                     * 重新定位视图的位置（也就是启动视图）
                     * @param int left   距离左边的位置
                     * @param int top    距离上边的位置
                     * @param int right  距离右边的位置
                     * @param int bottom 距离下边的位置
                     */
                    v.layout(left,top,right,bottom);
                }
                // 最后触摸事件所在位置
                lastX = rawX;
                lastY = rawY;
                return true;
            }
        });
    }
}
