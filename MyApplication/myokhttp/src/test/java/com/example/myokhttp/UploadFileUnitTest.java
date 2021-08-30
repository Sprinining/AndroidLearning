package com.example.myokhttp;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadFileUnitTest {

    @Test
    public void uploadFileTest() throws IOException {
        File file1 = new File("C:\\Users\\86139\\Downloads\\1.txt");
        File file2 = new File("C:\\Users\\86139\\Downloads\\2.txt");

        OkHttpClient client = new OkHttpClient();

        MultipartBody multipartBody = new MultipartBody.Builder()
                .addFormDataPart("file1", file1.getName(), RequestBody.create(file1, MediaType.parse("text/plain")))
                .addFormDataPart("file2", file2.getName(), RequestBody.create(file2, MediaType.parse("text/plain")))
                .build();
        Request request = new Request.Builder().url("https://www.httpbin.org/post").post(multipartBody).build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());

    }

    @Test
    public void jsonTest() throws IOException {
        OkHttpClient client = new OkHttpClient();
        // https://www.runoob.com/http/http-content-type.html
        RequestBody requestBody = RequestBody.create("{\"a\":1, \"b\":2}", MediaType.parse("application/json"));
        Request request = new Request.Builder().url("https://www.httpbin.org/post").post(requestBody).build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());
    }
}
