package com.example.mynotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取通知管理器类对象
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // 判断版本是否是8.0及以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 通知渠道，参数3是通知重要程度
            NotificationChannel notificationChannel = new NotificationChannel("wmj", "测试通知", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // 点击通知后跳转到的页面
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // Builder构造器创建Notification对象
        notification = new NotificationCompat.Builder(this, "wmj")
                .setContentTitle("重要通知")
                .setContentText("哈哈哈")
                .setAutoCancel(true) // 点击通知后自动消失
                .setSmallIcon(R.drawable.ic_baseline_person_24) // 不能使用彩色图片
                .setContentIntent(pendingIntent) // 设置待定的页面
                .setColor(Color.parseColor("#FF0000")) // 小图标颜色
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dog)) // 通知右侧大图标
                .build();// id和上面一致
    }

    public void sendNotification(View view) {
        notificationManager.notify(1, notification);
    }

    public void cancelNotification(View view) {
        notificationManager.cancel(1);
    }
}