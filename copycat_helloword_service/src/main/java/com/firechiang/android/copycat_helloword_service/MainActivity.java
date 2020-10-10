package com.firechiang.android.copycat_helloword_service;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 这个应用作为Service（服务端）提供接口 和 数据库数据供其他APP使用
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 内容提供者（注意：这个是将自己App的数据库提供给其它App使用，也就是下面的函数其实是给其它App使用的）
     * 注意：内容提供者需要在 AndroidManifest.xml 配置文件里面配置
     */
    public static class UserContentProvider extends ContentProvider {

        static int queryNotIdCode = 1;

        static int queryByIdCode = 2;

        // 这个就是我们在 AndroidManifest.xml 配置文件里面配置的内容提供者的唯一标识
        private static final String authorities = "com.firechiang.android.copycat_helloword_service.MainActivity$UserContentProvider";

        // 用来存放所有合法的Uri的容器
        private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // 添加自定义合法Uri
        static {
            /**
             * 不根据ID查询的合法Uri
             * @param authorities 提供者的唯一标识（这个就是我们在 AndroidManifest.xml 配置文件里面配置的内容提供者的唯一标识）
             * @param path        合法Uri的路径（注意：这个一般用表名来做）
             * @param code        此Uri的唯一标识
             * content://com.firechiang.android.copycat_helloword_service.MainActivity$UserContentProvider/person
             */
            uriMatcher.addURI(authorities,"/person",queryNotIdCode);
            /**
             * 根据ID查询的合法Uri（注意： # 表示匹配任意数字）
             * @param authorities 提供者的唯一标识（这个就是我们在 AndroidManifest.xml 配置文件里面配置的内容提供者的唯一标识）
             * @param path        合法Uri的路径（注意：这个一般用表名来做）
             * @param code        此Uri的唯一标识
             * content://com.firechiang.android.copycat_helloword_service.MainActivity$UserContentProvider/person/1
             */
            uriMatcher.addURI(authorities,"/person/#",queryByIdCode);
        }

        private SqlUtil sqlUtil;

        @Override
        public boolean onCreate() {
            /**
             * 创建数据库以及数据库连接工具
             * @param context 上下文
             * @param name    存储数据的文件名称
             * @param factory 游标工程
             * @param version 版本号（可以顺便写，如果版本号和以前的不一样就会更新数据版本，注意一点，数据版本不能降级否则报错）
             */
            sqlUtil = new SqlUtil(getContext(),"copycat_helloword_service.db",null,1);
            Log.i("TAG","内容提供者"+UserContentProvider.class.getName()+"创建成功");
            return false;
        }

        /**
         * 注意：这个查询完成以后连接不能关，关了客户端就得不到数据了
         * @param uri
         * @param projection
         * @param selection
         * @param selectionArgs
         * @param sortOrder
         * @return
         */
        @Nullable
        @Override
        public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
            /**
             * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
             * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
             * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
             */
            SQLiteDatabase database = sqlUtil.getReadableDatabase();
            int match = uriMatcher.match(uri);
            // 不根据ID查询
            if(match == queryNotIdCode) {
                return database.query("person",projection,selection,selectionArgs,null,null,null);
            }
            // 根据ID查询
            if(match == queryByIdCode) {
                // 获取ID
                long id = ContentUris.parseId(uri);
                return database.query("person",projection,"_id=?",new String[]{String.valueOf(id)},null,null,null);
            }
            return null;
        }

        @Nullable
        @Override
        public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
            /**
             * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
             * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
             * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
             */
            SQLiteDatabase database = sqlUtil.getReadableDatabase();
            try {
                // 匹配URI
                int match = uriMatcher.match(uri);
                if (match == queryNotIdCode || match == queryByIdCode) {
                    long id = database.insert("person", null, values);
                    // 包装ID返回
                    uri = ContentUris.withAppendedId(uri, id);
                }
            }finally{
                database.close();
            }
            return uri;
        }

        @Override
        public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
            /**
             * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
             * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
             * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
             */
            SQLiteDatabase database = sqlUtil.getReadableDatabase();
            try {
                // 匹配URI
                int match = uriMatcher.match(uri);
                if (match == queryNotIdCode || match == queryByIdCode) {
                    return database.delete("person", selection, selectionArgs);
                }
            }finally{
                database.close();
            }
            return 0;
        }

        @Override
        public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
            /**
             * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
             * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
             * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
             */
            SQLiteDatabase database = sqlUtil.getReadableDatabase();
            try {
                // 匹配URI
                int match = uriMatcher.match(uri);
                if (match == queryNotIdCode || match == queryByIdCode) {
                    return database.update("person",values,selection,selectionArgs);
                }
            }finally{
                database.close();
            }
            return 0;
        }

        @Nullable
        @Override
        public String getType(@NonNull Uri uri) {
            return null;
        }
    }


    /**
     * SQLite 工具
     */
    public static class SqlUtil extends SQLiteOpenHelper {

        /**
         * @param context 上下文
         * @param name    存储数据的文件名称
         * @param factory 游标工程
         * @param version 版本号（可以顺便写）
         */
        public SqlUtil(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context,name, factory, version);
        }

        /**
         * 数据库文件创建时回调（注意：这个函数只调用一次）
         * @param db
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            String table = "create table person(_id integer primary key autoincrement,name varchar,age int)";
            // 创建表
            db.execSQL(table);
            // 插入默认数据
            db.execSQL("insert into person(name,age) values('毛毛',11)");
        }

        /**
         * 数据库版本更改时回调
         * @param db
         * @param oldVersion
         * @param newVersion
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
