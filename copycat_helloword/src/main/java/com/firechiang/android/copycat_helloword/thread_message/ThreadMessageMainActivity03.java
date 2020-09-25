package com.firechiang.android.copycat_helloword.thread_message;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * AsyncTask 异步任务简单使用（以后刷新UI视图可以使用这个来做，因为它其实就是一个线程池）
 */
public class ThreadMessageMainActivity03 extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_message_main_activity_03);

        this.textView = findViewById(R.id.thread_message_main_activity_03_textview);
    }

    /**
     * 获取百度首页的数据
     * @param view
     */
    public void baiduData(View view) {
        this.textView.setText("");
        /**
         * Params  （参数类型）
         * Progress（进度函数onProgressUpdate的参数类型）
         * Result  （返回值类型）
         */
        AsyncTask<Void, String, Void> task = new AsyncTask<Void,String,Void>() {
            String baiduData = "";
            /**
             * 任务执行函数体
             * @param params 参数（注意：这个参数是调用 execute函数传过来的）
             * @return
             */
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    this.baiduData = getBaiduData();
                    // 在主线程更新进度或视图（注意：调用这个函数最后会调用到我们自己实现的 onProgressUpdate() 函数，参数也会传到 onProgressUpdate() 函数）
                    // 注意：这个一般用于进度条设置进度使用
                    this.publishProgress(baiduData);
                    // 更新UI视图（不推荐使用，建议使用 onPostExecute 函数）
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ThreadMessageMainActivity03.this.textView.setText(baiduData);
//                        }
//                    });
                } catch (IOException e) {
                    Log.e("TAG","获取百度首页数据异常",e);
                }
                return null;
            }

            /**
             * 任务执行完成回调（注意：更新UI视图就在这个函数里面做）
             * @param aVoid
             */
            @Override
            protected void onPostExecute(Void aVoid) {
                //ThreadMessageMainActivity03.this.textView.setText(baiduData);
            }

            /**
             * 任务执行之前回调（注意：在这里面可更新UI视图）
             */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            /**
             * 在主线程更新进度或视图（注意：这个函数是需要当前对象调用 publishProgress()函数回调的，也就是调用publishProgress()函数传了什么参数，那些参数就会传到这里来）
             * 注意：这个一般用于进度条设置进度使用
             * @param values
             */
            @Override
            protected void onProgressUpdate(String... values) {
                ThreadMessageMainActivity03.this.textView.setText(baiduData);
            }
        };
        /**
         * 执行任务
         * @param params 执行任务所要的参数
         */
        task.execute();
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
