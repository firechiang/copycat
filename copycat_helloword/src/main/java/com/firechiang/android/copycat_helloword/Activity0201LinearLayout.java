package com.firechiang.android.copycat_helloword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * 测试跳转到当前Activity再返回到上一个Activity
 */
public class Activity0201LinearLayout extends Activity {

    private EditText editSecondMsessage;

    private Button btnSecondBack1;

    private Button btnSecondBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity0201_linear_layout);
        // 获取输入框视图
        this.editSecondMsessage = findViewById(R.id.edit_second_message);
        // 获取到跳转过来的意图对象
        Intent intent = this.getIntent();
        // 获取到跳转过来所携带的信息
        String message = intent.getStringExtra("message");
        // 设置输入框信息
        this.editSecondMsessage.setText(message);
    }

    /**
     * 返回按钮点击事件回调函数
     * @param view
     */
    public void back01(View view) {
        // 关闭当前Activity（注意：关闭当前Activity后会默认显示上一个Activity，也就实现了返回的功能）
        this.finish();
    }

    /**
     * 带结果返回按钮点击事件回调函数
     * @param view
     */
    public void back02(View view) {
        int resultCode = 3;
        String result = this.editSecondMsessage.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("result",result);
        // 回调上一个Activity的onActivityResult函数
        this.setResult(resultCode,intent);
        // 关闭当前Activity（注意：关闭当前Activity后会默认显示上一个Activity，也就实现了返回的功能）
        this.finish();
    }
}
