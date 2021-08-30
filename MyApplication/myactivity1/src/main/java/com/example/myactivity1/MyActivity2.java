package com.example.myactivity1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MyActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);
    }

    // 关闭activity后会在后台运行
    public void startService(View view) {
        /**
         * onCreate() -> onStartCommand() -> onStart()
         */
        startService(new Intent(this, MyService.class));
    }

    public void stopService(View view) {
        /**
         * onDestroy()
         */
        stopService(new Intent(this, MyService.class));
    }

    // 与activity共存亡
    public void bindService(View view) {
        /**
         *onCreate() -> onBind()
         */
        bindService(new Intent(this, MyService.class), connection, BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        /**
         *onUnbind() -> onDestroy
         */
        unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    // 一般写法，当activity销毁时，自动解绑服务

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}