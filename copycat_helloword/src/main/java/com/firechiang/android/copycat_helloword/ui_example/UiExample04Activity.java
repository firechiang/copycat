package com.firechiang.android.copycat_helloword.ui_example;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个显示所有App信息的列表，向左滑动显示删除修改按钮
 */
public class UiExample04Activity extends Activity {

    private ListView listView;

    private AppAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_example01_activity);
        this.listView = findViewById(R.id.ui_example_activity_listview);
        this.adapter = new AppAdapter();
        // 设置ListView视图数据并加载显示视图
        this.listView.setAdapter(adapter);
    }

    /**
     * 触摸事件监听
     */
    public class SlideTouchListener implements View.OnTouchListener {
        // 视图移动工具
        private final Matrix matrix;
        // 左边默认显示的组件
        private final View targetLeftView;
        // 右边待滑出的组件
        private final View targetRightView;
        // 最后触摸X轴的位置
        private int lastX;
        // 滑动速度（越大越快），可调整
        private int speed = 8;
        // 可滑出最大宽度（就是滑出到最大宽度时直接跳固定宽度），可调整
        private int maxSlide = 14;
        // 右边滑出组件固定宽度，可调整
        private int fixedSlide = 28;

        // 右边待滑出组件可滑出最大宽度
        private int maxSlideOutWidth = speed * maxSlide;

        public SlideTouchListener(View targetLeftView,View targetRightView) {
            this.matrix = new Matrix();
            this.targetLeftView = targetLeftView;
            this.targetRightView = targetRightView;
        }
        /**
         * 触摸移动事件
         * 返回false表示只是监听，下一次的动作不会再回调，而是交给父视图或Activity的onTouchEvent()函数去处理。返回true表示处理该事件，下一次的动作还会继续回调，而父视图或Activity的onTouchEvent()函数不会被调用
         */
        @Override
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public boolean onTouch(View v, MotionEvent event) {
            // 获取当前触摸X轴的位置
            int rawX = (int)event.getRawX();
            int action = event.getAction();
            // 手指按下屏幕
            if(MotionEvent.ACTION_DOWN == action) {
                Log.i("TAG","手指按下屏幕");
            }
            // 手指滑动 并且 当前触摸X轴坐标小于最后触摸X轴坐标说明是向左滑动 并且 当前滑出的宽度还未到右边待滑出组件的固定滑出宽度
            if(MotionEvent.ACTION_MOVE == action && rawX < lastX && targetRightView.getLayoutParams().width < maxSlideOutWidth) {
                /**
                 * 设置平移
                 * @param dx X轴偏移量
                 * @param dy y轴偏移量
                 */
                matrix.postTranslate(-speed, 0);
                // 设置平移动画
                targetLeftView.setAnimationMatrix(matrix);
                // 获取视图宽高
                ViewGroup.LayoutParams lp = targetRightView.getLayoutParams();
                // 增加宽度
                lp.width = speed +  lp.width;
                // 设置宽度
                targetRightView.setLayoutParams(lp);
                // 设置平移动画
                targetRightView.setAnimationMatrix(matrix);
            }
            // 手指离开屏幕或滑动终止就还原视图
            if(MotionEvent.ACTION_UP == action || MotionEvent.ACTION_CANCEL == action) {
                // 还原视图
                restView();
            }
            // 最后触摸X轴的位置
            lastX = rawX;
            return Boolean.TRUE;
        }

        /**
         * 固定视图
         */
        @RequiresApi(api = Build.VERSION_CODES.Q)
        private boolean fixedView() {
            //Log.i("TAG",targetRightView.getLayoutParams().width+" = "+ maxSlideOutWidth);
            // 当前滑出宽度到达固定视图滑出宽度
            if(targetRightView.getLayoutParams().width >= maxSlideOutWidth) {
                /**
                 * 设置平移
                 * @param dx X轴偏移量
                 * @param dy y轴偏移量
                 */
                matrix.postTranslate(-speed * (fixedSlide - maxSlide), 0);
                // 设置平移动画
                targetLeftView.setAnimationMatrix(matrix);
                // 获取视图宽高
                ViewGroup.LayoutParams lp = targetRightView.getLayoutParams();
                lp.width = speed * fixedSlide;
                // 动态设置视图的宽高
                targetRightView.setLayoutParams(lp);
                // 设置平移动画
                targetLeftView.setAnimationMatrix(matrix);
                targetRightView.setAnimationMatrix(matrix);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        /**
         * 还原视图
         */
        @RequiresApi(api = Build.VERSION_CODES.Q)
        private void restView() {
            if(!fixedView()) {
                // 还原平移
                matrix.reset();
                // 设置平移
                targetLeftView.setAnimationMatrix(matrix);
                // 获取视图宽高
                ViewGroup.LayoutParams lp = targetRightView.getLayoutParams();
                lp.width = 0;
                // 动态设置视图的宽高
                targetRightView.setLayoutParams(lp);
                // 设置平移
                targetRightView.setAnimationMatrix(matrix);
            }
        }
    }

    public class AppAdapter extends BaseAdapter {
        // 手机里面所有App的列表信息
        private List<AppInfo> data;

        public AppAdapter () {
            this.data = getAllAppInfo();
        }

        /**
         * 列表数据个数
         * @return
         */
        @Override
        public int getCount() {
            return data.size();
        }

        /**
         * 单个数据
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 获取列表单个视图
         * @param position    下标
         * @param convertView 当前视图缓存对象（注意：如果还有缓存它就是空的）
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = View.inflate(UiExample04Activity.this,R.layout.ui_example01_item,null);
                View targetLeftView = convertView.findViewById(R.id.ui_example01_item_relative_layout);
                View targetRightView = convertView.findViewById(R.id.ui_example01_item_button);
                convertView.setOnTouchListener(new SlideTouchListener(targetLeftView,targetRightView));
            }
            AppInfo appInfo = data.get(position);
            ImageView image = convertView.findViewById(R.id.ui_example01_item_image);
            TextView name = convertView.findViewById(R.id.ui_example01_item_name);
            // 给视图设置数据
            name.setText(appInfo.getAppName());
            image.setImageDrawable(appInfo.getIcon());
            return convertView;
        }

        public List<AppInfo> getData() {
            return data;
        }
    }

    /**
     * 应用信息
     */
    public static class AppInfo {
        // App的包名
        private String packageName;

        // App的名称
        private String appName;

        // 获取App的图标
        private Drawable icon;

        public AppInfo() {
        }

        public AppInfo(String packageName, String appName, Drawable icon) {
            this.packageName = packageName;
            this.appName = appName;
            this.icon = icon;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }

        public String getPackageName() {
            return packageName;
        }

        public String getAppName() {
            return appName;
        }

        public Drawable getIcon() {
            return icon;
        }
    }

    /**
     * 获取手机里面所有App的列表信息
     * @return
     */
    private List<AppInfo> getAllAppInfo() {
        List<AppInfo> list  = new ArrayList<>();
        // 获取到所有App的包管理器
        PackageManager packageManager = getPackageManager();
        // 创建一个主界面的intent
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 获取到所有App的信息列表
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        for(ResolveInfo ri:resolveInfos) {
            // App的包名
            String packageName = ri.activityInfo.packageName;
            // 获取App的图标
            Drawable icon = ri.loadIcon(packageManager);
            // App的名称
            String appName = ri.loadLabel(packageManager).toString();
            AppInfo info = new AppInfo(packageName,appName,icon);
            list.add(info);
        }
        return list;
    }
}
