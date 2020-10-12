package com.firechiang.android.copycat_helloword.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firechiang.android.copycat_helloword.R;

/**
 * 动画示例App向导1界面
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
        //startActivity(new Intent(this,App01BackActivity.class));
        // 关闭当前Activity会默认跳转至上一个Activity
        finish();
        /**
         * 指定显示和退出2个Activity的动画
         * @param enterAnim 要显示的Activity的动画
         * @param exitAnim  要关闭的Activity的动画
         */
        overridePendingTransition(R.anim.translate_activity_in_left,R.anim.translate_activity_out_right);
    }

    /**
     * 完成
     * @param view
     */
    public void completeAction(View view) {
        startActivity(new Intent(this, App01MainActivity.class));
    }
}
