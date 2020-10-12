package com.firechiang.android.copycat_helloword.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * App杀毒界面
 */
public class App01AntiActivity extends Activity {

    // 进度条
    private ProgressBar progressBar;
    // 扫描图片
    private ImageView imageView;
    // 扫描文字
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app01_anti_activity);
        // 获取相关视图
        this.progressBar = findViewById(R.id.app01_anti_activity_progressbar);
        this.imageView = findViewById(R.id.app01_anti_activity_radar_stick);
        this.textView = findViewById(R.id.app01_anti_activity_textview);
        // 显示扫描动画
        showScanAnimation();
        // 设置进度
        setProgressBar();
    }

    /**
     * 设置进度
     */
    private void setProgressBar() {
        AsyncTask<Void, Integer, Void> task = new AsyncTask<Void, Integer, Void>() {
            /**
             * 进度条开始时调用（注意：这个函数在主线程执行）
             */
            @Override
            protected void onPreExecute() {
                textView.setText("手机杀毒正在扫描");
            }

            /**
             * 不断的设置进度（注意：这个函数在子线程执行）
             * @param voids
             * @return
             */
            @Override
            protected Void doInBackground(Void... voids) {
                int appCount = 60;
                // 设置进度条最大值
                progressBar.setMax(appCount);
                for(int i=0;i<appCount;i++) {
                    // 睡40毫秒
                    SystemClock.sleep(40);
                    // 更新进度（注意：调用这个函数后Task会自动调用下面的 onProgressUpdate() 函数）
                    publishProgress();
                }
                return null;
            }

            /**
             * 更新进度（注意：这个函数在主线程执行）
             * @param values
             */
            @Override
            protected void onProgressUpdate(Integer... values) {
                // 增加进度条的值
                progressBar.incrementProgressBy(1);
                // 和上面的同理
                //progressBar.setProgress(progressBar.getProgress() + 1);
            }

            /**
             * 进度完成时调用（注意：这个函数在主线程执行）
             * @param aVoid
             */
            @Override
            protected void onPostExecute(Void aVoid) {
                // 隐藏进度条
                progressBar.setVisibility(View.GONE);
                textView.setText("扫描完成，未发现病毒");
                // 停止动画
                imageView.clearAnimation();
            }
        };
        // 执行任务
        task.execute();
    }


    /**
     * 显示扫描动画
     */
    private void showScanAnimation(){
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
        // 动画重复执行次数（Animation.INFINITE（无限循环执行））
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        // 持续2秒
        rotateAnimation.setDuration(2000);
        /**
         * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
         * new AccelerateInterpolator（加速执行动画）
         * new DecelerateInterpolator（减速执行动画）
         * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
         * new CycleInterpolator（周期循环变化）
         */
        rotateAnimation.setInterpolator(new LinearInterpolator());
        // 最终停止时是否固定在起始状态
        //rotateAnimation.setFillBefore(true);
        // 最终停止时是否固定在最后状态
        //rotateAnimation.setFillAfter(true);
        // 启动动画
        imageView.startAnimation(rotateAnimation);
        // 停止动画
        //imageView.clearAnimation();
    }
}
