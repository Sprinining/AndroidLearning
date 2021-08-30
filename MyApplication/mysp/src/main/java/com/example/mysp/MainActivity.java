package com.example.mysp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *
     *     // 参数1：sp的名字
     *     // 参数2：sp保存用的模式 常规（覆盖）、追加
     *     public SharedPreferences getSharedPreferences(String name, int mode) {
     *         throw new RuntimeException("Stub!");
     *     }
     * @param view
     */

    public void saveToSP(View view) {
        SharedPreferences sp = getSharedPreferences("SPtest", Context.MODE_PRIVATE);
        sp.edit().putString("haha", "哈哈").apply(); // 保存到xml
    }

    public void getSpData(View view) {
        SharedPreferences sp = getSharedPreferences("SPtest", Context.MODE_PRIVATE);
        String s = sp.getString("haha", "默认值");
        Log.e("wmj", "getSpData: " + s);
    }
}