package com.example.myroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    // 用户只需要操作dao 必须暴露dao
    public abstract StudentDao getStudentDao();

    // 单例模式 返回db
    private static StudentDatabase INSTANCE;
    public static synchronized StudentDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder
                    (context.getApplicationContext(), StudentDatabase.class, "student_database")
                    // 默认异步线程
                    // 可以强制开启主线程使用
                    //.allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
