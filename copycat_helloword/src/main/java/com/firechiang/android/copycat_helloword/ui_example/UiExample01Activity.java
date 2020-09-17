package com.firechiang.android.copycat_helloword.ui_example;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个显示所有App信息的列表，点击某一项显示其名称，长按隐藏
 */
public class UiExample01Activity extends Activity {

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
        // 给单个选项添加点击事件
        this.listView.setOnItemClickListener(new ItemClickListener());
        // 给单个选项添加长按事件
        this.listView.setOnItemLongClickListener(new ItemLongClickListener());
    }

    /**
     * 点击事件实现（点击显示应用名称）
     */
    public class ItemClickListener implements AdapterView.OnItemClickListener {
        /***
         * @param parent   当前ListView
         * @param view     当前选项视图
         * @param position 当前视图数据的下标
         * @param id       当前视图的ID
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView name = view.findViewById(R.id.ui_example01_item_name);
            Toast.makeText(UiExample01Activity.this,name.getText(),Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 长按事件实现（长按删除选项）
     */
    public class ItemLongClickListener implements AdapterView.OnItemLongClickListener {

        /**
         * @param parent   ListView
         * @param view     当前选项视图
         * @param position 当前视图数据的下标
         * @param id       当前视图的ID
         * @return 返回 true，否则长按和点击同时触发
         */
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // 删除当前选项的数据
            UiExample01Activity.this.adapter.getData().remove(position);
            // 更新列表（刷新列表）（注意：这个函数使用的是缓存数据，不会去重新加载数据，故推荐生产使用）
            UiExample01Activity.this.adapter.notifyDataSetChanged();
            return true;
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
                convertView = View.inflate(UiExample01Activity.this,R.layout.ui_example01_item,null);
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
