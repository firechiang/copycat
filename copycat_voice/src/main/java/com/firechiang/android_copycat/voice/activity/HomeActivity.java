package com.firechiang.android_copycat.voice.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.firechiang.android_copycat.voice.R;
import com.firechiang.android_copycat.voice.adapter.HomePagerAdapter;
import com.firechiang.android_copycat.voice.model.Channel;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import static android.graphics.Color.WHITE;

/**
 * 主页面
 */
public class HomeActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private View mToggleView;
    private View mSearchView;
    private ViewPager mViewPager;
    // 自定义的适配器
    private HomePagerAdapter homePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initData();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggleView = findViewById(R.id.toggle_view);
        mSearchView = findViewById(R.id.search_view);
        mViewPager = findViewById(R.id.view_pager);
        // 自定义的适配器
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),Channel.values());
        mViewPager.setAdapter(homePagerAdapter);
        initMagicIndicator();
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 初始化指示器
     */
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundColor(WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return Channel.values().length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                // 设置文字
                simplePagerTitleView.setText(Channel.values()[index].getKey());
                // 设置字体大小
                simplePagerTitleView.setTextSize(19);
                simplePagerTitleView.setTypeface(Typeface.DEFAULT);
                // 默认颜色
                simplePagerTitleView.setNormalColor(Color.parseColor("#6600FF"));
                // 选择后的颜色
                simplePagerTitleView.setSelectedColor(Color.parseColor("#660033"));
                // 添加点击事件
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 切换页面
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        // 绑定组件
        ViewPagerHelper.bind(magicIndicator,mViewPager);
    }
}
