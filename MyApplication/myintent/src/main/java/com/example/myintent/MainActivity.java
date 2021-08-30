package com.example.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAction(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
//        intent.putExtra("name", "wmj");
//        intent.putExtra("sex", "female");

        // Bundle
//        Bundle bundle = new Bundle();
//        bundle.putString("name", "wmj");
//        bundle.putString("sex", "female");
//        intent.putExtras(bundle);

        // 必须实现Serializable接口
//        Student student = new Student("wmj", 7, 24);
//        intent.putExtra("student", student);

        Teacher teacher = new Teacher("wmj", 24);
        intent.putExtra("teac", teacher);

        startActivity(intent);
    }
}