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
public class App01BackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app01_back_activity);
    }

    /**
     * 下一步
     * @param view
     */
    public void nextAction(View view) {
        startActivity(new Intent(this,App01NextActivity.class));
        /**
         * 指定显示和退出2个Activity的动画
         * @param enterAnim 要显示的Activity的动画
         * @param exitAnim  要关闭的Activity的动画
         */
        overridePendingTransition(R.anim.translate_activity_in_right,R.anim.translate_activity_out_left);
    }
}
