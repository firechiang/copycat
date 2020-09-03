package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firechiang.android.copycat_helloword.R;

import java.util.Arrays;
import java.util.List;

/**
 * 线性布局以及常用简单的UI组件（TextView,EditView(输入框，密码输入框，数字输入框)，ImageView(图片组件),RadioGroup(单选组)，CheckBox（勾选组件））
 */
public class Ui01SimpleComponent extends Activity implements RadioGroup.OnCheckedChangeListener {

    private TextView textView;

    private EditText editTextPwd;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.ui01_simple_component);
        this.textView = findViewById(R.id.ui01SimpleComponentTextView01);
        this.textView.setText("代码里面修改过后的信息");
        this.editTextPwd = findViewById(R.id.ui01SimpleComponentEditText02);
        this.radioGroup = findViewById(R.id.ui01SimpleComponentRadioGroup);
        // 设置单选框选择事件（注意：这个事件是加载单选框组上面的）
        this.radioGroup.setOnCheckedChangeListener(this);
    }

    /**
     * 点击提交按钮事件回调函数
     * @param view
     */
    public void btnSubmit(View view) {
        String pwd = this.editTextPwd.getText().toString();
        // 提示输入的密码
        Toast.makeText(this,pwd,Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击图片事件回调函数
     * @param view
     */
    public void imageSwitch(View view) {
        ImageView iv = (ImageView)view;
        // 设置背景图片（注意：android.R.drawable 是去系统图片）
        iv.setBackgroundResource(android.R.drawable.alert_light_frame);
        // 设置前景图片（注意：android.R.drawable 是去系统图片）
        iv.setImageResource(android.R.drawable.ic_media_play);
    }

    /**
     * 点击显示被勾选的勾选框
     * @param view
     */
    public void btnCheck(View view) {
        CheckBox c1 = findViewById(R.id.ui01SimpleComponentCheckBox01);
        CheckBox c2 = findViewById(R.id.ui01SimpleComponentCheckBox02);
        CheckBox c3 = findViewById(R.id.ui01SimpleComponentCheckBox03);
        List<CheckBox> checkBoxes = Arrays.asList(c1, c2, c3);
        StringBuilder sb = new StringBuilder();
        for (CheckBox c:checkBoxes) {
            if(c.isChecked()) {
                sb.append(c1.getText().toString()).append(",");
            }
        }
        if(sb.length() != 0) {
            sb.deleteCharAt(sb.length()-1);
        }
        Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
    }

    /**
     * 单选框组选择事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = findViewById(checkedId);
        String text = rb.getText().toString();
        Toast.makeText(this,text+"被选中",Toast.LENGTH_SHORT).show();
    }

}
