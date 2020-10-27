package com.firechiang.android.copycat_helloword.ui_component_senior;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.firechiang.android.copycat_helloword.R;

/**
 * 测试使用仿 ViewPage 视图做轮播
 */
public class UI06ImitateViewPage extends Activity {

    private RadioGroup rg_main;

    private UI06ImitateViewPageView myviewpager;

    private int[] ids = {R.mipmap.imitateviewpage_a1, R.mipmap.imitateviewpage_a2, R.mipmap.imitateviewpage_a3, R.mipmap.imitateviewpage_a4, R.mipmap.imitateviewpage_a5, R.mipmap.imitateviewpage_a6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_comsenior06_imitateviewpage);
        myviewpager = (UI06ImitateViewPageView) findViewById(R.id.ui_comsenior06_imitateviewpage_myviewpager);
        rg_main = (RadioGroup) findViewById(R.id.ui_comsenior06_imitateviewpage_rg_main);

        //添加6页面
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);

            //添加到MyViewPager这个View中
            myviewpager.addView(imageView);
        }

        //添加测试页面
        View testview = View.inflate(this,R.layout.ui_comsenior06_imitateviewpage_test,null);
        myviewpager.addView(testview,2);


        for(int i=0;i<myviewpager.getChildCount();i++){
            RadioButton button = new RadioButton(this);
            button.setId(i);//0~5的id

            if(i==0){
                button.setChecked(true);
            }

            //添加到RadioGroup
            rg_main.addView(button);
        }




        //设置RadioGroup选中状态的变化
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *
             * @param group
             * @param checkedId : 0~5之间
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                myviewpager.scrollToPager(checkedId);//根据下标位置定位到具体的某个页面
            }
        });


        //设置监听页面的改变
        MyOnPagerChangListenter myOnPagerChangListenter = new MyOnPagerChangListenter();
        myviewpager.setOnPagerChangListenter(myOnPagerChangListenter);

    }

    class MyOnPagerChangListenter implements UI06ImitateViewPageView.OnPagerChangListenter {

        @Override
        public void onScrollToPager(int position) {
            rg_main.check(position);
        }
    }
}
