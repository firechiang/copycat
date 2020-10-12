package com.firechiang.android.copycat_helloword.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * App最终完成界面
 */
public class App01CompleteActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app01_complete_activity);
    }

    /**
     * 上一步
     * @param view
     */
    public void backAction(View view) {
        startActivity(new Intent(this,App01NextActivity.class));
    }

    /**
     * 完成
     * @param view
     */
    public void completeAction(View view) {

    }
}
