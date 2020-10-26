package com.firechiang.android.copycat_helloword.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 视图碎片（用多个碎片组成一个视图）
 * 注意：每一个Fragment都会生成一个FragmentLayout然后将Fragment里面的视图加载到FragmentLayout布局里面
 */
public class TestFragments1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("我是左边的视图碎片");
        return textView;
    }
}
