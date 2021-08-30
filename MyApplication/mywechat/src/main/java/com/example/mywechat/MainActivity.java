package com.example.mywechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager2 viewPager;
    private LinearLayout llweixin, llweixin1, llweixin2, llpay;
    private ImageView ivweixin, ivweixin1, ivweixin2, ivpay, ivCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPager();
        initTabView();
    }

    private void initPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance("微信"));
        fragments.add(BlankFragment.newInstance("通讯录"));
        fragments.add(BlankFragment.newInstance("发现"));
        fragments.add(BlankFragment.newInstance("我"));

        viewPager = findViewById(R.id.vp);
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        ivCurrent.setSelected(false); // 当前的按钮先复位
        switch (position){
            case R.id.tab_weixin:
                viewPager.setCurrentItem(0);
            case 0:
                ivweixin.setSelected(true);
                ivCurrent = ivweixin;
                break;
            case R.id.tab_pay:
                viewPager.setCurrentItem(1);
            case 1:
                ivpay.setSelected(true);
                ivCurrent = ivpay;
                break;
            case R.id.tab_weixin1:
                viewPager.setCurrentItem(2);
            case 2:
                ivweixin1.setSelected(true);
                ivCurrent = ivweixin1;
                break;
            case R.id.tab_weixin2:
                viewPager.setCurrentItem(3);
            case 3:
                ivweixin2.setSelected(true);
                ivCurrent = ivweixin2;
                break;
        }
    }

    private void initTabView(){
        llweixin = findViewById(R.id.tab_weixin);
        llweixin.setOnClickListener(this);
        llweixin1 = findViewById(R.id.tab_weixin1);
        llweixin1.setOnClickListener(this);
        llweixin2 = findViewById(R.id.tab_weixin2);
        llweixin2.setOnClickListener(this);
        llpay = findViewById(R.id.tab_pay);
        llpay.setOnClickListener(this);
        ivweixin = findViewById(R.id.iv_weixin);
        ivweixin1 = findViewById(R.id.iv_weixin1);
        ivweixin2 = findViewById(R.id.iv_weixin2);
        ivpay = findViewById(R.id.iv_pay);

        llweixin.setSelected(true);
        ivCurrent = ivweixin;
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}