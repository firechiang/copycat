package com.firechiang.android.copycat_helloword.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;
import com.firechiang.android.copycat_helloword_service.aidl.TestAidlInterface;

import java.util.Random;

/**
 * 远程Service(服务）相关（其实就是调用手机里面其它APP所暴露的API）
 * 注意：所有的Service都需要在AndroidManifest.xml文件中配置，否则无法使用（跟Servlet一样都需要配置）
 * 注意：启动该Activity需要先启动一下 copycat_helloword_service 模块，因为远程Service（服务）就是写在这个模块里面的
 */
public class ServiceRemoteMainActivity extends Activity {

    private EditText function;

    private TextView response;

    private RemoteServiceConnection serviceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_remote_main_activity);

        this.function = findViewById(R.id.service_remote_main_activity_function);
        this.response = findViewById(R.id.service_remote_main_activity_response);
    }

    /**
     * Activity 销毁时解绑远程Service（服务）（注意：如果不解绑将抛异常）
     */
    @Override
    protected void onDestroy() {
        // 远程Service（服务）连接器不为空才解绑
        if(serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
        super.onDestroy();
    }


    /**
     * 绑定手机里面其它APP的Service（服务）
     * @param view
     */
    public void bindRemoteService(View view) {
        // 服务连接器为空才绑定
        if(serviceConnection == null) {
            // 远程Service（服务连接器）
            serviceConnection = new RemoteServiceConnection();
            // 远程Service（服务）意图（注意：这个意图的名称在 copycat_helloword_service 模块里面的 AndroidManifest.xml配置文件里面已经配好了）
            Intent intent = new Intent("com.firechiang.android.copycat_helloword_servicei.TestService");
            // 设置远程Service（服务）Class的包路径（注意：是 TestService 所在包路径，如果设置错误将无法调用）
            intent.setPackage("com.firechiang.android.copycat_helloword_service");
            /**
             * 绑定服务
             * @param intent            意图
             * @param serviceConnection 服务连接器
             * @param flags             标识（Context.BIND_AUTO_CREATE=绑定时如果服务没有自动创建服务）
             *
             */
            bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this,"远程Service（服务）已绑定",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 解绑手机里面其它APP的Service（服务）
     * @param view
     */
    public void unRemoteService(View view) {
        // 远程Service（服务）连接器不为空才解绑
        if(serviceConnection != null) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
    }

    /**
     * 调用手机里面其它APP的Service（服务）的函数
     * @param view
     */
    public void execRemoteService(View view) {
        // 服务连接器不为空才调用（不为空说明绑定好了）
        if(serviceConnection != null) {
            String methodName = function.getText().toString();
            try {
                String responseStr = "";
                if("getAppName".equals(methodName)) {
                    responseStr = serviceConnection.getTestAidlInterface().getAppName("");
                }else{
                    responseStr = serviceConnection.getTestAidlInterface().getUser(new Random().nextInt()).toString();
                }
                response.setText("调用结果："+responseStr);
            } catch (RemoteException e) {
                Toast.makeText(this,"调用远程Service（服务）异常，详情请查看日志",Toast.LENGTH_SHORT).show();
                Log.e("TAG","调用远程Service（服务）异常",e);
            }
        }else{
            Toast.makeText(this,"未绑定远程Service（服务）",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 远程Service（服务连接器）
     */
    public static class RemoteServiceConnection implements ServiceConnection {
        /**
         * 远程Service（服务）（注意：这个接口是由 TestAidlInterface.aidl 文件自动生成的，而这个文件是远程Service（服务）端提供的）
         * 注意：生成方法请查看 TestAidlInterface.aidl 文件
         */
        private TestAidlInterface testAidlInterface;

        /**
         * 连接成功回调
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取远程的Service（服务）的代理对象
            testAidlInterface = TestAidlInterface.Stub.asInterface(service);
            Log.i("TAG","远程Service（服务连接成功）");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
        /**
         * 获取远程的Service（服务）
         * @return
         */
        public TestAidlInterface getTestAidlInterface() {
            return testAidlInterface;
        }
    }
}
