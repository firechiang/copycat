package com.firechiang.android.copycat_helloword;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * 线性布局和打电话和发短信以及通过Activity路径跳转简单使用
 * 查找隐式意图跳转Activity的路径，可以启动模拟器再启动某个Activity再查看Logcat的日志找到被启动的Activity的类路径(注意：查看日志是只查看activitymanager的日志，就是在过滤输入框里面输入activitymanager即可)，
 * 再去查看Android系统源码找那个类，再找到那个类在AndroidManifest.xml配置文件里面所配置的action名称，最后根据那个action的名称就可以跳转了
 *  Intent intent = new Intent(Intent.ACTION_CALL);// Intent.ACTION_CALL 就是Activity的action名称，只不过这个名称被定义成常量了
 */
public class Activity06TelephoneAndSendMsg extends AppCompatActivity {

    private EditText editPhone;

    private EditText editMessage;

    private Button buttonLongCallPhone;

    /**
     * 长按拨打电话
     */
    private View.OnLongClickListener longCallPhone = new View.OnLongClickListener() {
        /**
         * 事件监听回调
         * @param v
         * @return 返回true表示该事件已经被消费，不会在触发点击事件
         */
        @Override
        public boolean onLongClick(View v) {
            // 确定有打电话权限
            if (ContextCompat.checkSelfPermission(Activity06TelephoneAndSendMsg.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //没有获得授权，申请授权
                if (ActivityCompat.shouldShowRequestPermissionRationale(Activity06TelephoneAndSendMsg.this, Manifest.permission.CALL_PHONE)) {
                    //弹窗解释为何需要该权限，再次请求权限
                    Toast.makeText(Activity06TelephoneAndSendMsg.this, "请授权！", Toast.LENGTH_LONG).show();
                    //跳转到应用设置授权界面
                    Intent newIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    newIntent.setData(uri);
                    startActivity(newIntent);
                } else {
                    //不需要解释为何需要授权直接请求授权（注意：requestCode会原封不动的返回给授权回调函数 onRequestPermissionsResult）
                    ActivityCompat.requestPermissions(Activity06TelephoneAndSendMsg.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
            // 有权限
            }else{
                // 根据路径跳转Activity（Intent.ACTION_CALL=拨打电话界面路径）
                Intent intent = new Intent(Intent.ACTION_CALL);
                String phone = Activity06TelephoneAndSendMsg.this.editPhone.getText().toString();
                // 跳过去后要显示的电话号码
                intent.setData(Uri.parse("tel:"+phone));
                Activity06TelephoneAndSendMsg.this.startActivity(intent);
            }
            return Boolean.TRUE;
        }
    };

    /**
     * 授权回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                //授权成功，继续拨打电话
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 根据路径跳转Activity（Intent.ACTION_CALL=拨打电话界面路径）
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    String phone = Activity06TelephoneAndSendMsg.this.editPhone.getText().toString();
                    // 跳过去后要显示的电话号码
                    intent.setData(Uri.parse("tel:"+phone));
                    // 直接跳到打电话页面（注意：intent有个红波浪线不管它，其实是没有判断权限问题，但是这个位置是授权后的回调，所以就不用判断了）
                    Activity06TelephoneAndSendMsg.this.startActivity(intent);
                } else {
                    //授权失败
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity06_telephone_and_send_msg);
        // 获取组件
        this.editPhone = this.findViewById(R.id.phone_TelephoneAndSendMsg);
        this.editMessage = this.findViewById(R.id.message_TelephoneAndSendMsg);
        // 打电话按钮
        this.buttonLongCallPhone = this.findViewById(R.id.btn_call_phone_TelephoneAndSendMsg);
        // 长按事件
        this.buttonLongCallPhone.setOnLongClickListener(longCallPhone);
    }

    /**
     * 点击跳到破号界面
     * @param view
     */
    public void callPhone(View view) {
        // 根据路径跳转Activity（Intent.ACTION_DIAL=拨号界面路径）
        Intent intent = new Intent(Intent.ACTION_DIAL);
        String phone = this.editPhone.getText().toString();
        // 跳过去后要显示的电话号码
        intent.setData(Uri.parse("tel:"+phone));
        // 跳转到打电话界面
        this.startActivity(intent);
    }

    /**
     * 点击跳转到编辑短信界面
     * @param view
     */
    public void sedMessage(View view) {
        // 意图对象（跳转到短信编辑页面）
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        String phone = this.editPhone.getText().toString();
        String message = this.editMessage.getText().toString();
        intent.setData(Uri.parse("smsto:"+phone));
        intent.putExtra("sms_body",message);
        startActivity(intent);
    }

    /**
     * 直接发短信
     * @param view
     */
    public void sedMsg(View view) {
        SmsManager sms = SmsManager.getDefault();
        String phone = this.editPhone.getText().toString();
        String message = this.editMessage.getText().toString();
        /**
         * 发送短信
         * @param destinationAddress 号码
         * @param scAddress          is the service center address or null to use
         * @param text               短信内容
         * @param sentIntent         短信回执相关（不传就表示不关心短信回执），短信回执包括对方有没有看相关
         * @param deliveryIntent     短信回执相关（不传就表示不关心短信回执），短信回执包括对方有没有看相关
         */
        sms.sendTextMessage(phone,null,message,null,null);
        Toast.makeText(this,"短信发送成功",Toast.LENGTH_SHORT);
    }
}
