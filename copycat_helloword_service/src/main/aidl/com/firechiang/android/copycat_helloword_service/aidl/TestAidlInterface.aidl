// TestAidlInterface.aidl
package com.firechiang.android.copycat_helloword_service.aidl;
// 注意：这个User用的User.aidl而不是User.class
import com.firechiang.android.copycat_helloword_service.aidl.User;

/**
 * 这个Service里面的函数是供其他App调用的，还有这个接口名称前面不用加访问权限修饰符（这个接口使用 AIDL 视图创建文件）
 * 注意：这个文件写好后使用 Build--> Make Module 'copycat_helloword_service' 生成对应的 TestAidlInterface.Stub 类（注意：TestAidlInterface.Stub 就是这个接口的实现的类，但是它是看不到的直接去继承就好了）
 */
interface TestAidlInterface {
    /**
     * 获取当前App的名称
     * @param prefix
     * @return
     */
    String getAppName(String prefix);

    /**
     * 获取用户
     * @param id
     * @return
     */
    User getUser(int id);
}
