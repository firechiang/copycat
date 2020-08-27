package com.firechiang.android.copycat_voice.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.firechiang.android.copycat_voice.model.Channel;
import com.firechiang.android.copycat_voice.view.MineFragment;

import static com.firechiang.android.copycat_voice.model.Channel.DISCORY_ID;
import static com.firechiang.android.copycat_voice.model.Channel.FRIEND_ID;
import static com.firechiang.android.copycat_voice.model.Channel.MINE_ID;
import static com.firechiang.android.copycat_voice.model.Channel.VIDEO_ID;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private Channel[] mList;

    public HomePagerAdapter(FragmentManager fm, Channel[] datas) {
        super(fm);
        mList = datas;
    }

    @Override
    public Fragment getItem(int position) {
        int type = mList[position].getValue();
        switch (type) {
            case MINE_ID:
                return MineFragment.newInstance();
            case DISCORY_ID:
                return MineFragment.newInstance();//DiscoryFragment.newInstance();
            case FRIEND_ID:
                return MineFragment.newInstance();//FriendFragment.newInstance();
            case VIDEO_ID:
                return MineFragment.newInstance();//VideoFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }
}
