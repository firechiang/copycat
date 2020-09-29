// 注意：这个包名要和服务端的一致否则会报 java.lang.SecurityException: Binder invocation to an incorrect interface 错误
package com.android.internal.telephony;

/**
 * Android 电话服务所提供的相关API（源地址：https://github.com/aosp-mirror/platform_frameworks_base/blob/master/telephony/java/com/android/internal/telephony/ITelephony.aidl）
 * 注意：这个文件写好后使用 Build--> Make Module 'copycat_helloword' 生成对应的 ITelephony.Stub 类
 * 注意：这个文件的函数要和服务端的一致
 */
interface ITelephony {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
