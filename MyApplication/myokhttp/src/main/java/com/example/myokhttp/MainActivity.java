package com.example.myokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okHttpClient = new OkHttpClient();
    }

    public void getSync(View view) {
        // 必须放在线程里
        new Thread(()->{
            Request build = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
            // 准备好请求的call对象
            Call call = okHttpClient.newCall(build);

            // 同步请求
            try {
                Response response = call.execute(); // 会阻塞
                Log.d(TAG, "getSync: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void getAsync(View view) {
        Request build = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
        // 准备好请求的call对象
        Call call = okHttpClient.newCall(build);

        // 异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            // 只代表和服务器的请求成功了
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.d(TAG, "getSync: " + response.body().string());
                }
            }
        });
    }

    public void postSync(View view) {
        new Thread(()->{
            FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();

            Request build = new Request.Builder().url("https://www.httpbin.org/post")
                    .post(formBody).build();

            // 准备好请求的call对象
            Call call = okHttpClient.newCall(build);

            // 同步请求
            try {
                Response response = call.execute(); // 会阻塞
                Log.d(TAG, "postSync: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    public void postASync(View view) {
        FormBody formBody = new FormBody.Builder().add("a", "1").add("b", "2").build();

        Request build = new Request.Builder().url("https://www.httpbin.org/post")
                .post(formBody).build();

        // 准备好请求的call对象
        Call call = okHttpClient.newCall(build);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.d(TAG, "postAsync: " + response.body().string());
                }
            }
        });
    }
}