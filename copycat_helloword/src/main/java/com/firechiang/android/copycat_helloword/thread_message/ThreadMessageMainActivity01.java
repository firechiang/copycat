package com.firechiang.android.copycat_helloword.thread_message;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 使用Handler消息机制和不使用handler简单比较
 */
public class ThreadMessageMainActivity01 extends Activity {
    /**
     * 圆形进度条
     */
    private ProgressBar progressbar;

    private TextView textView;

    /**
     * 消息处理器
     */
    private Handler handler = new Handler() {
        /**
         * 只要发送消息就会回调这个函数（注意：这个实际是在主线程执行的，所以如果有网络请求，要再创建一个子线程才能做）
         * @param msg
         */
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.i("TAG","处理消息，参数 msg.what = "+msg.what);
            Log.i("TAG","处理消息，参数 msg.obj = "+msg.obj);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String baiduData = getBaiduData();
                        // 将返回的结果更新到UI界面（注意：更新UI一定要在子线程里面做）
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 更新UI数据
                                ThreadMessageMainActivity01.this.textView.setText(baiduData);
                                // 隐藏进度条
                                ThreadMessageMainActivity01.this.progressbar.setVisibility(View.GONE);
                            }
                        });
                    } catch (final IOException e) {
                        // 关闭Dialog
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("TAG","请求百度发送异常",e);
                                ThreadMessageMainActivity01.this.progressbar.setVisibility(View.GONE);
                                Toast.makeText(ThreadMessageMainActivity01.this,"请求发送异常，详情请查看日志",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }).start();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_message_main_activity_01);

        this.progressbar = findViewById(R.id.thread_message_main_activity_01_progressbar);
        this.textView = findViewById(R.id.thread_message_main_activity_01_textview);
    }

    /**
     * 不使用Handler的方式获取百度首页数据
     * @param view
     */
    public void notHandlerGetBaiduData(View view) {
        // 显示进度条
        this.progressbar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final String baiduData = getBaiduData();
                    // 将返回的结果更新到UI界面（注意：更新UI一定要在子线程里面做）
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 更新UI数据
                            ThreadMessageMainActivity01.this.textView.setText(baiduData);
                            // 隐藏进度条
                            ThreadMessageMainActivity01.this.progressbar.setVisibility(View.GONE);
                        }
                    });
                } catch (final IOException e) {
                    // 关闭Dialog
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("TAG","请求百度发送异常",e);
                            ThreadMessageMainActivity01.this.progressbar.setVisibility(View.GONE);
                            Toast.makeText(ThreadMessageMainActivity01.this,"请求发送异常，详情请查看日志",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 使用Handler的方式获取百度首页数据
     * @param view
     */
    public void useHandlerGetBaiduData(View view) {
        // 显示进度条
        this.progressbar.setVisibility(View.VISIBLE);
        // 获取一个Message实列
        Message msg = Message.obtain();
        msg.what = 1;
        msg.obj = "参数1";
        // 发送消息
        this.handler.sendMessage(msg);
    }

    /**
     * 获取百度首页的数据
     * @return
     * @throws IOException
     */
    private String getBaiduData() throws IOException {
        URL url = new URL("https://www.baidu.com");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        // 连接
        connection.connect();
        // body参数
        //OutputStream outputStream = connection.getOutputStream();
        //outputStream.write("name=sdasda".getBytes());
        int responseCode = connection.getResponseCode();
        // 响应成功
        // if(responseCode == 200) {}
        InputStream inputStream = connection.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = -1;
        while((len = inputStream.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        byte[] data = bos.toByteArray();
        bos.close();
        inputStream.close();
        return new String(data, StandardCharsets.UTF_8);
    }
}
