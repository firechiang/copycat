package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.firechiang.android.copycat_helloword.R;

/**
 * 常用菜单组件简单使用（包括 OptionsMenu（选项菜单），ContextMenu（上下文菜单））
 */
public class Ui02MenuComponent extends Activity  {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.ui02_menu_component);
        this.button = findViewById(R.id.ui02MenuComponent_btn01);
        // 设置长按Button显示ContextMenu（上下文菜单），为什么是长按我也不清楚
        this.button.setOnCreateContextMenuListener(this);
    }

    /**
     * 当OptionsMenu（选项菜单）被创建时回调
     * 注意：新的模拟器没有Menu按键，可使用 Ctrl + M 快捷键模拟Menu按键
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 通过编码的方式向OptionsMenu（选项菜单）里面添加Item（选项）
         * groupId 分组ID（可随便传）
         * itemId  选项ID
         * order   排序
         * title   选项名称
         */
        menu.add(0,2,0,"添加");
        menu.add(0,3,0,"删除");
        /**
         * 通过Menu（菜单）文件的方式向OptionsMenu（菜单）里面添加Item（选项）
         */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.ui02_menu_component_option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *  OptionsMenu（选项菜单）里面的Item（选项）被选择时回调
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        Toast.makeText(this,title+"被选择",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    /**
     * 当ContextMenu（上下文菜单）被创建时回调
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        /**
         * 通过编码的方式向ContextMenu（上下文菜单）里面添加Item（选项）
         * groupId 分组ID（可随便传）
         * itemId  选项ID
         * order   排序
         * title   选项名称
         */
        menu.add(0,2,0,"添加");
        menu.add(0,3,0,"删除");
        /**
         * 通过Menu（菜单）文件的方式向ContextMenu（上下文菜单）里面添加Item（选项）
         */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.ui02_menu_component_option_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /**
     *  ContextItem（上下文菜单）里面的Item（选项）被选择时回调
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        Toast.makeText(this,title+"被选择",Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
