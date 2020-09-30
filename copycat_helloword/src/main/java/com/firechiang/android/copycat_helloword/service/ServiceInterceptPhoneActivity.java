package com.firechiang.android.copycat_helloword.service;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 开机自动启动拦截黑名单电话服务和手动启动拦截服务并自动挂断
 */
public class ServiceInterceptPhoneActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_intercept_phone_activity);
    }

    /**
     * 启用黑名单电话拦截
     * @param view
     */
    public void enableInterceptPhone(View view) {
        Intent intent = new Intent(this,InterceptPhoneService.class);
        // 启动服务
        startService(intent);
    }

    /**
     * 停止黑名单电话拦截
     * @param view
     */
    public void disableInterceptPhone(View view) {
        Intent intent = new Intent(this,InterceptPhoneService.class);
        // 停止服务
        stopService(intent);
    }


    /**
     * 拦截黑名单的电话服务
     */
    public static class InterceptPhoneService extends Service {
        /**
         * 电话服务API管理者
         */
        private TelephonyManager telephonyManager;

        /**
         * 监听来电状态
         */
        private PhoneStateListener phoneStateListener;

        /**
         * 服务创建回调
         */
        @Override
        public void onCreate() {
            // 获取电话服务API管理者
            telephonyManager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            Log.i("TAG","拦截黑名单的电话服务 "+InterceptPhoneService.class.getName()+" 已启动");
            super.onCreate();
        }

        /**
         * 绑定服务回调（注意：这个用于调用其它App所暴露出来的API，如果不调用其它App的API就不用写）
         * @param intent
         * @return
         */
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        /**
         * 启动服务回调
         * @param intent
         * @param flags
         * @param startId
         * @return
         */
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            if(phoneStateListener == null) {
                phoneStateListener = new InterceptPhoneStateListener();
                /**
                 * 监听器来电状态
                 * @param listener 监听器
                 * @param events   需要监听来电的那些状态（PhoneStateListener.LISTEN_CALL_STATE=监听所有来电状态）
                 */
                telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
            }
            return super.onStartCommand(intent, flags, startId);
        }

        /**
         * 停止服务回调
         */
        @Override
        public void onDestroy() {
            if(phoneStateListener != null) {
                /**
                 * 监听器来电状态
                 * @param listener 监听器
                 * @param events   需要监听那些状态（PhoneStateListener.LISTEN_NONE=不监听了（就是停止监听））
                 */
                telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_NONE);
                phoneStateListener = null;
            }
            super.onDestroy();
        }
    }



    /**
     * 监听来电状态
     */
    public static class InterceptPhoneStateListener extends PhoneStateListener {
        /**
         * 监听来电状态
         * @param state       来电状态（TelephonyManager.CALL_STATE_IDLE=，TelephonyManager.CALL_STATE_OFFHOOK,TelephonyManager.CALL_STATE_RINGING）
         * @param phoneNumber 来电号码
         */
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            // 空闲（挂断或未来电之前都会调用一次）
            if(TelephonyManager.CALL_STATE_IDLE == state) {

            }
            // 响铃
            if(TelephonyManager.CALL_STATE_RINGING == state) {
                // 如果是黑名单的电话
                if("13113609230".equals(phoneNumber)) {
                    // 这里写自动挂断电话逻辑，可参考 ServiceEndPhoneActivity.class
                }
            }
            // 接通
            if(TelephonyManager.CALL_STATE_OFFHOOK == state) {

            }
            super.onCallStateChanged(state, phoneNumber);
        }
    }

    /**
     * 接收开机事件的广播接收器，就是接收开机事件（注意：广播接收器每处理一条广播就会被销毁，所以说广播接收器是多例的）
     * 注意：静态注册广播接收器需要在AndroidManifest.xml文件中配置，配好了以后会自动创建和注册
     */
    public static class SystemBootBroadcastReceiver extends BroadcastReceiver {

        /**
         * 接收到开机事件回调
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            // 终止广播（注意：如果终止了，优先级低的广播接收器就接收不到广播了）
            // 注意：这个函数只针对于有序广播
            //abortBroadcast();
            // 启动拦截黑名单电话服务
            context.startService(new Intent(context,InterceptPhoneService.class));
        }
    }

}
