package com.example.myglide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;

@GlideExtension
public class MyAppExtension {
    private MyAppExtension(){

    }

    @GlideOption
    public static BaseRequestOptions<?> defaultImg(BaseRequestOptions<?> options){
        return options
                .placeholder(R.drawable.ic_baseline_cloud_download_24) // 正在加载时显示的图片
                .error(R.drawable.ic_baseline_error_24) // 加载失败时显示的，默认为palceholder的图
                .fallback(R.drawable.ic_baseline_adb_24); // 请求为null时显示的图，默认placeholder
    }
}
