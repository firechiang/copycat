package com.firechiang.android.copycat_helloword.ui_component;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.firechiang.android.copycat_helloword.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab页的简单实现（注意：包含Fragment的Activity一定要从FragmentActivity继承）
 */
public class UI07TabLayoutComponent extends FragmentActivity {
    // Tab页的标题选项（注意：当前Activity必须设置一个主题否则TabLayout（Tab页的标题选项）无法初始化）
    private TabLayout tabLayout;
    // Tab页
    private ViewPager viewPager;

    private TextView textView;

    /**
     * 所有的Tab页
     */
    private List<Fragment> tabs;
    /**
     * Fragment 和 ViewPage的适配器
     */
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 必须设置一个主题否则TabLayout（Tab页的标题选项）无法初始化
        setTheme(R.style.AppTheme);
        setContentView(R.layout.ui07_tab_layout_component);
        this.tabLayout = findViewById(R.id.ui07_tab_layout_component_tablelayout);
        // 初始化 ViewPager
        this.viewPager = findViewById(R.id.ui07_tab_layout_component_viewpager);
        // 初始化顶部标题栏
        this.textView = findViewById(R.id.ui07_tab_layout_component_titlebar);
        // 初始化所有Tab页
        this.tabs = new ArrayList<>();
        for (int i=0;i<8;i++) {
            this.tabs.add(new UI07TabLayoutComponentFragment("标题"+i,"内容"+i));
        }
        // 初始化适配器
        this.adapter = new UI07TabLayoutComponentAdapter(tabs,getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // 设置适配器
        this.viewPager.setAdapter(adapter);
        // 为Tab页的标题选项设置Tab页数据
        this.tabLayout.setupWithViewPager(this.viewPager);
        // 设置Tab页的标题选项为滚动模式（就是和Tab页保持同步滚动）（TabLayout.MODE_FIXED=（固定不滚动））
        this.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
