package com.firechiang.android.copycat_helloword.ui_component_senior;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firechiang.android.copycat_helloword.R;

/**
 * 多层菜单简单使用
 */
public class UI01YoukuMenu extends AppCompatActivity {


    private ImageView icon_home;
    private ImageView icon_menu;
    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;

    /**
     * 是否显示第三圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel3 = true;

    /**
     * 是否显示第二圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel2 = true;


    /**
     * 是否显示第一圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.ui_comsenior01_youku_menu);
        icon_home = (ImageView) findViewById(R.id.ui_comsenior01_youku_menu_icon_home);
        icon_menu = (ImageView) findViewById(R.id.ui_comsenior01_youku_menu_icon_menu);
        level1 = (RelativeLayout) findViewById(R.id.ui_comsenior01_youku_menu_level1);
        level2 = (RelativeLayout) findViewById(R.id.ui_comsenior01_youku_menu_level2);
        level3 = (RelativeLayout) findViewById(R.id.ui_comsenior01_youku_menu_level3);

        MyOnClickListener myOnClickListener = new MyOnClickListener();
        //设置点击事件
        icon_home.setOnClickListener(myOnClickListener);
        icon_menu.setOnClickListener(myOnClickListener);
        level1.setOnClickListener(myOnClickListener);
        level2.setOnClickListener(myOnClickListener);
        level3.setOnClickListener(myOnClickListener);
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ui_comsenior01_youku_menu_level1:
                    Toast.makeText(UI01YoukuMenu.this, "level1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ui_comsenior01_youku_menu_level2:
                    Toast.makeText(UI01YoukuMenu.this, "level2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ui_comsenior01_youku_menu_level3:
                    Toast.makeText(UI01YoukuMenu.this, "level3", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ui_comsenior01_youku_menu_icon_home://home
                    //如果三级菜单和二级菜单是显示，都设置隐藏
                    if (isShowLevel2) {
                        //隐藏二级菜单
                        isShowLevel2 = false;
                        Tools.hideView(level2);

                        if (isShowLevel3) {
                            //隐藏三级菜单
                            isShowLevel3 = false;
                            Tools.hideView(level3, 200);
                        }
                    } else {
                        //如果都是隐藏的，二级菜单显示
                        //显示二级菜单
                        isShowLevel2 = true;
                        Tools.showView(level2);

                    }


                    break;
                case R.id.ui_comsenior01_youku_menu_icon_menu://菜单

                    if (isShowLevel3) {
                        //隐藏
                        isShowLevel3 = false;
                        Tools.hideView(level3);
                    } else {
                        //显示
                        isShowLevel3 = true;
                        Tools.showView(level3);
                    }

                    break;
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_MENU){

            //如果一级，二级，三级菜单是显示的就全部隐藏
            if(isShowLevel1){
                isShowLevel1 = false;
                Tools.hideView(level1);
                if(isShowLevel2){
                    //隐藏二级菜单
                    isShowLevel2 = false;
                    Tools.hideView(level2,200);
                    if(isShowLevel3){
                        //隐藏三级菜单
                        isShowLevel3 = false;
                        Tools.hideView(level3,400);
                    }
                }
            }else{
                //如果一级，二级菜单隐藏，就显示
                //显示一级菜单
                isShowLevel1 = true;
                Tools.showView(level1);

                //显示二级菜单
                isShowLevel2 = true;
                Tools.showView(level2,200);
            }




            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 用于显示和隐藏指定控件工具
     */
    private static class Tools {
        public static void hideView(ViewGroup view) {
            hideView(view, 0);
        }

        public static void showView(ViewGroup view) {
            showView(view, 0);
        }

        public static void hideView(ViewGroup view, int startOffset) {
//        RotateAnimation ra = new RotateAnimation(0,180,view.getWidth()/2,view.getHeight());
//        ra.setDuration(500);//设置动画播放持续的时间
//        ra.setFillAfter(true);//动画停留在播放完成的状态
//        ra.setStartOffset(startOffset);//延迟多久后播放动画
//        view.startAnimation(ra);
//
//        for(int i = 0;i<view.getChildCount();i++){
//            View children = view.getChildAt(i);
//            children.setEnabled(false);
//        }
//        //设置不可以点击
////        view.setEnabled(false);

            //视图动画--属性动画
//        view.setRotation();

            ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",0,180);
            animator.setDuration(500);
            animator.setStartDelay(startOffset);
            animator.start();

            view.setPivotX(view.getWidth() / 2);
            view.setPivotY(view.getHeight());



        }

        public static void showView(ViewGroup view, int startOffset) {
//        RotateAnimation ra = new RotateAnimation(180,360,view.getWidth()/2,view.getHeight());
//        ra.setDuration(500);//设置动画播放持续的时间
//        ra.setFillAfter(true);//动画停留在播放完成的状态
//        ra.setStartOffset(startOffset);
//        view.startAnimation(ra);
//
////        view.setEnabled(true);
//        for(int i = 0;i<view.getChildCount();i++){
//            View children = view.getChildAt(i);
//            children.setEnabled(true);
//        }



            ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",180,360);
            animator.setDuration(500);
            animator.setStartDelay(startOffset);
            animator.start();

            view.setPivotX(view.getWidth() / 2);
            view.setPivotY(view.getHeight());

        }
    }
}
