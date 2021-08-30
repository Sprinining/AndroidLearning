package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createDB(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getmInstance(this);

        // 文件夹的创建
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
    }

    public void query(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getmInstance(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        if(db.isOpen()){
            // 返回游标
            Cursor cursor = db.rawQuery("select * from persons", null);

            // 迭代游标，遍历数据
            while (cursor.moveToNext()){
//                int _id = cursor.getInt(0);
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));

                Log.d("wmj", "query: " + _id + " " + name);
                // 关闭
                cursor.close();
                db.close();
            }
        }
    }

    public void insert(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getmInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if(db.isOpen()){
            String sql = "insert into persons(name) values('haha')";
            db.execSQL(sql);
            db.close();
        }
    }

    public void update(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getmInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if(db.isOpen()){
            String sql = "update persons set name=? where _id=?";
            db.execSQL(sql, new Object[]{"xixi", 2});
            db.close();
        }
    }

    public void delete(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getmInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if(db.isOpen()){
            String sql = "delete from persons where _id =?";
            db.execSQL(sql, new Object[]{3});
            db.close();
        }
    }
}