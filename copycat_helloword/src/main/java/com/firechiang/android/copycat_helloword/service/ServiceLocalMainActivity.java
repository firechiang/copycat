package com.firechiang.android.copycat_helloword.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 本地Service(服务）相关（注意：Service（服务）的应用场景主要是后台服务和暴露API给其它App调用）
 * 注意：所有的Service都需要在AndroidManifest.xml文件中配置，否则无法使用（跟Servlet一样都需要配置）
 */
public class ServiceLocalMainActivity extends Activity {

    // Service（服务连接器）
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_local_main_activity);
    }

    /**
     * Activity 销毁时解绑Service（服务）（注意：如果不解绑将抛异常）
     */
    @Override
    protected void onDestroy() {
        // 服务连接器不为空才解绑
        if(serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
        super.onDestroy();
    }

    /**
     * 启动本地服务
     * 注意：已启动的服务不会重复启动
     * @param view
     */
    public void startLocalService(View view) {
        startService(new Intent(this,LocalService.class));
        Toast.makeText(this,"服务已启动",Toast.LENGTH_SHORT).show();
    }

    /**
     * 停止本地服务
     * 注意：已停止的服务不会重复停止 以及 已绑定的服务不能停止
     * @param view
     */
    public void stopLocalService(View view) {
        stopService(new Intent(this,LocalService.class));
        Toast.makeText(this,"服务已停止",Toast.LENGTH_SHORT).show();
    }

    /**
     * 绑定本地服务
     * @param view
     */
    public void buildLocalService(View view) {
        // 服务连接器为空才绑定
        if(serviceConnection == null) {
            serviceConnection = new LocalServiceConnection();
            /**
             * 绑定服务
             * @param intent            意图
             * @param serviceConnection 服务连接器
             * @param flags             标识（Context.BIND_AUTO_CREATE=绑定时如果服务没有自动创建服务）
             *
             */
            bindService(new Intent(this,LocalService.class),serviceConnection, Context.BIND_AUTO_CREATE);
        }
        Toast.makeText(this,"服务已绑定",Toast.LENGTH_SHORT).show();
    }

    /**
     * 解绑本地服务（注意：解绑Service（服务）后会自动销毁Service（服务））
     * @param view
     */
    public void unLocalService(View view) {
        // 服务连接器不为空才解绑
        if(serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
        Toast.makeText(this,"服务已解绑",Toast.LENGTH_SHORT).show();
    }


    /**
     * 自定义本地Service（服务）
     */
    public static class LocalService extends Service {

        /**
         * Service（服务）创建回调
         */
        @Override
        public void onCreate() {
            Log.i("TAG","Service: "+LocalService.class.getName()+",已创建");
            super.onCreate();
        }

        /**
         * Service（服务）启动回调
         */
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i("TAG","Service: "+LocalService.class.getName()+",已启动");
            return super.onStartCommand(intent, flags, startId);
        }

        /**
         * Service（服务）绑定回调
         * @param intent
         * @return
         */
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            Log.i("TAG","Service: "+LocalService.class.getName()+",已绑定");
            return null;
        }


        /**
         * Service（服务）解绑回调
         * @param intent
         * @return
         */
        @Override
        public boolean onUnbind(Intent intent) {
            Log.i("TAG","Service: "+LocalService.class.getName()+",已解绑");
            return super.onUnbind(intent);
        }

        /**
         * Service（服务）销毁回调
         */
        @Override
        public void onDestroy() {
            Log.i("TAG","Service: "+LocalService.class.getName()+",已销毁");
            super.onDestroy();
        }

    }

    /**
     * 本地Service（服务）连接器
     */
    private static class LocalServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
