package com.firechiang.android.copycat_helloword.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 音乐播放简单使用
 */
public class MediaAudioActivity extends Activity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_audio_activity);
    }

    /**
     * 播放
     * @param view
     */
    public void play(View view) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.laugh_storm);
        }
        // 播放
        mediaPlayer.start();
    }

    /**
     * 停止
     * @param view
     */
    public void stop(View view){
        if (mediaPlayer != null){
            mediaPlayer.stop();
            // 重头开始
            mediaPlayer.reset();
            // 释放加载的音乐资源
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    /**
     * 暂停
     * @param view
     */
    public void pause(View view) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    /**
     * 退出
     * @param view
     */
    public void exit(View view) {
        // 停止播放
        stop(view);
        // 关闭Activity
        finish();
    }


}
