package com.firechiang.android.copycat_helloword.ui_component;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 视图碎片（这里是一个Tab页）
 * 注意：每一个Fragment都会生成一个FragmentLayout然后将Fragment里面的视图加载到FragmentLayout布局里面
 */
public class UI07TabLayoutComponentFragment extends Fragment {

    private TextView textView;

    /**
     * Tab页的标题
     */
    private String title;
    /**
     * Tab页的内容
     */
    private String content;

    public UI07TabLayoutComponentFragment(String title,String content) {
        super();
        this.title = title;
        this.content = content;
    }

    /**
     * 创建视图碎片里面的子视图
     * 注意：每一个Fragment都会生成一个FragmentLayout然后将Fragment里面的视图加载到FragmentLayout布局里面
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.textView = new TextView(getActivity());
        this.textView.setTextColor(Color.RED);
        // 文字居中
        this.textView.setGravity(Gravity.CENTER);
        this.textView.setTextSize(20);
        return this.textView;
    }

    /**
     * 在Activity创建完成以后绑定数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 设置数据
        this.textView.setText(content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
