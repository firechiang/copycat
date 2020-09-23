package com.firechiang.android.copycat_helloword.network;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firechiang.android.copycat_helloword.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 网络请求相关主 Activity（注意：发送请求必须在子线程里面做否则报错）
 * 注意：如果报没有请求网络的权限就将APP卸载，重新安装即可
 */
public class NetWorkMainActivity extends Activity {

    private EditText editText;

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_main_activity);
        this.editText = findViewById(R.id.network_main_activity_url);
        this.textView = findViewById(R.id.network_main_activity_response);
    }

    /**
     * 使用HttpUrlConnection发送GET请求
     * @param view
     */
    public void httpUrlConnectionGet(View view) {
        this.textView.setText("");
        httpUrlConnectionExce("GET");
    }

    /**
     * 使用HttpUrlConnection发送POST请求
     * @param view
     */
    public void httpUrlConnectionPost(View view) {
        this.textView.setText("");
        //this.editText.setText("https://obspapi.ssssat.com/obsp");
        httpUrlConnectionExce("POST");
    }

    /**
     * 使用Volley发送GET请求
     * @param view
     */
    public void volleyGet(View view) {
        if(!permission()) {
            Toast.makeText(this,"没有网络请求权限",Toast.LENGTH_SHORT).show();
            return;
        }
        textView.setText("");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 注意这个Dialog已经废弃（不建议使用，推荐查看Dialog列子使用）
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在加载...");
        final String urlStr = editText.getText().toString();
        // String类型的返回值请求
        StringRequest request = new StringRequest(urlStr, new Response.Listener<String>() {
            /**
             * 成功返回
             * @param response
             */
            @Override
            public void onResponse(String response) {
                textView.setText(response);
                // 关闭 Dialog
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            /**
             * 错误处理
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }

    /**
     * 使用Volley发送POST请求（注意：不能使用Volley进行下载，否则报错）
     * @param view
     */
    public void volleyPost(View view) {
        if(!permission()) {
            Toast.makeText(this,"没有网络请求权限",Toast.LENGTH_SHORT).show();
            return;
        }
        textView.setText("");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // 注意这个Dialog已经废弃（不建议使用，推荐查看Dialog列子使用）
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在加载...");
        final String urlStr = editText.getText().toString();
        // String类型的返回值请求
        StringRequest request = new StringRequest(Request.Method.POST,urlStr, new Response.Listener<String>() {
            /**
             * 成功返回
             * @param response
             */
            @Override
            public void onResponse(String response) {
                textView.setText(response);
                // 关闭 Dialog
                dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            /**
             * 错误处理
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            /**
             * 传递参数
             * @return
             * @throws AuthFailureError
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return super.getParams();
            }
        };
        requestQueue.add(request);
    }

    /**
     * 使用 HttpUrlConnection 发送请求（注意：不能使用Volley进行下载，否则报错）
     * @param method
     */
    private void httpUrlConnectionExce(final String method) {
        // 注意这个Dialog已经废弃（不建议使用，推荐查看Dialog列子使用）
        final ProgressDialog dialog = ProgressDialog.show(this, null, "正在加载...");
        final String urlStr = editText.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod(method);
                    connection.setConnectTimeout(3000);
                    connection.setReadTimeout(3000);
                    Log.i("TAG","请求发送成功："+urlStr);
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
                    final String response = new String(data, StandardCharsets.UTF_8);
                    // 将返回的结果更新到UI界面
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(response);
                            // 移除Dialog（弹出框）
                            dialog.dismiss();
                        }
                    });
                } catch (IOException e) {
                    Log.e("TAG","请求地址"+urlStr+"，发送异常",e);
                    // 关闭Dialog
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 移除Dialog（弹出框）
                            dialog.dismiss();
                            Toast.makeText(NetWorkMainActivity.this,"请求发送异常，详情请查看日志",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 验证网络请求权限
     * @return
     */
    public boolean permission() {
        // 确定有网络权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            //没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                //弹窗解释为何需要该权限，再次请求权限
                Toast.makeText(this, "请授予网络请求权限！", Toast.LENGTH_LONG).show();
                //跳转到应用设置授权界面
                Intent newIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                newIntent.setData(uri);
                startActivity(newIntent);
            } else {
                //不需要解释为何需要授权直接请求授权（注意：requestCode会原封不动的返回给授权回调函数 onRequestPermissionsResult）
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
            }
            // 有权限
        }else{
            return true;
        }
        return false;
    }

}
