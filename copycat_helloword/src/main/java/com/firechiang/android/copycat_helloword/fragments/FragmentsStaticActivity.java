package com.firechiang.android.copycat_helloword.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.firechiang.android.copycat_helloword.R;

/**
 * 静态加载组合Fragment（视图碎片）
 */
public class FragmentsStaticActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_test_activity);
    }
}
