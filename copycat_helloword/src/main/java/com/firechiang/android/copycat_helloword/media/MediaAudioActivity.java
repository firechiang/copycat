package com.firechiang.android.copycat_helloword.media;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 音乐播放简单使用（注意：音乐播放不要直接在Activity里面播放，因为Activity关闭后，音乐会在后台播放，音乐一旦到了后台，Activity就控制不了）
 * 注意：所有的Service都需要在AndroidManifest.xml文件中配置，否则无法使用（跟Servlet一样都需要配置）
 */
public class MediaAudioActivity extends Activity {

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
        Intent intent = new Intent(this,AudioService.class);
        intent.putExtra("action","play");
        startService(intent);
    }

    /**
     * 停止
     * @param view
     */
    public void stop(View view){
        Intent intent = new Intent(this,AudioService.class);
        intent.putExtra("action","stop");
        startService(intent);
    }

    /**
     * 暂停
     * @param view
     */
    public void pause(View view) {
        Intent intent = new Intent(this,AudioService.class);
        intent.putExtra("action","pause");
        startService(intent);
    }

    /**
     * 退出
     * @param view
     */
    public void exit(View view) {
        Intent intent = new Intent(this,AudioService.class);
        // 停止音乐播放服务
        stopService(intent);
        // 关闭Activity
        finish();
    }

    /**
     * 播放音乐的 Service（服务）
     * 注意：所有的Service都需要在AndroidManifest.xml文件中配置，否则无法使用（跟Servlet一样都需要配置）
     */
    public static class AudioService extends Service {

        private MediaPlayer mediaPlayer;

        @Override
        public void onCreate() {
            Log.i("TAG",AudioService.class.getName()+" 音乐播放Service（服务）创建成功");
            super.onCreate();
        }

        /**
         * 只有调用其它App的Service（服务）才需要绑定（我们调用的是自己所以就不需要实现这个函数）
         * @param intent
         * @return
         */
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        /**
         * Activity调用startService()函数的回调函数（也就是每调用一次startService()函数就会调用一次这个函数）
         * @param intent
         * @param flags
         * @param startId
         * @return
         */
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            String action = intent.getStringExtra("action");
            // 播放
            if("play".equals(action)) {
                Log.i("TAG",AudioService.class.getName()+" 播放音乐");
                if(mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.laugh_storm);
                }
                // 播放
                mediaPlayer.start();
            }
            // 暂停
            if("pause".equals(action)) {
                Log.i("TAG",AudioService.class.getName()+" 暂停播放");
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
            // 停止
            if("stop".equals(action)) {
                Log.i("TAG",AudioService.class.getName()+" 停止播放");
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    // 重头开始
                    mediaPlayer.reset();
                    // 释放加载的音乐资源
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
            return super.onStartCommand(intent, flags, startId);
        }

        /**
         * 停止服务回调
         */
        @Override
        public void onDestroy() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                // 重头开始
                mediaPlayer.reset();
                // 释放加载的音乐资源
                mediaPlayer.release();
                mediaPlayer = null;
            }
            super.onDestroy();
        }
    }
}
