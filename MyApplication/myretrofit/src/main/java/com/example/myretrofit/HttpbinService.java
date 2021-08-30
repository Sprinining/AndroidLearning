package com.example.myretrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpbinService {

    // 根据Http接口创建java接口
    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("username") String userName, @Field("password") String pwd);

    @GET("get")
    Call<ResponseBody> get(@Query("username") String userName, @Query("password") String pwd);
}
