package com.firechiang.android.copycat_helloword.service;

import android.app.Activity;
import android.app.Service;
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
 * 拦截黑名单电话并自动断
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
        private PhoneStateListener phoneStateListener = new PhoneStateListener() {
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
                        // 这里写自动挂断电话逻辑
                    }
                }
                // 接通
                if(TelephonyManager.CALL_STATE_OFFHOOK == state) {

                }
                super.onCallStateChanged(state, phoneNumber);
            }
        };

        /**
         * 服务创建回调
         */
        @Override
        public void onCreate() {
            // 获取电话服务API管理者
            telephonyManager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            /**
             * 监听器来电状态
             * @param listener 监听器
             * @param events   需要监听来电的那些状态（PhoneStateListener.LISTEN_CALL_STATE=监听所有来电状态）
             */
            telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
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

            return super.onStartCommand(intent, flags, startId);
        }

        /**
         * 停止服务回调
         */
        @Override
        public void onDestroy() {
            /**
             * 监听器来电状态
             * @param listener 监听器
             * @param events   需要监听那些状态（PhoneStateListener.LISTEN_NONE=不监听了（就是停止监听））
             */
            telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_NONE);
            super.onDestroy();
        }
    }

}
