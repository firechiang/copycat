package com.firechiang.android.copycat_helloword.content_resolver;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选择联系人简单使用
 */
public class ContentResolverContactsActivity extends Activity {

    private static int requestCode = 1;

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_resolver_contacts_activity);
        this.editText = findViewById(R.id.content_resolver_contacts_activity_phone);
    }

    /**
     * 选择联系人
     * @param view
     */
    public void selectContact(View view) {
        startActivityForResult(new Intent(this,ContactsListActivity.class),requestCode);
    }

    /**
     * 选择联系人后的回调函数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 返回成功（注意：这个 resultCode 是跳转后的那个Activity在关闭之前设置的，详情请查看164行代码）
        if(resultCode == Activity.RESULT_OK) {
            String name = data.getStringExtra(Phone.DISPLAY_NAME);
            String number = data.getStringExtra(Phone.NUMBER);
            this.editText.setText(number);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 联系人列表
     * 注意：ListActivity 里面必须有一个ListView而且ID必须使用系统定义的ID
     */
    public static class ContactsListActivity extends ListActivity {

        private ListView listView;

        private ContactsAdapter adapter;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_resolver_contacts_activity_listview);
            this.listView = getListView();

            ContentResolver contentResolver = getContentResolver();
            // 查询所联系人（Phone.DISPLAY_NAME=联系名称, Phone.NUMBER=手机号）
            Cursor cursor = contentResolver.query(Phone.CONTENT_URI, new String[]{Phone.DISPLAY_NAME, Phone.NUMBER}, null, null, null);

            List<Map<String,String>> data = new ArrayList<>();
            while(cursor.moveToNext()) {
                String name = cursor.getString(0);
                String number = cursor.getString(1);
                Map<String,String> map = new HashMap<>();
                map.put(Phone.DISPLAY_NAME,name);
                map.put(Phone.NUMBER,number);
                data.add(map);
            }
            this.adapter = new ContactsAdapter(data);
            // 设置数据适配器
            this.setListAdapter(adapter);
            // 添加点击事件监听
            this.listView.setOnItemClickListener(adapter);
        }

        /**
         * 适配列表数据
         */
        public class ContactsAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

            private List<Map<String,String>> data;

            public ContactsAdapter(List<Map<String,String>> data) {
                this.data = data;
            }

            /**
             * 列表数据个数
             * @return
             */
            @Override
            public int getCount() {
                return data.size();
            }

            /**
             * 单个数据
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

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null) {
                    convertView = View.inflate(ContactsListActivity.this,R.layout.content_resolver_contacts_activity_item,null);
                }
                TextView nameView = convertView.findViewById(R.id.content_resolver_contacts_activity_item_name);
                TextView phoneView = convertView.findViewById(R.id.content_resolver_contacts_activity_item_phone);

                Map<String, String> stringObjectMap = data.get(position);
                nameView.setText(stringObjectMap.get(Phone.DISPLAY_NAME));
                phoneView.setText(stringObjectMap.get(Phone.NUMBER));
                return convertView;
            }

            /**
             * 点击联系人事件监听
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String> stringStringMap = data.get(position);
                String name = stringStringMap.get(Phone.DISPLAY_NAME);
                String number = stringStringMap.get(Phone.NUMBER);

                Intent intent = getIntent();
                intent.putExtra(Phone.DISPLAY_NAME,name);
                intent.putExtra(Phone.NUMBER,number);
                // 设置跳转回调结果（Activity.RESULT_OK=成功），注意：这个设置完成，只要关闭当前页面就会回调上一个Activity的onActivityResult()函数
                ContactsListActivity.this.setResult(Activity.RESULT_OK,intent);
                // 关闭当前页面
                ContactsListActivity.this.finish();
            }
        }
    }
}
