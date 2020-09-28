package com.firechiang.android.copycat_helloword_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword_service.aidl.TestAidlInterface;

import java.util.Random;

/**
 * 这个Service(服务）暴露某些函数给其它APP调用
 * 注意：所有的Service都需要在AndroidManifest.xml文件中配置，否则无法使用（跟Servlet一样都需要配置）
 */
public class TestService extends Service {

    // 暴露给其他App调用的接口实现
    private TestAidlInterfaceImpl testAidlInterface = new TestAidlInterfaceImpl();

    /**
     * Service（服务）创建回调
     */
    @Override
    public void onCreate() {
        Log.i("TAG","Service: "+ TestService.class.getName()+",已创建");
        super.onCreate();
    }

    /**
     * Service（服务）启动回调
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG","Service: "+ TestService.class.getName()+",已启动");
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * Service（服务）绑定回调（返回暴露给其他App调用的接口实现）
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("TAG","Service: "+ TestService.class.getName()+",已绑定");
        // 暴露给其他App调用的接口实现
        return testAidlInterface;
    }


    /**
     * Service（服务）解绑回调
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("TAG","Service: "+ TestService.class.getName()+",已解绑");
        return super.onUnbind(intent);
    }

    /**
     * Service（服务）销毁回调
     */
    @Override
    public void onDestroy() {
        Log.i("TAG","Service: "+ TestService.class.getName()+",已销毁");
        super.onDestroy();
    }

    /**
     * 实现暴露给其他App调用的接口（注意：TestAidlInterface.Stub 是通过 TestAidlInterface 自动生成的（Build--> Make Module copycat_helloword_service））
     */
    private static class TestAidlInterfaceImpl extends TestAidlInterface.Stub {

        @Override
        public String getAppName(String prefix) throws RemoteException {

            return prefix+"_copycat_helloword_service_"+Math.random();
        }

        @Override
        public User getUser(int id) throws RemoteException {
            Random random = new Random();
            return new User("我的序号:"+random.nextInt(),random.nextInt());
        }
    }
}
