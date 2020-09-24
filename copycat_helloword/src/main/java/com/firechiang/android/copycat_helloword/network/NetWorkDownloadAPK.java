package com.firechiang.android.copycat_helloword.network;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.firechiang.android.copycat_helloword.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 测试下载APK并显示下载进度，下载完成进行安装apk
 */
public class NetWorkDownloadAPK extends Activity {
    // 跳转到设置 允许安装未知来源APP 的权限界面 所携带的参数code
    int requestCodePermission = 1;
    // 跳转到安装APP的界面 所携带的参数code
    int requestCodeInstall = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_download_apk);
    }

    /**
     * 跳转到另一个Activity的回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == requestCodePermission) {
                // 注意：在这里写安装App的逻辑
                Log.e("TAG", "用户赋予了安装未知来源APP的权限");
            }
        } else {
            if (requestCode == requestCodePermission) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                    if (!hasInstallPermission) {
                        // 注意：在这里写用户没有赋予安装未知来源APP权限的处理逻辑
                        Log.e("TAG", "用户没有赋予安装未知来源APP的权限");
                    }
                }
            } else if (requestCode == requestCodeInstall) {
                // 注意： 用户不管有没有安装都会到这里来
                Log.e("TAG","到了安装界面但是用户拒绝安装APP");
            }
        }
    }

    /**
     * 下载APK并安装
     * @param view
     */
    public void downloadApk(View view) {
        // 注意这个Dialog已经废弃（不建议使用，推荐查看Dialog列子使用）
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 显示进度条
        dialog.show();
        // SD卡目录
        //File externalFilesDir = getExternalFilesDir(null);
        // 手机内部存储目录
        File externalFilesDir = getFilesDir();
        // apk文件
        final File apkFile = new File(externalFilesDir,"firefox.apk");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.wandoujia.com/apps/288613/download/dot?ch=detail_normal_dl");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    // 发起连接
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if(responseCode == 200) {
                        // 设置进度条最大进度（就是设置文件大小）
                        dialog.setMax(connection.getContentLength());
                        InputStream inputStream = connection.getInputStream();
                        FileOutputStream fos = new FileOutputStream(apkFile);
                        byte[] bytes = new byte[1024];
                        int len = -1;
                        while((len= inputStream.read(bytes)) != -1) {
                            fos.write(bytes,0,len);
                            // 进度条增长进度
                            dialog.incrementProgressBy(len);
                        }
                        fos.close();
                        inputStream.close();
                    }
                    // 安装APK
                    installApk(apkFile);
                    // 关闭进度条
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    });
                } catch (IOException e) {
                    Log.e("TAG","下载地址：https://www.wandoujia.com/apps/288613/download/dot?ch=detail_normal_dl 异常",e);
                    // 关闭进度条
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    });
                    Toast.makeText(NetWorkDownloadAPK.this,"下载错误，详情请查看日志",Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    /**
     * 自动安装APK
     */
    private void installApk(File apkFile) {
        // 自动安装APK意图
        Intent intent = new Intent(Intent.ACTION_VIEW/*Intent.ACTION_INSTALL_PACKAGE（这个其实也可以只是被废弃了）*/);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 如果Android大于7.0就使用把当前应用的存储目录共享给第三方应用以达到可以自动安装APK的效果（Android7.0以后才需要这样做）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            // 注意：com.firechiang.android.copycat_helloword.fileprovider 是共享权限的名称，权限在AndroidManifest.xml配置文件里面
            Uri uri = FileProvider.getUriForFile(NetWorkDownloadAPK.this, "com.firechiang.android.copycat_helloword.fileprovider", apkFile);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            // 如果Android大于 8.0（验证是否有可以安装未知来源APP的权限）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                // 没有可以安装未知来源APP的权限
                if (!hasInstallPermission) {
                    // 跳转到设置 允许安装未知来源APP 的权限界面
                    startInstallPermissionSettingActivity();
                    return;
                }
            }
        // 如果Android小于7.0用传统的方式自动安装APK
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivityForResult(intent, requestCodeInstall);
//        if (getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
//            //如果APK安装界面存在，携带请求码跳转。使用forResult是为了处理用户 取消 安装的事件。外面这层判断理论上来说可以不要，但是由于国内的定制，这个加上还是比较保险的
//        }
    }

    /**
     * 验证网络请求权限（注意：验证网络权限最好使用带回调的跳转）
     * @return
     */
    public void permission() {
        // 确定有网络权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            //没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                //弹窗解释为何需要该权限，再次请求权限
                Toast.makeText(this, "请授予网络请求权限！", Toast.LENGTH_LONG).show();
                //跳转到应用设置授权界面
                Intent newIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                newIntent.setData(uri);
                startActivity(newIntent);
                return;
            } else {
                //不需要解释为何需要授权直接请求授权（注意：requestCode会原封不动的返回给授权回调函数 onRequestPermissionsResult）
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            // 有权限
        }
    }

    /**
     * 跳转到设置 允许安装未知来源APP 的权限界面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, requestCodePermission);
    }

}
