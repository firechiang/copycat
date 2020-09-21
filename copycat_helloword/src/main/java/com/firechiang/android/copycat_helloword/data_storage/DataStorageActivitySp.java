package com.firechiang.android.copycat_helloword.data_storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 测试SharedPreference存储
 * SharedPreference存储专门用来存储一些单一的小数据，它是以 Key Value的方式进行存储，可存储的数据类型有 boolean，float，int，long，string
 * 数据保存在 /data/data/packageName/shared_prefs/yyy.xml 文件里面（注意：packageName就是app的名称，yyy是创建文件时所指定的名称），改文件可设置为只当前应用可读，其它应用不可读，应用删除时也会删除此文件
 *
 */
public class DataStorageActivitySp extends Activity {

    private EditText key;

    private EditText value;

    // 存储对象
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_storage_activity_sp);
        // 获取两个输入组件
        this.key = findViewById(R.id.data_storage_activity_sp_key);
        this.value = findViewById(R.id.data_storage_activity_sp_value);

        /**
         * 存储对象
         * @param name  存储的文件名称（可随便写）
         * @oparam mode 存储模型（Context.MODE_PRIVATE=只当前应用可使用，其它应用不可用）
         */
        this.sharedPreferences = getSharedPreferences("dataStorageActivitySp", Context.MODE_PRIVATE);
    }

    /**
     *  存储数据
     * @param v
     */
    public void saveData(View v) {
        String key = this.key.getText().toString();
        String value = this.value.getText().toString();
        // 获取存储对象的修改对象
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        edit.putString(key,value);
        // 将数据保存到文件
        edit.commit();
    }

    /**
     * 读取数据
     * @param v
     */
    public void queryData(View v) {
        String key = this.key.getText().toString();
        String value = this.sharedPreferences.getString(key,"未读取到 key = "+key+"的值");
        Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
    }

}
