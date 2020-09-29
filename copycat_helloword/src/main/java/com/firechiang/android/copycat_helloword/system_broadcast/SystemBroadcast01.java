package com.firechiang.android.copycat_helloword.system_broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 发送一般的系统广播和有序广播以及静态注册广播接收器简单使用
 * 注意：系统广播的应用场景主要用于监听系统事件
 * 注意：静态注册广播接收器需要在AndroidManifest.xml文件中配置，配好了以后会自动创建和注册
 */
public class SystemBroadcast01 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_broadcast_01);
    }

    /**
     * 发送一般的系统广播（注意：这种广播所有的广播接收器都会接收到，几乎是同时接收到）
     * @param view
     */
    public void sendGeneralBroadcast(View view) {
        /**
         * @param action 注意：这个action就是在静态注册广播时配置在AndroidManifest.xml文件里面的那个action
         */
        Intent intent = new Intent("com.firechiang.android.copycat_helloword.system_broadcast.SystemBroadcast01$SimpleBroadcastReceiver1.action");
        // Android8.0以上在同一个APP里面发送隐式广播给静态注册的广播接收器一定要设置 广播接收器所在的包名否则广播将发送不出去
        intent.setPackage(getPackageName());
        intent.putExtra("msg","一般广播");
        // 发送一般广播
        sendBroadcast(intent);
    }

    /**
     * 发送有序的系统广播（注意：这种广播所有的广播接收器都会接收到，但是它会根据接收器的优先级来有序执行，而且还可以终止广播，终止了广播，优先级低的广播接收器将接收不到广播）
     * @param view
     */
    public void sendOrderBroadcast(View view) {
        /**
         * @param action 注意：这个action就是在静态注册广播时配置在AndroidManifest.xml文件里面的那个action
         */
        Intent intent = new Intent("com.firechiang.android.copycat_helloword.system_broadcast.SystemBroadcast01$SimpleBroadcastReceiver1.action");
        // Android8.0以上在同一个APP里面发送隐式广播给静态注册的广播接收器一定要设置 广播接收器所在的包名否则广播将发送不出去
        intent.setPackage(getPackageName());
        intent.putExtra("msg","有序广播");
        /**
         * @param intent             意图
         * @param receiverPermission 权限
         */
        sendOrderedBroadcast(intent,null);
    }


    /**
     * 自定义的广播接收器1（注意：广播接收器每处理一条广播就会被销毁，所以说广播接收器是多例的）
     * 注意：静态注册广播接收器需要在AndroidManifest.xml文件中配置，配好了以后会自动创建和注册
     */
    public static class SimpleBroadcastReceiver1 extends BroadcastReceiver {

        public SimpleBroadcastReceiver1() {
            Log.i("TAG","静态注册的广播接收器 "+SimpleBroadcastReceiver1.class.getName()+" 被创建");
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // 终止广播（注意：如果终止了，优先级低的广播接收器就接收不到广播了）
            // 注意：这个函数只针对于有序广播
            //abortBroadcast();
            Log.i("TAG","静态注册的广播接收器 "+SimpleBroadcastReceiver1.class.getName()+" 接收到了事件，msg="+intent.getStringExtra("msg"));
        }
    }
}
