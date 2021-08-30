package com.example.myglide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.Rotate;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_baseline_cloud_download_24) // 正在加载时显示的图片
                .error(R.drawable.ic_baseline_error_24) // 加载失败时显示的，默认为palceholder的图
                .fallback(R.drawable.ic_baseline_adb_24) // 请求为null时显示的图，默认placeholder
                .override(100, 100);

        // 过渡效果结束后让placeholder消失
        DrawableCrossFadeFactory fadeFactory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

        Glide.with(this) // 与activity生命周期绑定
                .load("https://img0.baidu.com/it/u=2151136234,3513236673&fm=26&fmt=auto&gp=0.jpg")
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(fadeFactory)) // 默认时Drawable，可以用.asBitmap()改变
                .transform(new CircleCrop()) // 圆角
//                .transform(new RoundedCorners(20)) // 自定义弧度
//                .transform(new GranularRoundedCorners(20, 30, 40, 50)) // 自定义每个角弧度
//                .transform(new Rotate(90)) // 旋转角度

                .into(imageView);


        // 配置到扩展中
//        GlideApp.with(this).load("").defaultImg().into(imageView);


    }
}