package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 列表组件（注意：这个是主 Activity）
 * ListView是一种用来显示多个可滑动项（Item）列表的ViewGroup
 * ListView需要使用Adapter将数据和每一个Item所对应的布局动态添加到ListView
 */
public class Ui05ListViewComponent extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui05_listview_c);
    }

    /**
     * 显示简单文本列表
     * @param view
     */
    public void showSimpleTextListView(View view) {
        startActivity(new Intent(this,Ui05ListViewComponentSimpleTextListView.class));
    }

    /**
     * 显示复杂文本带图片的列表
     * @param view
     */
    public void showComplexTextListView(View view) {
        startActivity(new Intent(this, Ui05ListViewComponentComplexTextListView.class));
    }

    /**
     * 显示自定义的列表
     * @param view
     */
    public void showCustomTextListView(View view) {
        startActivity(new Intent(this,Ui05ListViewComponentCustomTextListView.class));
    }

    /**
     * 显示自定义的列表（TextView左边直接加图片）
     * @param view
     */
    public void showCustomXTextListView(View view) {
        startActivity(new Intent(this,Ui05ListViewComponentImageTextListView.class));
    }

    /**
     * ListView（列表）里面没有数据就自动显示一个TextView
     * @param view
     */
    public void showEmptyTextListView(View view) {
        startActivity(new Intent(this,Ui06ListViewComponentListViewListView.class));
    }


}
