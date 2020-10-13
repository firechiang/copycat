package com.firechiang.android.copycat_helloword.canvas;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 使用Matrix缩放，旋转，平移图片
 */
public class GraphicalImageActivity02Matrix extends Activity {

    private ImageView imageView;

    private Matrix matrix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_image_activity_02_matrix);
        this.imageView = findViewById(R.id.graphical_image_activity_02_matrix_image01);
        this.matrix = new Matrix();
    }

    /**
     * 缩放
     * @param view
     */
    public void actionScale(View view) {
        /**
         * @param sx X轴缩放比例
         * @param sy Y轴缩放比例
         */
        matrix.postScale(0.8f,0.8f);
        imageView.setImageMatrix(matrix);
    }

    /**
     * 平移
     * @param view
     */
    public void actionTranslation(View view){
        /**
         * @param dx X轴偏移量
         * @param dy y轴偏移量
         */
        matrix.postTranslate(10, 10);
        imageView.setImageMatrix(matrix);
    }

    /**
     * 旋转
     * @param view
     */
    public void actionRotate(View view) {
        /**
         * @param degrees 旋转角度
         * @param px 旋转中心点X轴的坐标
         * @param py 旋转中心点Y轴的坐标
         */
        matrix.postRotate(30,imageView.getWidth()/2,imageView.getHeight()/2);
        imageView.setImageMatrix(matrix);
    }

    /**
     * 还原
     * @param view
     */
    public void actionReset(View view) {
        matrix.reset();
        imageView.setImageMatrix(matrix);
    }
}
