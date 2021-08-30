package com.example.rxjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginEngine.login("wmj", "666")
                .subscribe(new CustomObserver() {
                    @Override
                    public void success(SuccessBean successBean) {
                        Log.d("wmj", "成功bean：" + successBean.toString());
                    }

                    @Override
                    public void error(String message) {
                        Log.d("wmj", "错误信息: " + message);
                    }
                });
    }
}