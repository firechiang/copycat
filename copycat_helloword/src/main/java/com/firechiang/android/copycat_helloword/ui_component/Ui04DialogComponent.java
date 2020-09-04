package com.firechiang.android.copycat_helloword.ui_component;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firechiang.android.copycat_helloword.R;

import java.util.Calendar;

/**
 * 对话框组件（一般消息提示，带单选框的提示，自定义的单选框，日期选择弹出框）
 */
public class Ui04DialogComponent extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置主界面布局文件
        setContentView(R.layout.ui04_dialog_component);
    }

    /**
     * 显示一般的AlerttDialog
     * @param view
     */
    public void showCommonlyDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("删除数据")
                .setMessage("确定删除吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Ui04DialogComponent.this, "点击了确定", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Ui04DialogComponent.this, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 显示单选列表AlerttDialog
     * @param view
     */
    public void showRadioListDialog(View view) {
        final String[] items = {"红","篮","绿"};
        new AlertDialog.Builder(this)
                .setTitle("指定背景颜色")
                /**
                 *  items       选项名称列表
                 *  checkedItem 第几个被默认选择
                 *  listener    选择事件监听器
                 */
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Ui04DialogComponent.this,items[which]+"被选择",Toast.LENGTH_SHORT).show();
                        // 移除对话框
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * 显示自定义AlerttDialog
     * @param v
     */
    public void showCustomDialog(View v) {
        /**
         * context 上下文
         * viewID  视图ID
         * root    父组件
         */
        View view = View.inflate(this,R.layout.ui04_dialog_component_dialog01,null);
        final EditText username = view.findViewById(R.id.ui04_dialog_component_dialog01_username);
        final EditText pwd = view.findViewById(R.id.ui04_dialog_component_dialog01_pwd);
        new AlertDialog.Builder(this)
                // 设置布局文件
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String u  = username.getText().toString();
                        final String p = pwd.getText().toString();
                        Toast.makeText(Ui04DialogComponent.this, "username="+u+",pwd="+p, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Ui04DialogComponent.this,"点击取消",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }


    /**
     * 显示图形进度条AlerttDialog
     * @param v
     */
    public void showGraphicProgressDialog(View v) {
        /**
         * context 上下文
         * viewID  视图ID
         * root    父组件
         */
        View view = View.inflate(this,R.layout.ui04_dialog_component_dialog02,null);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("数据加载")
                .create();
        dialog.show();
        /**
         * 注意：如果需要等待不能在主线程做，否则ui无法渲染，还有子线程里面不能直接更新UI，需要借助 Handler 工具才能更新UI（注意：Handler 实际是将更新UI的任务交给了主线程）
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 移除对话框（注意：这个函数虽然在这里调用，但更新UI还是在主线里面）
                dialog.dismiss();
                // 更新UI（注意：它的内部原理还是用 Handler）
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Ui04DialogComponent.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    /**
     * 显示水平进度条AlerttDialog
     * @param v
     */
    public void showHorizontalProgressDialog(View v) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show();
        /**
         * 注意：如果需要等待不能在主线程做，否则ui无法渲染，还有子线程里面不能直接更新UI，需要借助 Handler 工具才能更新UI（注意：Handler 实际是将更新UI的任务交给了主线程）
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<11;i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 注意：这个函数不是直接更新UI
                    pd.setProgress(i * 10);
                }
                // 移除对话框（注意：这个函数虽然在这里调用，但更新UI还是在主线里面）
                pd.dismiss();
                // 更新UI（注意：它的内部原理还是用 Handler）
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Ui04DialogComponent.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }


    /**
     * 显示选择日期AlerttDialog
     * @param v
     */
    public void showDateDialog(View v) {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        /**
         * context  上下文
         * listener 选择事件
         * year     默认年
         * month    默认月
         * day      默认日
         */
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(Ui04DialogComponent.this,"year="+year+",month="+month+",day="+dayOfMonth,Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);
        // 显示组件
        datePickerDialog.show();
    }

    /**
     * 显示选择时分秒AlerttDialog
     * @param v
     */
    public  void showTimeDialog(View v) {
        Calendar instance = Calendar.getInstance();
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        /**
         * context      上下文
         * listener     选择事件
         * hour         默小时
         * minute       默认分钟
         * is24HourView 是否24小时制
         */
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(Ui04DialogComponent.this, "hourOfDay=" + hourOfDay + ",minute=" + minute, Toast.LENGTH_SHORT).show();
            }
        }, hour, minute, true);
        // 显示组件
        timePickerDialog.show();
    }
}
