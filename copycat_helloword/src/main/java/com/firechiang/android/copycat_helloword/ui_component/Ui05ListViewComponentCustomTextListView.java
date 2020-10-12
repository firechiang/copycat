package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示自定义的ListView，主定义的主要是用于获取单个视图和添加相关事件（注意：这个不能当成主 Activity）
 */
public class Ui05ListViewComponentCustomTextListView extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui05_listview_c01_simpletext_listview);
        this.listView = findViewById(R.id.ui05_listview_c01_simpletext_listview);
        CustomListViewAdapter adapter = new CustomListViewAdapter();
        // 动态给ListView添加数据
        this.listView.setAdapter(adapter);
        // 滑到最底部的事件
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    /**
     * 自定义视图适配器
     */
    public class CustomListViewAdapter extends BaseAdapter {

        // 列表数据
        private  List<Item> data = new ArrayList<>();

        {
            //添加列表数据
            data.add(new Item("仿佛",R.mipmap.icon01,"SDK福利闪兑收到了防嗮的了六点十分"));
            data.add(new Item("南京",R.mipmap.icon02,"大幅度所发生的方法的"));
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
                convertView = View.inflate(Ui05ListViewComponentCustomTextListView.this,R.layout.ui05_listview_c03_simpletext_complexview,null);
            }
            // 获取当前选项的数据对象
            Item item = data.get(position);
            // 获取单个选项里面的所有组件并给他们设置数据
            // 注意：最好是写一个ViewHolder类里面存储的全部是该列表项里面的视图，然后利用 convertView.setTag(); 将ViewHolder存起来，以后直接从convertView.getTag()里面取视图而不是用findViewById
            ImageView imageView = convertView.findViewById(R.id.ui05_listview_c03_simpletext_complexview_item_image);
            TextView nameView = convertView.findViewById(R.id.ui05_listview_c03_simpletext_complexview_item_name);
            TextView contentView = convertView.findViewById(R.id.ui05_listview_c03_simpletext_complexview_item_content);

            imageView.setImageResource(item.getIcon());
            nameView.setText(item.getName());
            contentView.setText(item.getContent());
            return convertView;
        }
    }

    public static class Item {

        private String name;

        private int icon;

        private String content;

        public Item() {
        }

        public Item(String name, int icon, String content) {
            this.name = name;
            this.icon = icon;
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public int getIcon() {
            return icon;
        }

        public String getContent() {
            return content;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
