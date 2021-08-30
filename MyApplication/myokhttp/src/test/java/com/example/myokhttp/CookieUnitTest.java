package com.example.myokhttp;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CookieUnitTest {

    Map<String, List<Cookie>> map = new HashMap<>();

    @Test
    public void cookieTest() throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
                        map.put(httpUrl.host(), list);
                    }

                    @NotNull
                    @Override
                    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                        List<Cookie> list = CookieUnitTest.this.map.get(httpUrl.host());
                        return list == null ? new ArrayList<>() : list;
                    }
                }).build();

        FormBody formBody = new FormBody.Builder()
                .add("username", "sprinining")
                .add("password", "wanandroid")
                .build();
        Request request = new Request.Builder()
                .url("https://www.wanandroid.com/user/login")
                .post(formBody).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


        request = new Request.Builder().url("https://www.wanandroid.com/lg/collect/list/0/json").build();
        call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
