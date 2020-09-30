package com.firechiang.android.copycat_helloword.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import androidx.annotation.Nullable;

/**
 * 开机自动注册接收短信事件的广播接收器，接收到短信后实现黑名单短信拦截
 * 注意：这个接收短信广播事件的 Action 字符串在 Android8.0 以后已经不能用了被限制了，如果还是要接收短信广播事件需要重新研究
 */
public class ServiceInterceptMessageActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 接收短信事件的广播接收器，就是接收短信事件（注意：广播接收器每处理一条广播就会被销毁，所以说广播接收器是多例的）
     * 注意：静态注册广播接收器需要在AndroidManifest.xml文件中配置，配好了以后会自动创建和注册
     * 注意：这个接收短信广播事件的 Action 字符串在 Android8.0 以后已经不能用了被限制了，如果还是要接收短信广播事件需要重新研究
     */
    public static class MessageBroadcastReceiver extends BroadcastReceiver {

        /**
         * 接收到短信事件回调
         * @param context
         * @param intent
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            // 终止广播（注意：如果终止了，优先级低的广播接收器就接收不到广播了）
            // 注意：这个函数只针对于有序广播
            //abortBroadcast();
            // 启动拦截黑名单短信服务

            // 获取所有的额外数据
            Bundle extras = intent.getExtras();
            // 获取短信数据
            byte[] bytes = (byte[])extras.get("pdus");
            // 转换为短信对象
            SmsMessage fromPdu = SmsMessage.createFromPdu(bytes);
            // 短信的发送号码
            String originatingAddress = fromPdu.getOriginatingAddress();
            // 短信的内容
            String messageBody = fromPdu.getMessageBody();
            // 终止广播（注意：终止后优先级低的广播接收器将接收不到这个条广播）
            //abortBroadcast();
        }
    }
}
