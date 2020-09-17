package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示自定义的ListView，主定义的主要是用于获取单个视图和添加相关事件，TextView左边直接添加图片，和自定义公共样式属性（注意：这个不能当成主 Activity）
 */
public class Ui05ListViewComponentImageTextListView extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui05_listview_c01_simpletext_listview);
        this.listView = findViewById(R.id.ui05_listview_c01_simpletext_listview);
        CustomListXViewAdapter adapter = new CustomListXViewAdapter();
        // 动态给ListView添加数据
        this.listView.setAdapter(adapter);
    }

    /**
     * 自定义视图适配器
     */
    public class CustomListXViewAdapter extends BaseAdapter {

        // 列表数据
        private List<String> data = new ArrayList<>();

        {
            //添加列表数据
            data.add("SDK福利闪兑收到了防嗮的了六点十分");
            data.add("大幅度所发生的方法的");
        }

        /**
         * 获取列表数据的数量
         * @return
         */
        @Override
        public int getCount() {
            return data.size();
        }

        /**
         * 获取单个数据对象
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        /**
         * 获取单个视图的ID
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 返回单个视图
         * @param position    下标
         * @param convertView 当前选项视图的缓存对象（可能为空）
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 如果当前选项的视图没有缓存就加载视图
            if(null == convertView) {
                convertView = View.inflate(Ui05ListViewComponentImageTextListView.this,R.layout.ui05_listview_c04_imagetext_textview,null);
            }
            // 获取当前选项的数据对象
            String text = data.get(position);
            // 获取单个选项里面的所有组件并给他们设置数据
            TextView nameView = convertView.findViewById(R.id.ui05_listview_c04_imagetext_textview_name);
            nameView.setText(text);
            return convertView;
        }
    }
}
