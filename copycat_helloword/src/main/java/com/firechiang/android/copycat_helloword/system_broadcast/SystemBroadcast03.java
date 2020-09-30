package com.firechiang.android.copycat_helloword.system_broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 测试接收开机事件的广播接收器简单使用
 * 注意：系统广播的应用场景主要用于监听系统事件
 */
public class SystemBroadcast03 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_broadcast_03);
    }

    /**
     * 接收开机事件的广播接收器，就是接收开机事件（注意：广播接收器每处理一条广播就会被销毁，所以说广播接收器是多例的）
     * 注意：静态注册广播接收器需要在AndroidManifest.xml文件中配置，配好了以后会自动创建和注册
     */
    public static class SimpleBroadcastReceiver3 extends BroadcastReceiver {

        public SimpleBroadcastReceiver3() {
            Log.i("TAG","开机事件广播接收器 "+SimpleBroadcastReceiver3.class.getName()+" 被创建");
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // 终止广播（注意：如果终止了，优先级低的广播接收器就接收不到广播了）
            // 注意：这个函数只针对于有序广播
            //abortBroadcast();
            Log.i("TAG","开机事件广播接收器 "+SimpleBroadcastReceiver3.class.getName()+" 接收到了事件，msg="+intent.getStringExtra("msg"));
        }
    }
}
