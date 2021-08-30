package com.example.myroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myroom.manager.DBEngine;

public class MainActivity extends AppCompatActivity {

    private DBEngine dbEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbEngine = new DBEngine(this);
    }

    // 插入
    public void insertAction(View view) {
        Student s1 = new Student("张三", 20);
        Student s2 = new Student("张四", 21);
        Student s3 = new Student("张五", 22);
        dbEngine.insertStudents(s1, s2, s3);
    }

    // 更新
    public void updateAction(View view) {
        Student s = new Student("王五", 30);
        s.setId(2);
        dbEngine.updateStudents(s);
    }

    // 条件删除
    public void deleteAction(View view) {
        Student student = new Student(null, 0);
        student.setId(2);
        dbEngine.deleteStudents(student);
    }

    // 全部查询
    public void queryAction(View view) {
        dbEngine.queryAllStudents();
    }

    // 全部删除
    public void deleteAllAction(View view) {
        dbEngine.deleteAllStudents();
    }
}