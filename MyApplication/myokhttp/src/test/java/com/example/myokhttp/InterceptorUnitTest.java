package com.example.myokhttp;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InterceptorUnitTest {
    @Test
    public void interceptorTest() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(new File("C:\\Users\\86139\\Downloads\\2.txt"), 1024*1024))
                .addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                // 前置处理
                Request request = chain.request().newBuilder().addHeader("os", "android")
                        .addHeader("version", "1").build();
                Response response = chain.proceed(request);
                // 后置处理
                return response;
            }
        }).addNetworkInterceptor(new Interceptor() { // 一定在addInterceptor后面执行
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                System.out.println(chain.request().header("version"));
                return chain.proceed(chain.request());
            }
        }).build();

        Request build = new Request.Builder().url("https://www.httpbin.org/get?a=1&b=2").build();
        try {
            Call call = okHttpClient.newCall(build);
            Response response = call.execute();
            System.out.println(response.body().string());
        } finally {

        }
    }
}
