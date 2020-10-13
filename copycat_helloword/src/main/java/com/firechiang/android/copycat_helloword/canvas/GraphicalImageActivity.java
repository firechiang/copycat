package com.firechiang.android.copycat_helloword.canvas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 测试操作图片和图形相关
 */
public class GraphicalImageActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_image_activity);
    }


    /**
     * 测试Bitmap
     * @param view
     */
    public void testBitmap(View view) {
        startActivity(new Intent(this,GraphicalImageActivity01Bitmap.class));
    }

    /**
     * 使用Matrix缩放，旋转，平移图片
     * @param view
     */
    public void testImageScale(View view) {
        startActivity(new Intent(this,GraphicalImageActivity02Matrix.class));
    }

    /**
     * 使用Shape制作图形（类似于CSS的应用）
     * @param view
     */
    public void testShapeButton(View view) {
        startActivity(new Intent(this,GraphicalImageActivity03Shap.class));
    }

    /**
     * Selector+Shape或Selector+Drawable（就是类似于鼠标按下一个样式和鼠标抬起一个样式简单使用）
     * @param view
     */
    public void testSelectorShapeButton(View view) {
        startActivity(new Intent(this,GraphicalImageActivity04Selector.class));
    }

    /**
     * 9patch图片操作相关
     */
    public void test9patch(View view) {
        startActivity(new Intent(this,GraphicalImageActivity05Patch9.class));
    }

    /**
     * 绘制自定义图形
     * @param view
     */
    public void drawCustomGraphics(View view) {
        startActivity(new Intent(this,GraphicalImageActivity06CustomGraphics.class));
    }
}
