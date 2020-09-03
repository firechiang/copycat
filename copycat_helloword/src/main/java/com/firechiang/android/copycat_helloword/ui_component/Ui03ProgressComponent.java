package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.firechiang.android.copycat_helloword.R;

/**
 * 进度条组件和组件显示隐藏简单使用（Progressbar（简单进度条）,SeekBar（可滑动进度条））
 */
public class Ui03ProgressComponent extends Activity {

    private LinearLayout linearLayout;

    private ProgressBar progressBar01;

    private SeekBar seekBar01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.ui03_progress_component);
        this.linearLayout = findViewById(R.id.ui03ProgressComponentLinearLayout01);
        this.progressBar01 = findViewById(R.id.ui03ProgressComponentProgressBar01);
        this.seekBar01 = findViewById(R.id.ui03ProgressComponentSeekBar01);

        /**
         * 可拖动进度条相关事件
         */
        this.seekBar01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 进度条移动事件
             * @param seekBar
             * @param progress
             * @param fromUser
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            /**
             * 按下进度条事件
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * 离开进度条事件
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 当前进度
                int progress = seekBar.getProgress();
                // 将滑动进度条的值设置给不能滑动的进度条
                Ui03ProgressComponent.this.progressBar01.setProgress(progress);
                // 进度条拉到了最大值
                if(progress == Ui03ProgressComponent.this.seekBar01.getMax()) {
                    /**
                     * View.INVISIBLE (隐藏但还占空间)
                     * View.GONE (隐藏但不占空间)
                     *
                     */
                    Ui03ProgressComponent.this.linearLayout.setVisibility(View.GONE);
                }else{
                    // 显示组件
                    Ui03ProgressComponent.this.linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
