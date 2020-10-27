package com.firechiang.android.copycat_helloword.ui_component_senior;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;

/**
 * 测试侧滑菜单视图（就是向左滑动显示删除按钮）
 */
public class UI08SideslipMenu extends AppCompatActivity {

    private ListView lv_main;

    private ArrayList<UI08SideslipMenuBean> myBeans;

    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.ui_comsenior08_sideslip_menu);
        lv_main = (ListView) findViewById(R.id.ui_comsenior08_sideslip_menu_lv_main);

        //设置适配器
        //准备数据
        myBeans = new ArrayList<>();
        for(int i=0;i<100;i++){
            myBeans.add(new UI08SideslipMenuBean("Content"+i));
        }
        myAdapter = new MyAdapter();
        lv_main.setAdapter(myAdapter);

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView ==null){
                convertView = View.inflate(UI08SideslipMenu.this,R.layout.ui_comsenior08_sideslip_menu_item_main,null);
                viewHolder = new ViewHolder();
                viewHolder.item_content = (TextView) convertView.findViewById(R.id.ui_comsenior08_sideslip_menu_item_main_item_content);
                viewHolder.item_menu = (TextView) convertView.findViewById(R.id.ui_comsenior08_sideslip_menu_item_main_item_menu);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到内容
            final UI08SideslipMenuBean myBean = myBeans.get(position);
            viewHolder.item_content.setText(myBean.getName());

            viewHolder.item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UI08SideslipMenuBean myBean1 = myBeans.get(position);
                    Toast.makeText(UI08SideslipMenu.this, myBean1.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.item_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    UI08SideslipMenuSlideLayout slideLayout = (UI08SideslipMenuSlideLayout) v.getParent();
                    slideLayout.closeMenu();
                    myBeans.remove(myBean);
//                    myAdapter.notifyDataSetChanged();
                    notifyDataSetChanged();
                }
            });

            UI08SideslipMenuSlideLayout slideLayout = (UI08SideslipMenuSlideLayout) convertView;
            slideLayout.setOnStateChangeListenter(new MyOnStateChangeListenter());

            return convertView;
        }
    }

    private UI08SideslipMenuSlideLayout slideLayout;

    class MyOnStateChangeListenter implements UI08SideslipMenuSlideLayout.OnStateChangeListenter {

        @Override
        public void onClose(UI08SideslipMenuSlideLayout layout) {
            if(slideLayout ==layout){
                slideLayout = null;
            }

        }

        @Override
        public void onDown(UI08SideslipMenuSlideLayout layout) {
            if(slideLayout != null && slideLayout!=layout){
                slideLayout.closeMenu();
            }

        }

        @Override
        public void onOpen(UI08SideslipMenuSlideLayout layout) {
            slideLayout = layout;
        }
    }

    static class ViewHolder{
        TextView item_content;
        TextView item_menu;
    }
}
