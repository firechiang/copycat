package com.firechiang.android.copycat_helloword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 测试跳转到另一个Activity并监听回调
 */
public class Activity02LinearLayout extends AppCompatActivity implements View.OnClickListener {

    private EditText editMainMsessage;

    private Button btnMainStart1;

    private Button btnMainStart2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.activity02_linear_layout);
        // 获取输入框视图
        this.editMainMsessage = findViewById(R.id.edit_main_message);
        // 获取两个按钮
        this.btnMainStart1 = findViewById(R.id.btn_main_start1);
        this.btnMainStart2 = findViewById(R.id.btn_main_start2);
        // 设置监听
        this.btnMainStart1.setOnClickListener(this);
        this.btnMainStart2.setOnClickListener(this);
    }

    /**
     * 监听按钮实现
     * @param
     */
    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        // 跳转界面
        if (viewId == R.id.btn_main_start1) {
            // 创建一个意图对象（要跳转到另一个Activity）
            Intent intent = new Intent(this,Activity0201LinearLayout.class);
            // 获取输入框里面的数据
            String message = this.editMainMsessage.getText().toString();
            // 跳转过去携带的数据，在另一个Activity里面可以获取到
            intent.putExtra("message",message);
            // 跳转到另一个Activity
            this.startActivity(intent);
        }
        // 带回调跳转界面（就是调整界面完成后再回调当前Activity的onActivityResult函数）
        if(viewId == R.id.btn_main_start2) {
            // 创建一个意图对象（要跳转到另一个Activity）
            Intent intent = new Intent(this,Activity0201LinearLayout.class);
            // 获取输入框里面的数据
            String message = this.editMainMsessage.getText().toString();
            // 跳转过去携带的数据，在另一个Activity里面可以获取到
            intent.putExtra("message",message);
            int requestCode = 2;
            // 跳转到另一个Activity，成功后回调当前Activity的onActivityResult函数（注意：requestCode再回调函数里面可以获取得到）
            this.startActivityForResult(intent,requestCode);
        }
    }

    /**
     * 带回调跳转界面成功后回调函数（注意：这个回调需要目的地的Activity调用setResult函数才会回调）
     * @param requestCode 请求码
     * @param resultCode  响应码
     * @param data        响应数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == 3) {
            String result = data.getStringExtra("result");
            // 设置输入框的数据
            this.editMainMsessage.setText(result);
        }
    }
}
