package com.firechiang.android.copycat_helloword.ui_component;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 当LIstView里面没有数据时，自动显示一个 TextView
 * 注意：ListActivity 里面必须有一个ListView和一个TextView而且ID必须使用系统定义的ID
 */
public class Ui05ListViewComponentListViewListView extends ListActivity {

    AppAdapter adapter = new AppAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui05_listview_c05_listview_listview);
        setListAdapter(adapter);
        // 给单个选项添加长按事件
        getListView().setOnItemLongClickListener(new ItemLongClickListener());
    }

    public class AppAdapter extends BaseAdapter {

        // 列表数据
        List<String> data = new ArrayList<>(Arrays.asList("A","B","C","D","E"));

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
                convertView = new TextView(Ui05ListViewComponentListViewListView.this);
            }
            TextView textView = (TextView)convertView;
            textView.setText(data.get(position));
            return textView;
        }

        public List<String> getData() {
            return data;
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
            Ui05ListViewComponentListViewListView.this.adapter.getData().remove(position);
            // 更新列表（刷新列表）（注意：这个函数使用的是缓存数据，不会去重新加载数据，故推荐生产使用）
            Ui05ListViewComponentListViewListView.this.adapter.notifyDataSetChanged();
            return true;
        }
    }
}
