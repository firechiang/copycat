package com.firechiang.android.copycat_helloword.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.firechiang.android.copycat_helloword.R;

/**
 * Bitmap加载张图片数据到内存中和封装Bitmap对象
 * 1，加载资源文件夹中的图片资源并显示
 * 2，加载存储空间中的图片资源并显示
 * 3，将一个bitmap对象保存到存储空间中
 */
public class GraphicalImageActivity01Bitmap extends Activity {

    private ImageView imageView01;

    private ImageView imageView02;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_image_activity_01_bitmap);

        this.imageView01 = findViewById(R.id.graphical_image_activity_01_bitmap_image01);
        this.imageView02 = findViewById(R.id.graphical_image_activity_01_bitmap_image02);
        // 加载资源文件夹中的图片资源并显示
        loadResourcesImage();
        // 加载存储空间中的图片资源并显示
        loadLoaclImage();
        // 将一个bitmap对象保存到存储空间中
        saveImage2Local();
    }

    /**
     * Bitmap加载张图片数据到内存中和封装Bitmap对象
     * 加载资源文件夹中的图片资源并显示
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void loadResourcesImage() {
        this.imageView01.setImageResource(R.drawable.app05);
        // 将资源文件夹里面的图片封装成 Drawable 对象
        Drawable drawable = getResources().getDrawable(R.drawable.app05,getTheme());
        //this.imageView01.setImageDrawable(drawable);
        // 将资源文件夹里面的图片封装成 Bitmap 对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app05);
        //this.imageView01.setImageBitmap(bitmap);
    }

    /**
     * 加载存储空间中的图片资源并显示
     */
    public void loadLoaclImage() {
        // 加载本地图片文件（注意：这个路径是假的）
        //Bitmap bitmap = BitmapFactory.decodeFile("/sdas/sadas/sadas/sd");
    }

    /**
     * 将一个bitmap对象保存到存储空间中
     */
    public void saveImage2Local() {
        // 将资源文件夹里面的图片封装成 Bitmap 对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app05);
        try {
            /**
             * 将Bitmap的图片保存到磁盘
             * @param CompressFormat format 格式
             * @param int quality           图片质量（0-100 注意：100等于不压缩）
             * @param OutputStream stream   输出流（创建文件输出流：name 文件路径以及名称，mode 只当前项目）
             */
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, openFileOutput("xxxx.jpeg", Context.MODE_PRIVATE));
        }catch(Exception e){

        }
    }
}


