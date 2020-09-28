package com.firechiang.android.copycat_helloword.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 自定义图片视图以达到自定义事件监听的效果
 */
public class MyImageView extends androidx.appcompat.widget.AppCompatImageView {

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("TAG","自定义的 "+MyImageView.class.getName()+" 被创建了");
    }
}
