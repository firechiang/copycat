package com.firechiang.android.copycat_helloword.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firechiang.android.copycat_helloword.R;

/**
 * App向导1界面
 */
public class App01NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app01_next_activity);
    }

    /**
     * 上一步
     * @param view
     */
    public void backAction(View view) {
        startActivity(new Intent(this,App01BackActivity.class));
    }

    /**
     * 下一部
     * @param view
     */
    public void nextAction(View view) {
        startActivity(new Intent(this,App01CompleteActivity.class));
    }
}
