package com.example.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// 工具类  单例模式：1.构造函数私有化 2.对外提供函数
public class MySqliteOpenHelper extends SQLiteOpenHelper {

    // 2.对外提供函数
    private static MySqliteOpenHelper mInstance;
    public static synchronized SQLiteOpenHelper getmInstance(Context context){
        if(mInstance == null){
            mInstance = new MySqliteOpenHelper(context, "wmj.db", null, 1);
        }
        return mInstance;
    }

    // 1.构造函数私有化
    private MySqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // 数据库初始化时使用  此函数只会执行一次
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建persons表 _id, name
        String sql = "create table persons(_id integer primary key autoincrement, name text)";
        sqLiteDatabase.execSQL(sql);
    }

    // 数据库升级时使用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
