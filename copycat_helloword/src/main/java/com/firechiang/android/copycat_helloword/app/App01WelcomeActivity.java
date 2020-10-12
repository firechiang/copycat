package com.firechiang.android.copycat_helloword.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 动画示例App欢迎界面
 */
public class App01WelcomeActivity extends Activity {

    private RelativeLayout layoutView;
    // 跳转界面标识
    private int sendStart = 1;

    /**
     * 处理3秒后自动跳转至其它页面
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 跳转消息
            if(msg.what == sendStart) {
                App01WelcomeActivity.this.startActivity(new Intent(App01WelcomeActivity.this, App01BackActivity.class));
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app01_welcome_activity);
        this.layoutView = findViewById(R.id.app01_welcome_activity_layout);
        // 显示动画
        showAnimation();
        // 发送一个3秒以后执行的延迟消息
        handler.sendEmptyMessageDelayed(sendStart,2000);
    }

    /**
     * 显示动画
     */
    private void showAnimation() {
        /**
         * 缩放动画（宽度从0.5到1，高度从0.0到1.0）
         * X轴坐标的相对类型使用Animation.RELATIVE_TO_SELF，中心点和相对视图在X轴上的偏移量是0.5说明是绕自己的中心缩放
         * @param fromX       开始时X轴上的缩放比例
         * @param toX         结束时X轴上的缩放比例
         * @param fromY       开始时Y轴上的缩放比例
         * @param toY         结束时Y轴上的缩放比例
         * @param pivotXType  X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param pivotXValue 中心点和相对视图在X轴上的偏移量
         * @param pivotYType  Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
         * @param pivotYValue 中心点和相对视图在Y轴上的偏移量
         */
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1f, 0.0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        // 持续2秒
        scaleAnimation.setDuration(2000);
        /**
         * 旋转动画
         * X轴坐标的相对类型使用Animation.RELATIVE_TO_SELF，中心点和相对视图在X轴上的偏移量是0.5说明是绕自己的中心旋转
         * @param fromDegrees 从哪个角度开始旋转
         * @param toDegrees   旋转到哪个角度结束
         * @param pivotXType  X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param pivotXValue 中心点和相对视图在X轴上的偏移量
         * @param pivotYType  Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
         * @param pivotYValue 中心点和相对视图在Y轴上的偏移量
         */
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        // 持续2秒
        rotateAnimation.setDuration(2000);
        // 组合动画
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        // 启动动画
        layoutView.startAnimation(animationSet);
        // 清除动画
        //imageView.clearAnimation();
    }
}
