package com.firechiang.android.copycat_helloword.ui_example;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个显示所有App信息的列表，点击显示一个小界面里面有删除运行按钮
 */
public class UiExample03Activity extends Activity {

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
        // 给单个选项添加点击事件监听
        ItemClickOrScrollListener itemClickOrScrollListener = new ItemClickOrScrollListener();
        // 给单个选项添加点击事件监听
        this.listView.setOnItemClickListener(itemClickOrScrollListener);
        // 给ListView添加滚动事件监听
        this.listView.setOnScrollListener(itemClickOrScrollListener);
    }

    /**
     * 点击和滚动事件
     */
    public class ItemClickOrScrollListener implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
        /**
         * 悬浮显示框
         */
        private PopupWindow popupWindow;
        /**
         * 悬浮显示框要显示的视图
         */
        private View popupWindowView;

        /***
         * @param parent   当前ListView
         * @param view     当前选项视图
         * @param position 当前视图数据的下标
         * @param id       当前视图的ID
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //TextView name = view.findViewById(R.id.ui_example01_item_name);
            //Toast.makeText(UiExample03Activity.this,name.getText(),Toast.LENGTH_SHORT).show();
            // 初始化悬浮显示框
            if(popupWindow == null) {
                this.popupWindowView = View.inflate(UiExample03Activity.this, R.layout.ui_example01_popup_window, null);
                /**
                 * @param contentView 悬浮显示框的视图
                 * @param width       悬浮显示框的最大宽度
                 * @param height      悬浮显示框的最大高度
                 */
                this.popupWindow = new PopupWindow(popupWindowView,view.getWidth() - 200,view.getHeight());
            }
            // 如果正在显示，就移除
            if(this.popupWindow.isShowing()) {
                // 隐藏
                this.popupWindow.dismiss();
            }
            /**
             * 显示悬浮显示框
             * @param anchor 要显示哪个视图的上面
             * @param xoff   显示时X轴偏移量，就是显示X轴的位置（注意：显示在哪个视图上面哪个视图就是基准视图）
             * @param yoff   显示时Y轴偏移量，就是显示Y轴的位置（注意：显示在哪个视图上面哪个视图就是基准视图）
             */
            this.popupWindow.showAsDropDown(view,180,-view.getHeight());
            /**
             * 实现从右向左移动一个自己的宽度
             * @param fromXType  移动前X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
             * @param fromXValue 移动前的中心点和相对视图在X轴上的偏移量（就是移动前X轴的位置）
             * @param toXType    移动完成后X轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父组视图））
             * @param toXValue   移动完成后的中心点和相对视图在X轴上的偏移量（就是最终X轴的位置）
             * @param fromYType  移动前Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
             * @param fromYValue 移动前的中心点和相对视图在Y轴上的偏移量（就是移动前Y轴的位置）
             * @param toYType    移动完成后Y轴坐标的相对类型（Animation.ABSOLUTE（动画中心点坐标坐标相对绝对坐标值），Animation.RELATIVE_TO_SELF（动画中心点坐标相对自己），Animation.RELATIVE_TO_PARENT（动画中心点坐标相对父视图））
             * @param toYValue   移动完成后的中心点和相对视图在Y轴上的偏移量（就是最终Y轴的位置）
             */
            TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, popupWindow.getWidth(), Animation.RELATIVE_TO_SELF, 0,Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_SELF,0);
            // 持续2秒
            translateAnimation.setDuration(1000);
            /**
             * 设置动画篡改器（注意：动画篡改器可以使用Xml配置示列文件 /res/anim/cycle_7.xml）
             * new AccelerateInterpolator（加速执行动画）
             * new DecelerateInterpolator（减速执行动画）
             * new LinearInterpolator（线性平滑（就是如果动画多次执行它不会有间隔的感觉））
             * new CycleInterpolator（周期循环变化）
             */
            // 启动动画
            this.popupWindowView.startAnimation(translateAnimation);
        }

        /**
         * 滚动状态发送改变时回调
         * @param view
         * @param scrollState AbsListView.OnScrollListener.SCROLL_STATE_IDLE（空闲状态（就是没有动）），AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL（滚动状态（跟着手指在移动，就是开始滚动）），AbsListView.OnScrollListener.SCROLL_STATE_FLING（快速滚动）
         */
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // 开始滚动时
            if(AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                // 如果正在显示，就移除
                if(null != this.popupWindow && this.popupWindow.isShowing()) {
                    // 隐藏
                    this.popupWindow.dismiss();
                }
            }
        }
        /**
         * 滚动事件一直滚动一直回调
         * @param view
         * @param firstVisibleItem 滚动时第一个可见视图的下标
         * @param visibleItemCount 当前显示的视图个数
         * @param totalItemCount   当前总视图个数
         */
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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
                convertView = View.inflate(UiExample03Activity.this,R.layout.ui_example01_item,null);
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
