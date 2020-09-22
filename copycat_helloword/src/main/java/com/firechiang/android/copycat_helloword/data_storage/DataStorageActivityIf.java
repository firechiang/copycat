package com.firechiang.android.copycat_helloword.data_storage;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试手机内部存储
 * 数据保存在 /data/data/packageName/files 文件里面（注意：packageName就是app的名称），该文件可设置为只当前应用可读，其它应用不可读，应用删除时也会删除此文件
 */
public class DataStorageActivityIf extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_storage_activity_if);

        this.imageView = findViewById(R.id.data_storage_activity_if_image);
    }

    /**
     * 保存文件
     * @param view
     */
    public void saveData(View view) throws IOException {
        // 获取assets文件夹管理者（注意：assets目录java目录在同一级，也就是在main文件夹下面）
        AssetManager assetManager = getAssets();
        // 读取文件
        InputStream inputStream = assetManager.open("001.jpg");
        /**
         * 创建文件输出流
         * @param name 文件名称
         * @param mode 只当前项目可读
         */
        FileOutputStream outputStream = openFileOutput("001.jpg", Context.MODE_PRIVATE);
        // 将数据写到输出流
        byte[] bytes = new byte[1024];
        int len = -1;
        while((len = inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,len);
        }
        outputStream.close();
        inputStream.close();
        Toast.makeText(this,"保存完成",Toast.LENGTH_SHORT).show();
    }

    /**
     * 读取显示文件
     * @param view
     */
    public void queryData(View view) {
        // 获取到当前项目的手机内部存储目录的绝对路劲
        String dir = getFilesDir().getAbsolutePath();
        // 拼接图片的全路径
        String fileDir = dir+ "/001.jpg";
        // 将文件加载到Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeFile(fileDir);
        // 显示图片
        this.imageView.setImageBitmap(bitmap);
    }
}
