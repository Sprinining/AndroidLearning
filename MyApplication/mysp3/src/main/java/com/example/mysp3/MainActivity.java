package com.example.mysp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 不能传递this，MyData保存着Activity的引用，Activity会反复创建，但无法销毁
         * 会导致内存泄漏
         */
        MyData myData = new MyData(getApplicationContext()); // myData具备访问全局资源的能力
        myData.number = 1000;
        myData.save();
        int y = myData.load();
        Log.d("wmj", "onCreate: " + y);
    }
}