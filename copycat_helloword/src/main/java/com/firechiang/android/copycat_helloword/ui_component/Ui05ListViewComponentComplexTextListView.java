package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 显示复杂文本带图片的ListView（注意：这个不能当成主 Activity）
 */
public class Ui05ListViewComponentComplexTextListView extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui05_listview_c01_simpletext_listview);
        this.listView = findViewById(R.id.ui05_listview_c01_simpletext_listview);
        // 列表数据
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> item1 = new HashMap<>();
        item1.put("icon",R.mipmap.icon01);
        item1.put("name","美女");
        item1.put("content","也不知道哪来的美女");
        data.add(item1);

        Map<String,Object> item2 = new HashMap<>();
        item2.put("icon",R.mipmap.icon02);
        item2.put("name","夜景");
        item2.put("content","深圳的夜景还是很好看的");
        data.add(item2);

        // 自定义字段的名称数组
        String[] from = new String[]{"icon","name","content"};
        // 自定义名称对应的组件ID数组（就是 icon字段对应 ui05_listview_c03_simpletext_complexview_item_image 组件）
        int[] to = new int[]{R.id.ui05_listview_c03_simpletext_complexview_item_image,R.id.ui05_listview_c03_simpletext_complexview_item_name,R.id.ui05_listview_c03_simpletext_complexview_item_content};

        // 创建SimpleAdapter对象 （注意：每一项都使用一个 R.layout.ui05_listview_c03_simpletext_complexview 布局）
        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.ui05_listview_c03_simpletext_complexview,from,to);
        // 动态给ListView添加数据
        this.listView.setAdapter(adapter);
    }


}
