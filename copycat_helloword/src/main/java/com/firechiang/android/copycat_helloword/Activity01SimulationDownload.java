package com.firechiang.android.copycat_helloword;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 模拟下载小程序
 */
public class Activity01SimulationDownload extends AppCompatActivity {

    private Button buttonDownload;

    /**
     * 主界面创建的时候回调
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.activity01_simulation_download);
        // 获取下载按钮对象
        this.buttonDownload = (Button)this.findViewById(R.id.simulation_download_but_down);
        // 设置下载按钮点击事件
        this.buttonDownload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /**
                 * 创建提示信息
                 * @param context   提示信息的上下文（就是提示信息要显示在哪个Activity上）
                 * @param text      提示文本
                 * @param duration  提示信息显示时间长短（注意：这个只有两种选择一种是Toast.LENGTH_LONG(长),一种是Toast.LENGTH_SHORT（短））
                 */
                // 获取外部类对象，需要加上外部类类名。如果直接写this，它代表的是View.OnClickListener对象，这个和JAVA有所不同，需要注意
                Toast toast = Toast.makeText(Activity01SimulationDownload.this, "开始下载...", Toast.LENGTH_SHORT);
                // 显示提示信息
                toast.show();
                // 更新按钮文本信息
                buttonDownload.setText("正在下载中...");
            }
        });
    }
}
