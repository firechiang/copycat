package com.firechiang.android.copycat_helloword.animation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 连续动画简单使用（自定义绘制动画的效果，可简单实现GIF）
 */
public class AnimationContinuousActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_continuous_activity);

        this.imageView = findViewById(R.id.animation_continuous_activity_image);
    }

    /**
     * 启动连续动画
     * @param view
     */
    public void startAnimation(View view) {
        // 获取背景（注意：因为我们在Xml文件里面配置的背景就是一个连续的动画所以可以强转成 AnimationDrawable（连续动画））
        AnimationDrawable animationDrawable = (AnimationDrawable)this.imageView.getBackground();
        // 启动连续动画
        animationDrawable.start();
    }

    /**
     * 停止连续动画
     * @param view
     */
    public void stopAnimation(View view) {
        // 获取背景（注意：因为我们在Xml文件里面配置的背景就是一个连续的动画所以可以强转成 AnimationDrawable（连续动画））
        AnimationDrawable animationDrawable = (AnimationDrawable)this.imageView.getBackground();
        // 停止连续动画
        animationDrawable.stop();
    }
}
