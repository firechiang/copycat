package com.firechiang.android.copycat_helloword.animation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 动画相关简单使用
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
         * @param pivotXType  X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param pivotXValue 中心点和相对视图在X轴上的偏移量
         * @param pivotYType  Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
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
        /**
         * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
         * new AccelerateInterpolator（加速执行动画）
         * new DecelerateInterpolator（减速执行动画）
         * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
         * new CycleInterpolator（周期循环变化）
         */
        //scaleAnimation.setInterpolator(new LinearInterpolator());
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        //scaleAnimation.setRepeatCount(2);
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
        // 设置动画（注意:这个可以不用）
        //imageView.setAnimation(scaleAnimation);
        // 启动动画
        imageView.startAnimation(scaleAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * 代码方式实现Rotate（旋转）动画
     * @param view
     */
    public void rotateCode(View view) {
        /**
         * @param fromDegrees 从哪个角度开始旋转
         * @param toDegrees   旋转到哪个角度结束
         * @param pivotXType  X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param pivotXValue 中心点和相对视图在X轴上的偏移量
         * @param pivotYType  Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
         * @param pivotYValue 中心点和相对视图在Y轴上的偏移量
         */
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.ABSOLUTE,imageView.getWidth() / 2, Animation.ABSOLUTE,imageView.getHeight() / 2);
        // X轴坐标的相对类型使用Animation.RELATIVE_TO_SELF，中心点和相对视图在X轴上的偏移量是0.5说明是绕自己的中心旋转
        //RotateAnimation rotateAnimation = new RotateAnimation(-90,90,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        // 延迟1秒执行
        rotateAnimation.setStartOffset(1000);
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        //rotateAnimation.setRepeatCount(2);
        // 持续2秒
        rotateAnimation.setDuration(2000);
        /**
         * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
         * new AccelerateInterpolator（加速执行动画）
         * new DecelerateInterpolator（减速执行动画）
         * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
         * new CycleInterpolator（周期循环变化）
         */
        //rotateAnimation.setInterpolator(new LinearInterpolator());
        // 最终停止时是否固定在起始状态
        //rotateAnimation.setFillBefore(true);
        // 最终停止时是否固定在最后状态
        //rotateAnimation.setFillAfter(true);
        // 启动动画
        imageView.startAnimation(rotateAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * XML方式实现Rotate（旋转）动画
     * @param view
     */
    public void rotateXml(View view) {
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_test);
        // 启动动画
        imageView.startAnimation(rotateAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * 代码方式实现Alpha（透明）动画
     * @param view
     */
    public void alphaCode(View view) {
        /**
         * 实现从完全透明到完全不透明
         * @param fromAlpha 动画开始时的透明度（0 表示完全透明）
         * @param toAlpha   动画结束时的透明度（1 表示不透明）
         */
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        // 延迟1秒执行
        alphaAnimation.setStartOffset(1000);
        // 持续2秒
        alphaAnimation.setDuration(2000);
        /**
         * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
         * new AccelerateInterpolator（加速执行动画）
         * new DecelerateInterpolator（减速执行动画）
         * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
         * new CycleInterpolator（周期循环变化）
         */
        //alphaAnimation.setInterpolator(new LinearInterpolator());
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        //alphaAnimation.setRepeatCount(2);
        // 最终停止时是否固定在起始状态
        //alphaAnimation.setFillBefore(true);
        // 最终停止时是否固定在最后状态
        //alphaAnimation.setFillAfter(true);
        // 启动动画
        imageView.startAnimation(alphaAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * XML方式实现Alpha（透明）动画
     * @param view
     */
    public void alphaXml(View view) {
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_test);
        // 启动动画
        imageView.startAnimation(alphaAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * 代码方式实现Translation（平衡）动画
     * @param view
     */
    public void translationCode(View view) {
        /**
         * 实现向右移动一个自己的宽度，向下移动一个自己的高度
         * @param fromXType  移动前X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param fromXValue 移动前的中心点和相对视图在X轴上的偏移量（就是移动前X轴的位置）
         * @param toXType    移动完成后X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param toXValue   移动完成后的中心点和相对视图在X轴上的偏移量（就是最终X轴的位置）
         * @param fromYType  移动前Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
         * @param fromYValue 移动前的中心点和相对视图在Y轴上的偏移量（就是移动前Y轴的位置）
         * @param toYType    移动完成后Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
         * @param toYValue   移动完成后的中心点和相对视图在Y轴上的偏移量（就是最终Y轴的位置）
         */
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF, 1,Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF,1);
        // 延迟1秒执行
        translateAnimation.setStartOffset(1000);
        // 持续2秒
        translateAnimation.setDuration(2000);
        /**
         * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
         * new AccelerateInterpolator（加速执行动画）
         * new DecelerateInterpolator（减速执行动画）
         * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
         * new CycleInterpolator（周期循环变化）
         */
        //translateAnimation.setInterpolator(new LinearInterpolator());
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        //translateAnimation.setRepeatCount(2);
        // 最终停止时是否固定在起始状态
        //translateAnimation.setFillBefore(true);
        // 最终停止时是否固定在最后状态
        //translateAnimation.setFillAfter(true);
        // 开始动画
        imageView.startAnimation(translateAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * XML方式实现Translation（平衡）动画
     * @param view
     */
    public void translationXml(View view) {
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_test);
        // 开始动画
        imageView.startAnimation(translateAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * 代码方式实现组合动画（多种动画组合起来）
     * 效果：动画从透明到不透明，持续2秒，接着旋转360度，持续2秒
     * combination
     * @param view
     */
    public void combinationCode(View view) {
        /**
         * 动画从透明到不透明，持续2秒
         * @param fromAlpha 动画开始时的透明度（0 表示完全透明）
         * @param toAlpha   动画结束时的透明度（1 表示不透明）
         */
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        // 延迟1秒执行
        //alphaAnimation.setStartOffset(1000);
        // 持续2秒
        alphaAnimation.setDuration(2000);
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        //alphaAnimation.setRepeatCount(2);

        /**
         * 旋转动画，持续2秒
         * @param fromDegrees 从哪个角度开始旋转
         * @param toDegrees   旋转到哪个角度结束
         * @param pivotXType  X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param pivotXValue 中心点和相对视图在X轴上的偏移量
         * @param pivotYType  Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
         * @param pivotYValue 中心点和相对视图在Y轴上的偏移量
         */
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.ABSOLUTE,imageView.getWidth() / 2, Animation.ABSOLUTE,imageView.getHeight() / 2);
        // 延迟1秒执行
        //rotateAnimation.setStartOffset(1000);
        // 持续2秒
        rotateAnimation.setDuration(2000);
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        //rotateAnimation.setRepeatCount(2);
        /**
         * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
         * new AccelerateInterpolator（加速执行动画）
         * new DecelerateInterpolator（减速执行动画）
         * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
         * new CycleInterpolator（周期循环变化）
         */
        //rotateAnimation.setInterpolator(new LinearInterpolator());
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        // 启动动画
        imageView.startAnimation(animationSet);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * XML方式实现组合动画（多种动画组合起来）
     * @param view
     */
    public void combinationXml(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_test);
        // 开始动画
        imageView.startAnimation(animation);
        // 清除动画
        //imageView.clearAnimation();
    }

    /**
     * 测试动画监听（以旋转动画为列）
     * @param view
     */
    public void listenerAnimation(View view) {
        /**
         * @param fromDegrees 从哪个角度开始旋转
         * @param toDegrees   旋转到哪个角度结束
         * @param pivotXType  X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
         * @param pivotXValue 中心点和相对视图在X轴上的偏移量
         * @param pivotYType  Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
         * @param pivotYValue 中心点和相对视图在Y轴上的偏移量
         */
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.ABSOLUTE,imageView.getWidth() / 2, Animation.ABSOLUTE,imageView.getHeight() / 2);
        // X轴坐标的相对类型使用Animation.RELATIVE_TO_SELF，中心点和相对视图在X轴上的偏移量是0.5说明是绕自己的中心旋转
        //RotateAnimation rotateAnimation = new RotateAnimation(-90,90,Animation.ABSOLUTE,0.5f, Animation.ABSOLUTE,0.5f);
        // 延迟1秒执行
        //rotateAnimation.setStartOffset(1000);
        // 持续2秒
        rotateAnimation.setDuration(2000);
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        //rotateAnimation.setRepeatCount(1);
        /**
         * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
         * new AccelerateInterpolator（加速执行动画）
         * new DecelerateInterpolator（减速执行动画）
         * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
         * new CycleInterpolator（周期循环变化）
         */
        //rotateAnimation.setInterpolator(new LinearInterpolator());
        // 最终停止时是否固定在起始状态
        //rotateAnimation.setFillBefore(true);
        // 最终停止时是否固定在最后状态
        //rotateAnimation.setFillAfter(true);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener(){

            /**
             * 动画开始回调
             * @param animation
             */
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("TAG","旋转动画开始执行");
            }
            /**
             * 动画结束回调
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("TAG","旋转动画结束执行");
            }
            /**
             * 动画重复执行回调
             * @param animation
             */
            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("TAG","旋转动画重复执行");
            }
        });

        // 启动动画
        imageView.startAnimation(rotateAnimation);
        // 清除动画
        //imageView.clearAnimation();
    }

}
