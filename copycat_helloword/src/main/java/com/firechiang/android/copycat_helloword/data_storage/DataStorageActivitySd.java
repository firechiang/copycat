package com.firechiang.android.copycat_helloword.data_storage;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.firechiang.android.copycat_helloword.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 测试SD卡存储
 * 数据保存在 /storage/sdcard/Android/data/packageName/files 文件夹里面（注意：packageName就是app的名称），其它应用可读，应用删除时也会删除自动删除
 * 也可以自定义文件夹目录（注意：自定义文件夹目录已废弃不能再使用了，其实这个自定义的目录还是在SD卡里面，但是应用删除时不会删除该目录，可使用 Environment.getExternalStorageDirectory() 获取SD挂载目录，）
 */
public class DataStorageActivitySd extends Activity {

    private EditText fileName;

    private EditText fileContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_storage_activity_sd);

        this.fileName = findViewById(R.id.data_storage_activity_sd_filename);
        this.fileContent = findViewById(R.id.data_storage_activity_sd_filecontent);
    }

    /**
     * 保存文件（保存到SD卡系统目录：/sdcard/Android/data/packageName/files 文价夹里面（注意：packageName就是app的名称））
     * @param view
     */
    public void saveData1(View view) throws IOException {
        // 确定有SD卡读写权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //弹窗解释为何需要该权限，再次请求权限
                Toast.makeText(this, "请授权SD卡读写权限！", Toast.LENGTH_LONG).show();
                //跳转到应用设置授权界面
                Intent newIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                newIntent.setData(uri);
                startActivity(newIntent);
            } else {
                //不需要解释为何需要授权直接请求授权（注意：requestCode会原封不动的返回给授权回调函数 onRequestPermissionsResult）
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            // 有权限
        } else {
            // SD卡处于挂载状态
            if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                Toast.makeText(this,"SD卡未挂载",Toast.LENGTH_SHORT).show();
            }
            String fileName = this.fileName.getText().toString();
            String fileContent = this.fileContent.getText().toString();
            /**
             * 获取SD卡系统目录
             * 注意：type(Environment.DIRECTORY_DCIM) 其实是子目录的名称，传了就定位到子目录，不传就在SD卡系统目录
             * @param type
             */
            String path = getExternalFilesDir(null).getAbsolutePath();
            // 创建文件输出流
            FileOutputStream file = new FileOutputStream(path + File.separator + fileName);
            // 写入内容
            file.write(fileContent.getBytes(StandardCharsets.UTF_8));
            file.close();
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 读取文件内容（保存到SD卡系统目录：/sdcard/Android/data/packageName/files 文价夹里面（注意：packageName就是app的名称））
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void queryData1(View view) throws IOException {
        String fileName = this.fileName.getText().toString();
        /**
         * 获取SD卡系统目录
         * 注意：type(Environment.DIRECTORY_DCIM) 其实是子目录的名称，传了就定位到子目录，不传就在SD卡系统目录
         * @param type
         */
        String path = getExternalFilesDir(null).getAbsolutePath();
        // 获取要读取的文件的全路径
        String filePath = path + File.separator + fileName;
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        Toast.makeText(this,new String(bytes,StandardCharsets.UTF_8),Toast.LENGTH_SHORT).show();
    }
}
