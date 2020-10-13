package com.firechiang.android.copycat_helloword.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 绘制自定义图形
 */
public class GraphicalImageActivity06CustomGraphics extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_image_activity_06_customgraphics);
        setContentView(new CustomGraphicsView(this));
    }

    /**
     * 绘制自定义图形的View（视图）
     */
    class CustomGraphicsView extends View {
        /**
         * 绘制工具
         * BitmapDrawable 可绘制位图
         * ShapeDrawable  可绘制图形
         * LayerDrawable  可绘制图层
         */
        private ShapeDrawable shapeDrawable;
        // 画笔
        private Paint paint;

        public CustomGraphicsView(Context context) {
            super(context);
            /**
             * @param shape new OvalShape()绘制椭圆
             */
            this.shapeDrawable = new ShapeDrawable(new OvalShape());
            // 设置绘制的颜色
            this.shapeDrawable.getPaint().setColor(Color.RED);
            // 设置显示的区域范围（就是绘制范围，也是椭圆的范围）
            this.shapeDrawable.setBounds(10,10,210,160);

            // 初始化画笔
            this.paint = new Paint();
            // 设置画笔颜色
            this.paint.setColor(Color.GREEN);
            // 设置画笔字体大小
            this.paint.setTextSize(40);
            // 设置粗体字
            this.paint.setTypeface(Typeface.DEFAULT_BOLD);
            // 清除锯齿
            paint.setAntiAlias(true);
        }

        /**
         * 每次更新视图会回调此函数
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas) {
            Log.i("TAG","重新绘制自定义图形");
            // 画一个白色背景
            canvas.drawColor(Color.WHITE);
            // 画一个椭圆
            this.shapeDrawable.draw(canvas);
            /**
             * 画文字
             * @param text  要画的文字
             * @param x     X轴的坐标
             * @param y     Y轴坐标
             * @param paint 画笔
             */
            canvas.drawText("来自湖南的毛毛",200,200,this.paint);
            super.onDraw(canvas);
        }
    }
}
