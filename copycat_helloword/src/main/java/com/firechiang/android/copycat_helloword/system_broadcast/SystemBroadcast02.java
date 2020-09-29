package com.firechiang.android.copycat_helloword.system_broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 动态注册系统广播接收器和动态解除注册系统广播接收器
 * 注意：系统广播的应用场景主要用于监听系统事件
 */
public class SystemBroadcast02 extends Activity {

    String action = "com.firechiang.android.copycat_helloword.system_broadcast.SimpleBroadcastReceiver2.action";
    // 广播接收器对象
    SimpleBroadcastReceiver2 simpleBroadcastReceiver2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_broadcast_02);
    }

    /**
     * 注册系统广播接收器
     * @param view
     */
    public void registerBroadcast(View view) {
        if(simpleBroadcastReceiver2 == null) {
            simpleBroadcastReceiver2 = new SimpleBroadcastReceiver2();
            IntentFilter intentFilter = new IntentFilter(action);
            // 指定当前广播接收器的优先级，值一般是 0-1000。越大越好
            intentFilter.setPriority(500);
            // 动态注册广播接收器
            registerReceiver(simpleBroadcastReceiver2,intentFilter);
        }
    }

    /**
     * 解除注册系统广播接收器
     * @param view
     */
    public void unRegisterBroadcast(View view) {
        if(simpleBroadcastReceiver2 != null) {
            unregisterReceiver(simpleBroadcastReceiver2);
            simpleBroadcastReceiver2 = null;
        }
    }

    /**
     * 发送一般的系统广播（注意：这种广播所有的广播接收器都会接收到，几乎是同时接收到）
     * @param view
     */
    public void sendGeneralBroadcast(View view) {
        // 注意：这个action就是上面我们动态注册广播接收器所传的那个action
        Intent intent = new Intent(action);
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
        // 注意：这个action就是上面我们动态注册广播接收器所传的那个action
        Intent intent = new Intent(action);
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
     * 注意：这个广播接收器是动态注册的
     */
    public static class SimpleBroadcastReceiver2 extends BroadcastReceiver {

        public SimpleBroadcastReceiver2() {
            Log.i("TAG","动态注册的广播接收器 "+SimpleBroadcastReceiver2.class.getName()+" 被创建");
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // 终止广播（注意：如果终止了，优先级低的广播接收器就接收不到广播了）
            // 注意：这个函数只针对于有序广播
            //abortBroadcast();
            Log.i("TAG","动态注册的广播接收器 "+SimpleBroadcastReceiver2.class.getName()+" 接收到了事件，msg="+intent.getStringExtra("msg"));
        }
    }
}
