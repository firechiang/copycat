package com.firechiang.android.copycat_helloword.data_storage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.firechiang.android.copycat_helloword.R;

/**
 * 测试 SQLite 数据库存储
 * 数据保存在 /data/data/packageName/databases/xxx.db 文件里面（注意：packageName就是app的名称，xxx是库的名称）db文件只当前应用可读写，其它应用不可读，应用删除时也会删除此文件
 */
public class DataStorageActivitySql extends Activity  {

    /**
     * 数据库操作工具
     * @param context 上下文
     * @param name    存储数据的文件名称
     * @param factory 游标工程
     * @param version 版本号（可以顺便写，如果版本号和以前的不一样就会更新数据版本，注意一点，数据版本不能降级否则报错）
     */
    SqlUtil sqlUtil = new SqlUtil(this,"copycat_helloword.db",null,3);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_storage_activity_sql);
    }

    /**
     * 创建DB数据库
     * @param view
     */
    public void createDB(View view) {
        /**
         * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
         * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
         * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
         */
        SQLiteDatabase database = sqlUtil.getReadableDatabase();
        Toast.makeText(this,"数据库创建成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 修改DB数据库
     * @param view
     */
    public void updateDB(View view) {
        /**
         * 数据库操作工具
         * @param context 上下文
         * @param name    存储数据的文件名称
         * @param factory 游标工程
         * @param version 版本号（可以顺便写，如果版本号和以前的不一样就会更新数据版本，注意一点，数据版本不能降级否则报错）
         */
        SqlUtil sqlUtil = new SqlUtil(this,"copycat_helloword.db",null,3);
        /**
         * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
         * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
         * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
         */
        SQLiteDatabase database = sqlUtil.getReadableDatabase();
        Toast.makeText(this,"修改数据库版本成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 插入数据
     * @param view
     */
    public void insert(View view) {
        /**
         * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
         * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
         * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
         */
        SQLiteDatabase database = sqlUtil.getReadableDatabase();
        // 插入一条数据
        database.execSQL("insert into person (name,age) values ('天天',12)");
        // 字段的值
        ContentValues values = new ContentValues();
        values.put("name","我问问");
        values.put("age",121212);
        /**
         * 插入一条数据
         * @param table          表名
         * @param nullColumnHack 字段名
         * @param values          字段的值
         */
        long id = database.insert("person", null, values);
        database.close();
        Toast.makeText(this,"插入成功 id="+id,Toast.LENGTH_SHORT).show();
    }

    /**
     * 修改数据
     * @param view
     */
    public void update(View view) {
        /**
         * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
         * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
         * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
         */
        SQLiteDatabase database = sqlUtil.getReadableDatabase();
        // 修改一条数据
        database.execSQL("update person set name = '修改递四方速递' where _id = 1");
        // 字段的值
        ContentValues values = new ContentValues();
        values.put("name","修改后我问问");
        values.put("age",121212);
        /**
         * 修改一条数据
         * @param table        表名
         * @param values      字段的值
         * @param whereClause 更新条件
         * @param whereArgs   更新条件参数的值
         */
        database.update("person",values,"_id=?",new String[]{"1"});
        database.close();
        Toast.makeText(this,"修改数据成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除数据
     * @param view
     */
    public void del(View view) {
        /**
         * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
         * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
         * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
         */
        SQLiteDatabase database = sqlUtil.getReadableDatabase();
        // 删除一条数据
        database.execSQL("delete from person where _id = 2");
        /**
         * 修改一条数据
         * @param table        表名
         * @param whereClause 删除条件
         * @param whereArgs   删除条件参数的值
         */
        database.delete("person","_id=?",new String[]{"2"});
        Toast.makeText(this,"删除数据成功",Toast.LENGTH_SHORT).show();
    }

    /**
     * 查询数据
     * @param view
     */
    public void query(View view) {
        /**
         * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
         * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
         * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
         */
        SQLiteDatabase database = sqlUtil.getReadableDatabase();
        /**
         * 查询所有数据
         * @param table         表名
         * @param colums        要查询的列（所有就传null）
         * @param selection     查询条件（没有就传null）
         * @param selectionArgs 查询条件参数的值
         * @param groupBy       分组条件
         * @param having        分组过滤条件
         * @paam orderBy        排序条件
         */
        Cursor person = database.query("person", null, null, null, null, null, null);
        // 是否有下一条数据
        while(person.moveToNext()) {
            String personName = person.getString(1);
        }
        Toast.makeText(this,"person表有"+person.getCount()+"条数据",Toast.LENGTH_SHORT).show();
        /**
         * 查询_id=1的所有数据
         * @param table         表名
         * @param colums        要查询的列（所有就传null）
         * @param selection     查询条件（没有就传null）
         * @param selectionArgs 查询条件参数的值
         * @param groupBy       分组条件
         * @param having        分组过滤条件
         * @paam orderBy        排序条件
         */
        Cursor person1 = database.query("person", null, "_id=?", new String[]{"1"}, null, null, null);
        database.close();
    }

    /**
     * 事物简单使用
     * @param view
     */
    public void transaction(View view) {
        /**
         * 获取数据库连接并创建数据库存储文件（注意：如果数据存储文件已存在就不会创建该数据库存储文件）
         * 注意：sqlUtil.getWritableDatabase() 和 sqlUtil.getReadableDatabase() 获取连接在正常情况下是没什么区别的，
         * 但是如果手机空间不足了，sqlUtil.getReadableDatabase() 获取到的连接就无法使用也不会报错，而sqlUtil.getWritableDatabase()获取到的连接在插入数据时就会报错
         */
        SQLiteDatabase database = sqlUtil.getReadableDatabase();
        // 事物开始
        database.beginTransaction();
        try {
            // 修改一条数据
            database.execSQL("update person set name = '修改递四方速递' where _id = 1");
            // 字段的值
            ContentValues values = new ContentValues();
            values.put("name", "修改后我问问");
            values.put("age", 121212);
            /**
             * 修改一条数据
             * @param table        表名
             * @param values      字段的值
             * @param whereClause 更新条件
             * @param whereArgs   更新条件参数的值
             */
            database.update("person", values, "_id=?", new String[]{"1"});
            // 提交事物
            database.setTransactionSuccessful();
        } finally {
            // 事物结束（注意：如果没有提交事物，在事物结束后就会回滚数据）
            database.endTransaction();
        }
        database.close();
        Toast.makeText(this,"事物修改数据成功",Toast.LENGTH_SHORT).show();
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
