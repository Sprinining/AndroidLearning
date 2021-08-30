package com.example.myactivity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MyActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my3);

        // 动态注册
        CustomReceiver2 customReceiver2 = new CustomReceiver2();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionUtils.ACTION_FLAG2);
        registerReceiver(customReceiver2, intentFilter);
    }

    // 静态发送广播
    public void sendAction1(View view) {
        Intent intent = new Intent();
        intent.setAction(ActionUtils.ACTION_FLAG);
        // 必须指定包名
        intent.setPackage("com.example.myactivity1");
        sendBroadcast(intent);
    }

    // 动态发送广播
    public void sendAction2(View view) {
        Intent intent = new Intent();
        intent.setAction(ActionUtils.ACTION_FLAG2);
        sendBroadcast(intent);
    }
}