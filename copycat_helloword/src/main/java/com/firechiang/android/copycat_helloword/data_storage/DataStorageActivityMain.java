package com.firechiang.android.copycat_helloword.data_storage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 数据存储示列主Activity
 */
public class DataStorageActivityMain extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_storage_activity_main);
    }

    /**
     * 测试SD卡存储
     * @param v
     */
    public void toSdStorage(View v) {
        startActivity(new Intent(this,DataStorageActivitySd.class));
    }

    /**
     * 测试内部文件存储
     * @param v
     */
    public void toIfStorage(View v) {
        startActivity(new Intent(this,DataStorageActivityIf.class));
    }

    /**
     * 测试数据库存储
     * @param v
     */
    public void toDbStorage(View v) {

    }

    /**
     * 测试SharedPreference存储
     * @param v
     */
    public void toSpStorage(View v) {
        startActivity(new Intent(this,DataStorageActivitySp.class));
    }
}
