package com.firechiang.android.copycat_helloword.canvas;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 9patch图片操作相关（注意：9patch图片的应用场景是保证图片不会被拉伸所导致图片模糊与失真，一般使用纯色图片）
 * 制作9patch图片方法和步骤（首先图片一定要是png格，再选择图片点击右键，再选择 Create 9-Patch file 即可）
 */
public class GraphicalImageActivity05Patch9 extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphical_image_activity_05_patch9);
    }
}
