package com.firechiang.android.copycat_helloword.content_resolver;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.Random;

/**
 * 四大组件之一内容解析器简单使用（注意：内容解析器其实就是操作其它App的数据库）
 * 注意：要测试这个类要先启动一下 copycat_helloword_service 模块，因为下面要操作的数据库其实就是这个App里面的
 */
public class ContentResolverTestActivity extends Activity {

    private String[] names = new String[]{"添Tina","马默","潘斌","将将","防方","可可","菲菲","LOMO","毛毛","水诉"};

    private Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resolver_test_activity);
    }

    /**
     * 添加数据到其它App的数据库
     * @param view
     */
    public void addData(View view) {
        int age = Math.abs(random.nextInt() % 10);
        String name = names[age];
        // 内容解析器
        ContentResolver contentResolver = getContentResolver();
        // 注意这个地址在 copycat_helloword_service 模块以配好，请过去查看
        Uri uri = Uri.parse("content://com.firechiang.android.copycat_helloword_service.MainActivity$UserContentProvider/person");
        // 字段的值
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        Uri insert = contentResolver.insert(uri, values);
        // 获取ID
        long id = ContentUris.parseId(insert);
        Toast.makeText(this,"数据插入成功ID="+id,Toast.LENGTH_SHORT).show();
    }

    /**
     * 修改其它App数据库里面的数据
     * @param view
     */
    public void updateData(View view) {
        int age = Math.abs(random.nextInt() % 10);
        String name = names[age];
        // 内容解析器
        ContentResolver contentResolver = getContentResolver();
        // 注意这个地址在 copycat_helloword_service 模块以配好，请过去查看
        Uri uri = Uri.parse("content://com.firechiang.android.copycat_helloword_service.MainActivity$UserContentProvider/person");
        // 字段的值
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        int update = contentResolver.update(uri, values, "name=?", new String[]{name});
        Toast.makeText(this,"修改数量="+update,Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除其它App数据库里面的数据
     * @param view
     */
    public void deleteData(View view) {
        String name = names[Math.abs(random.nextInt() % 10)];
        // 内容解析器
        ContentResolver contentResolver = getContentResolver();
        // 注意这个地址在 copycat_helloword_service 模块以配好，请过去查看
        Uri uri = Uri.parse("content://com.firechiang.android.copycat_helloword_service.MainActivity$UserContentProvider/person");
        int update = contentResolver.delete(uri, "name=?", new String[]{name});
        Toast.makeText(this,"删除数量="+update,Toast.LENGTH_SHORT).show();
    }

    /**
     * 查询其它App数据库里面的数据
     * @param view
     */
    public void queryData(View view) {
        // 内容解析器
        ContentResolver contentResolver = getContentResolver();
        // 注意这个地址在 copycat_helloword_service 模块以配好，请过去查看
        Uri uri = Uri.parse("content://com.firechiang.android.copycat_helloword_service.MainActivity$UserContentProvider/person");
        Cursor query = contentResolver.query(uri, new String[]{"_id", "name", "age"}, null, null, null);
        StringBuilder sb = new StringBuilder();
        // 是否有下一条数据
        while(query.moveToNext()) {
            String personName = query.getString(1);
            sb.append(personName).append(",");
        }
        Toast.makeText(this,"查询的数据有"+query.getCount()+"条，简要内容有："+sb.toString(),Toast.LENGTH_SHORT).show();
        query.close();
    }
}
