package com.firechiang.android.copycat_helloword.ui_example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.Arrays;
import java.util.List;

/**
 * GridView（栏珊）视图使用
 */
public class UiExample02Activity extends Activity {

    private GridView gridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_example02_activity);
        this.gridView = findViewById(R.id.ui_example02_activity_gridview);
        GridAdapter adapter = new GridAdapter();
        this.gridView.setAdapter(adapter);
        this.gridView.setOnItemClickListener(adapter);
    }

    public class GridAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

        private List<Item> data;

        public GridAdapter () {
            this.data = Arrays.asList(new Item("山东",R.mipmap.app01),
                                      new Item("大师",R.mipmap.app02),
                                      new Item("健康",R.mipmap.app03),
                                      new Item("欧派人投票",R.mipmap.app04),
                                      new Item("零零",R.mipmap.app05),
                                      new Item("对付他",R.mipmap.app06),
                                      new Item("婆婆",R.mipmap.app07),
                                      new Item("瑞特",R.mipmap.app08),
                                      new Item("发士大夫",R.mipmap.app09));
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
            Item item = data.get(position);
            if(convertView == null) {
                convertView = View.inflate(UiExample02Activity.this,R.layout.ui_example02_item,null);
            }
            ImageView image = convertView.findViewById(R.id.ui_example02_item_image);
            TextView name = convertView.findViewById(R.id.ui_example02_item_name);

            image.setImageResource(item.getIcon());
            name.setText(item.getName());
            return convertView;
        }

        /**
         * 单个选项点击事件监听
         * @param parent   GridView
         * @param view     当前选项视图
         * @param position 当前选项视图下标
         * @param id       当前选项视图ID
         */
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(UiExample02Activity.this,data.get(position).getName(),Toast.LENGTH_SHORT).show();
        }
    }

    public static class Item {

        private String name;
        private int icon;

        public Item() {
        }

        public Item(String name, int icon) {
            this.name = name;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public int getIcon() {
            return icon;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }
    }
}
