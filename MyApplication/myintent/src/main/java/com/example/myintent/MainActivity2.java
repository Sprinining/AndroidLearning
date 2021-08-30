package com.example.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
//        Log.d("wmj", "onCreate: " + intent.getStringExtra("name") + intent.getStringExtra("sex"));

        // 强制转换
//        Student student = (Student) intent.getSerializableExtra("student");
//        Toast.makeText(this, student.name, Toast.LENGTH_SHORT).show();

        Teacher teacher = intent.getParcelableExtra("teac");
        Toast.makeText(this, teacher.name, Toast.LENGTH_SHORT).show();

    }
}