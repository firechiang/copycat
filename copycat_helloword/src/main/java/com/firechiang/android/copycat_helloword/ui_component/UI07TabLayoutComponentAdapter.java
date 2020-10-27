package com.firechiang.android.copycat_helloword.ui_component;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Fragment 和 ViewPage的适配器
 */
public class UI07TabLayoutComponentAdapter extends FragmentPagerAdapter {

    /**
     * 所有的Tab页
     */
    private List<Fragment> tabs;


    /**
     *
     * @param fm        Fragment 管理者
     * @param behavior  FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT（确定是否只有当前Fragment（视图碎片）处于恢复状态）
     */
    public UI07TabLayoutComponentAdapter(List<Fragment> tabs,@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tabs = tabs;
    }

    /**
     * 获取当前的视图碎片
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.tabs.get(position);
    }

    /**
     * 获取全部的视图碎片
     * @return
     */
    @Override
    public int getCount() {
        return this.tabs.size();
    }

    /**
     * 获取当前视图碎片的Title信息
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ((UI07TabLayoutComponentFragment)this.tabs.get(position)).getTitle();
    }
}
