package com.firechiang.android.copycat_helloword.network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.firechiang.android.copycat_helloword.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 豆瓣热门电视剧第一页并且图片使用三级缓存
 */
public class NetWorkImageListView extends Activity {

    /**
     * 用于缓存图片文件
     */
    private Map<String,Bitmap> imageMap = new ConcurrentHashMap<>();
    // 用于发送HTTP请求
    RequestQueue mRequestQueue;
    // 豆瓣热门电视剧地址
    String url = "https://movie.douban.com/j/search_subjects?type=tv&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&page_start=0";
    // 数据列表视图
    private ListView listView;
    // 数据加载进度条
    private LinearLayout progressBar;
    // 消息处理器
    private Handler handler = new Handler() {
        /**
         * 处理消息更新UI视图
         * @param msg
         */
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 隐藏数据加载进度条
            progressBar.setVisibility(View.GONE);
            // 显示列表数据
            listView.setVisibility(View.VISIBLE);
            // 获取消息参数（就是列表数据）
            List<Item> data = (List<Item>)msg.obj;
            ListViewAdapter adapter = new ListViewAdapter(data);
            listView.setAdapter(adapter);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_image_listview);
        this.mRequestQueue = Volley.newRequestQueue(NetWorkImageListView.this);
        this.listView = findViewById(R.id.network_image_listview_listview);
        this.progressBar = findViewById(R.id.network_image_listview_progressbar);
        // 发送请求获取豆瓣热门电视剧
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray subjects = response.getJSONArray("subjects");
                    List<Item> data = new ArrayList<>();
                    for(int i=0;i<subjects.length();i++) {
                        JSONObject jsonObject = subjects.getJSONObject(i);
                        Item item = new Item(jsonObject.getString("title"), "豆瓣评分："+jsonObject.getString("rate"), jsonObject.getString("cover"));
                        data.add(item);
                    }
                    // 发送消息更新UI数据
                    Message msg = Message.obtain();
                    // 消息参数
                    msg.obj = data;
                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    Log.e("TAG","解析豆瓣热门电视剧JSON数据异常",e);
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG","发送请求："+url+"，异常",error.getCause());
            }
        });
        // 发送请求
        this.mRequestQueue.add(jsonObjectRequest);
    }


    public class ListViewAdapter extends BaseAdapter {

        private List<Item> data;

        public ListViewAdapter(List<Item> data) {
            this.data = data;
        }

        /**
         * 获取列表数据的数量
         * @return
         */
        @Override
        public int getCount() {
            return data.size();
        }

        /**
         * 获取单个数据对象
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 返回单个视图（注意：实际开发中不用手动做缓存，Volley其实已经做了缓存，这里我们手动做缓存的原因是想写一个手动做缓存的列子）
         * @param position    下标
         * @param convertView 当前选项视图的缓存对象（可能为空）
         * @param parent
         * @return
         */
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(null == convertView) {
                convertView = View.inflate(NetWorkImageListView.this,R.layout.network_image_listview_item,null);
            }
            // 获取自定义标识
            Object tag = convertView.getTag();
            // 如果自定义标识不等于1说明该View没有处理过（注意：这个处理标识只是做列子用的，实际开发看情况使用，建议把处理逻辑写到 if(null == convertView) 里面）
            if(!Integer.valueOf(1).equals(tag)) {
                Log.i("TAG","正在处理VIew");
                // 设置一个处理标识等于1
                convertView.setTag(Integer.parseInt("1"));
                Item item = data.get(position);

                final ImageView image = convertView.findViewById(R.id.network_image_listview_item_image);
                TextView title = convertView.findViewById(R.id.network_image_listview_item_title);
                TextView rate = convertView.findViewById(R.id.network_image_listview_item_rate);
                title.setText(item.getTitle());
                rate.setText(item.getRate());
                // 图片地址
                final String imageUrl = item.getUrl();
                // 图片数据（注意：实际开发中不用手动做缓存，Volley其实已经做了缓存，这里我们手动做缓存的原因是想写一个手动做缓存的列子）
                Bitmap imageBitmap = imageMap.get(imageUrl);
                if(imageBitmap != null) {
                    // 更新UI视图
                    image.setImageBitmap(imageBitmap);
                } else {
                    // 获取系统缓存目录
                    String cacheDirPath = getCacheDir().getAbsolutePath();
                    // 文件名称
                    String imageFileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
                    // 图片全路径
                    final String imageFilePath = cacheDirPath + File.separator + imageFileName;
                    final File imageFile = new File(imageFilePath);
                    // 如果图片文件已存在直接读取本地图片（注意：实际开发中不用手动做缓存，Volley其实已经做了缓存，这里我们手动做缓存的原因是想写一个手动做缓存的列子）
                    if(imageFile.exists()) {
                        // 加载本地图片文件
                        Bitmap bitmap1 = BitmapFactory.decodeFile(imageFilePath);
                        // 缓存图片
                        //imageMap.put(imageUrl,bitmap);
                        // 更新UI视图
                        image.setImageBitmap(bitmap1);
                        Log.i("TAG","从本地获取图片："+imageFilePath);
                    } else {
                        // 获取图片数据
                        ImageRequest imageRequest = new ImageRequest(imageUrl,
                                new Response.Listener<Bitmap>() {
                                    /**
                                     * 注意：实际开发中不用手动做缓存，Volley其实已经做了缓存，这里我们手动做缓存的原因是想写一个手动做缓存的列子
                                     * @param response
                                     */
                                    @Override
                                    public void onResponse(Bitmap response) {
                                        try {
                                            // 缓存图片到本地磁盘（注意：实际开发中不用手动做缓存，Volley其实已经做了缓存，这里我们手动做缓存的原因是想写一个手动做缓存的列子）
                                            FileOutputStream outputStream = new FileOutputStream(imageFile);
                                            /**
                                             * 压缩图片
                                             * @param CompressFormat format 格式
                                             * @param int quality           图片质量（0-100 注意：100等于不压缩）
                                             * @param OutputStream stream   输出流
                                             */
                                            response.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                                            outputStream.close();
                                        } catch (IOException e) {
                                            Log.e("TAG","缓存文件："+imageFilePath+"，到本地异常",e);
                                        }
                                        // 更新UI视图
                                        image.setImageBitmap(response);
                                    }
                                },
                                0,
                                0,
                                ImageView.ScaleType.CENTER_INSIDE,
                                Bitmap.Config.RGB_565,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // 获取图片失败使用默认图片
                                        image.setImageResource(R.drawable.ic_launcher_foreground);
                                    }
                                });
                        // 发送请求获取图片
                        mRequestQueue.add(imageRequest);
                    }
                }
            }
            return convertView;
        }
    }

    public class Item {
        /**
         * 电视剧名称
         */
        private String title;
        /**
         * 评分
         */
        private String rate;
        /**
         * 图片地址
         */
        private String url;

        public Item(String title, String rate, String url) {
            this.title = title;
            this.rate = rate;
            this.url = url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public String getRate() {
            return rate;
        }

        public String getUrl() {
            return url;
        }
    }
}
