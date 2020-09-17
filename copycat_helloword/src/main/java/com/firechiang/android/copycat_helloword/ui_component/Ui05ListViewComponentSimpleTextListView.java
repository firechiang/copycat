package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 简单显示文本列表的 ListView（注意：这个不能当成主 Activity）
 */
public class Ui05ListViewComponentSimpleTextListView extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui05_listview_c01_simpletext_listview);
        this.listView = findViewById(R.id.ui05_listview_c01_simpletext_listview);
        // 列表数据
        String[] data = new String[]{"A","B","C","D","E"};
        // 创建ArrayAdapter对象（注意：每一项都使用一个 R.layout.ui05_listview_c02_simpletext_textview 布局）
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.ui05_listview_c02_simpletext_textview,data);
        // 动态给ListView添加数据
        this.listView.setAdapter(adapter);
    }


}
