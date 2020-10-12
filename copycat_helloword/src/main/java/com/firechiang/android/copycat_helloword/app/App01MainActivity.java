package com.firechiang.android.copycat_helloword.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 动画示例App最终主界面（主要实现输入框抖动动画）
 */
public class App01MainActivity extends Activity {

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app01_main_activity);
        this.editText = findViewById(R.id.app01_main_activity_username);
    }

    /**
     * 登陆按钮点击事件
     * @param view
     */
    public void loginAction(View view) {
        String username = this.editText.getText().toString();
        // 如果输入框里面没有内容就显示抖动动画
        if(username.isEmpty()) {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate_shake);
            // 开始动画
            this.editText.startAnimation(animation);
            // 清除动画
            //this.editText.clearAnimation();
        }
    }
}
