package com.firechiang.android.copycat_helloword.service;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.internal.telephony.ITelephony;
import com.firechiang.android.copycat_helloword.R;

import java.lang.reflect.Method;

/**
 * 调用系统电话相关Service（服务）的APi
 */
public class ServicePhoneActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_phone_activity);
    }

    /**
     * 自动挂断电话
     * @see https://www.jianshu.com/p/ab88284384e9s
     * @see https://blog.csdn.net/u011146511/article/details/80059995
     * @param view
     */
    public void hangUp(View view) {
        try {
            Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
            IBinder binder = (IBinder) method.invoke(null, new Object[]{Context.TELEPHONY_SERVICE});
            ITelephony telephony = ITelephony.Stub.asInterface(binder);
            // 新版本的Android里面已经没有 endCall() 函数了，要写需要重新研究了
            //telephony.endCall();
        } catch (NoSuchMethodException e) {
            Log.d("TAG", "", e);
        } catch (ClassNotFoundException e) {
            Log.d("TAG", "", e);
        } catch (Exception e) {
        }
    }
}
