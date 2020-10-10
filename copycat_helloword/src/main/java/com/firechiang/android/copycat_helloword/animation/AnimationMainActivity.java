package com.firechiang.android.copycat_helloword.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 动画相关
 */
public class AnimationMainActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_main_activity);
        this.imageView = findViewById(R.id.animation_main_activity_image);
    }

    /**
     * 代码方式实现Scale（缩放）动画
     * @param view
     */
    public void scaleCode(View view) {
        /**
         * 创建缩放动画（宽度从0.5到1，高度从0.0到1.0）
         * @param fromX       开始时X轴上的缩放比例
         * @param toX         结束时X轴上的缩放比例
         * @param fromY       开始时Y轴上的缩放比例
         * @param toY         结束时Y轴上的缩放比例
         * @param pivotXType  X轴坐标的相对类型Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图）
         * @param pivotXValue 中心点和相对视图在X轴上的偏移量
         * @param pivotYType  Y轴坐标的相对类型 Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图）
         * @param pivotYValue 中心点和相对视图在Y轴上的偏移量
         */
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1f, 0.0f,1f,Animation.ABSOLUTE,imageView.getWidth() / 2, Animation.ABSOLUTE,imageView.getHeight() / 2);
        // X轴坐标的相对类型使用Animation.RELATIVE_TO_SELF，中心点和相对视图在X轴上的偏移量是0.5说明是绕自己的中心缩放
        //ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.5f, 0.0f,1f,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);

        // 延迟1秒执行
        scaleAnimation.setStartOffset(1000);
        // 持续2秒
        scaleAnimation.setDuration(2000);
        // 最终停止时是否固定在起始状态
        scaleAnimation.setFillBefore(true);
        // 最终停止时是否固定在最后状态
        //scaleAnimation.setFillAfter(true);
        // 启动动画
        imageView.startAnimation(scaleAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * Xml方式实现Scale（缩放）动画
     * @param view
     */
    public void scaleXml(View view) {
        // 加载动画配置文件
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_test);
        imageView.setAnimation(scaleAnimation);
        // 启动动画
        imageView.startAnimation(scaleAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }
}
