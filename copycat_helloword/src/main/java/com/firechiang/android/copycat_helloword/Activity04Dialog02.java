package com.firechiang.android.copycat_helloword;

import android.app.Activity;
import android.os.Bundle;

/**
 * 弹出框形式的界面（注意：是不是弹出框只要在Activity上配置android:theme="@style/Theme.AppCompat.Dialog"即可）
 */
public class Activity04Dialog02 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity04_dialog02);
    }
}
