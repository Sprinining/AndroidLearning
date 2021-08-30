package com.example.deviceinfo.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.deviceinfo.R;
import com.example.deviceinfo.adapter.MyFragmentPagerAdapter;
import com.example.deviceinfo.entity.Bean;
import com.example.deviceinfo.util.BatteryInfo;
import com.example.deviceinfo.util.CPUInfo;
import com.example.deviceinfo.util.DeviceInfo;
import com.example.deviceinfo.util.StorageInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "mainActivity";
    private SharedPreferences preferences;

    private ViewPager2 viewPager;
    private ImageView ivDevice, ivCpu, ivBattery, ivStorage, ivCurrent; // 控制下方tabBar显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        // 电池信息
        preferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        IntentFilter intentFilter = new IntentFilter();
        // 电量状态改变时，系统会发送对应广播
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        BatteryInfo batteryInfo = new BatteryInfo(editor);
        registerReceiver(batteryInfo, intentFilter);

        initPager();
        initTabView();

//        Log.d(TAG, "onCreate: " + StorageInfo.getSDCardInfo(this));
    }


    private void initPager() {
        List<Fragment> fragments = new ArrayList<>();

        // 加载数据
        fragments.add(BlankFragment.newInstance(initDeviceData()));
        fragments.add(BlankFragment.newInstance(initCpuData()));
        fragments.add(BlankFragment.newInstance(initBatteryData()));
        fragments.add(BlankFragment.newInstance(initStorageData()));

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
                // 滑动viewpage，tabBar跟着变化
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void initTabView() {
        LinearLayout llDevice = findViewById(R.id.tab_device);
        llDevice.setOnClickListener(this);
        LinearLayout llCpu = findViewById(R.id.tab_cpu);
        llCpu.setOnClickListener(this);
        LinearLayout llBattery = findViewById(R.id.tab_battery);
        llBattery.setOnClickListener(this);
        LinearLayout llStorage = findViewById(R.id.tab_storage);
        llStorage.setOnClickListener(this);

        ivDevice = findViewById(R.id.iv_device);
        ivCpu = findViewById(R.id.iv_cpu);
        ivBattery = findViewById(R.id.iv_battery);
        ivStorage = findViewById(R.id.iv_storage);

        // 默认选中device
        llDevice.setSelected(true);
        ivCurrent = ivDevice;
    }


    // 系统信息
    private List<Bean> initDeviceData() {
        List<Bean> data = new ArrayList<>();
        data.add(new Bean(getString(R.string.DeviceWidth), String.valueOf(DeviceInfo.getDeviceWidth(this))));
        data.add(new Bean(getString(R.string.DeviceHeight), String.valueOf(DeviceInfo.getDeviceHeight(this))));
        data.add(new Bean(getString(R.string.DeviceManufacturer), DeviceInfo.getDeviceManufacturer()));
        data.add(new Bean(getString(R.string.DeviceProduct), DeviceInfo.getDeviceProduct()));
        data.add(new Bean(getString(R.string.DeviceBrand), DeviceInfo.getDeviceBrand()));
        data.add(new Bean(getString(R.string.DeviceModel), DeviceInfo.getDeviceModel()));
        data.add(new Bean(getString(R.string.DeviceBoard), DeviceInfo.getDeviceBoard()));
        data.add(new Bean(getString(R.string.DeviceDevice), DeviceInfo.getDeviceDevice()));
        data.add(new Bean(getString(R.string.DeviceFingerprint), DeviceInfo.getDeviceFingerprint()));
        data.add(new Bean(getString(R.string.DeviceHardware), DeviceInfo.getDeviceHardware()));
        data.add(new Bean(getString(R.string.DeviceHost), DeviceInfo.getDeviceHost()));
        data.add(new Bean(getString(R.string.DeviceDisplay), DeviceInfo.getDeviceDisplay()));
        data.add(new Bean(getString(R.string.DeviceId), DeviceInfo.getDeviceId()));
        data.add(new Bean(getString(R.string.DeviceUser), DeviceInfo.getDeviceUser()));
        data.add(new Bean(getString(R.string.DeviceSDK), String.valueOf(DeviceInfo.getDeviceSDK())));
        data.add(new Bean(getString(R.string.DeviceAndroidVersion), DeviceInfo.getDeviceAndroidVersion()));
        data.add(new Bean(getString(R.string.DeviceCurrentLanguage), DeviceInfo.getDeviceCurrentLanguage()));
        data.add(new Bean(getString(R.string.AndroidID), DeviceInfo.getAndroidID(this)));
        data.add(new Bean(getString(R.string.ElapsedRealtime), DeviceInfo.getElapsedRealtime()));
        data.add(new Bean(getString(R.string.DeviceTag), DeviceInfo.getDeviceTag()));
        data.add(new Bean(getString(R.string.DeviceType), DeviceInfo.getDeviceType()));
        return data;
    }

    // 内存信息
    private List<Bean> initStorageData() {
        List<Bean> data = new ArrayList<>();
        data.add(new Bean(getString(R.string.RAMInfo), StorageInfo.getRAMInfo(this)));
        data.add(new Bean(getString(R.string.ROM), StorageInfo.getStorageInfo(this, 0)));
        data.add(new Bean(getString(R.string.SD), StorageInfo.getStorageInfo(this, 1)));
        return data;
    }

    // CPU信息
    private List<Bean> initCpuData() {
        List<Bean> data = new ArrayList<>();
        data.add(new Bean(getString(R.string.CpuName), CPUInfo.getCpuName()));
        data.add(new Bean(getString(R.string.MaxCpuFreq), CPUInfo.getMaxCpuFreq()));
        data.add(new Bean(getString(R.string.MinCpuFreq), CPUInfo.getMinCpuFreq()));
        data.add(new Bean(getString(R.string.CurCpuFreq), CPUInfo.getCurCpuFreq()));
        data.add(new Bean(getString(R.string.CpuABI), CPUInfo.getCpuABI()));
        data.add(new Bean(getString(R.string.CpuABI2), CPUInfo.getCpuABI2()));
        data.add(new Bean(getString(R.string.CpuVender), CPUInfo.getCpuVender()));

        data.add(new Bean(getString(R.string.CpuFamily), CPUInfo.getCpuFamily()));
        data.add(new Bean(getString(R.string.CpuModel), CPUInfo.getCpuModel()));
        data.add(new Bean(getString(R.string.CpuModelName), CPUInfo.getCpuModelName()));
        data.add(new Bean(getString(R.string.CpuStepping), CPUInfo.getCpuStepping()));

        data.add(new Bean(getString(R.string.CpuCacheSize), CPUInfo.getCpuCacheSize()));
        data.add(new Bean(getString(R.string.CpuClflushSize), CPUInfo.getCpuClflushSize()));
        data.add(new Bean(getString(R.string.Cpucache_alignment), CPUInfo.getCpuCacheAlignment()));

        data.add(new Bean(getString(R.string.CpuPhysicalId), CPUInfo.getCpuPhysicalId()));
        data.add(new Bean(getString(R.string.CpuSiblings), CPUInfo.getCpuSiblings()));
        data.add(new Bean(getString(R.string.CpuCores), CPUInfo.getCpuCores()));

        data.add(new Bean(getString(R.string.Cpufpu), CPUInfo.getCpuFpu()));
        data.add(new Bean(getString(R.string.Cpufpu_exception), CPUInfo.getCpuFpuException()));
        data.add(new Bean(getString(R.string.Cpuaddress_sizes), CPUInfo.getCpuAddressSizes()));


        return data;
    }

    // 电池信息
    private List<Bean> initBatteryData() {
        List<Bean> data = new ArrayList<>();
        data.add(new Bean(getString(R.string.BatteryStatus), preferences.getString("batteryStatus", "")));
        data.add(new Bean(getString(R.string.BatteryHealth), preferences.getString("batteryHealth", "")));
        data.add(new Bean(getString(R.string.BatteryPresent), preferences.getString("batteryPresent", "")));
        data.add(new Bean(getString(R.string.BatteryCurrent), preferences.getString("batteryCurrent", "")));
        data.add(new Bean(getString(R.string.BatteryTotal), preferences.getString("batteryTotal", "")));
        data.add(new Bean(getString(R.string.BatteryPlugged), preferences.getString("batteryPlugged", "")));
        data.add(new Bean(getString(R.string.BatteryVoltage), preferences.getString("batteryVoltage", "")));
        data.add(new Bean(getString(R.string.BatteryTemperature), preferences.getString("batteryTemperature", "")));
        data.add(new Bean(getString(R.string.BatteryTechnology), preferences.getString("batteryTechnology", "")));
        return data;
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }

    // 按钮viewPage一起变化
    private void changeTab(int position) {
        ivCurrent.setSelected(false); // 当前按钮先复位
        switch (position) {
            case R.id.tab_device:
                viewPager.setCurrentItem(0);
            case 0:
                ivDevice.setSelected(true);
                ivCurrent = ivDevice;
                break;
            case R.id.tab_cpu:
                viewPager.setCurrentItem(1);
            case 1:
                ivCpu.setSelected(true);
                ivCurrent = ivCpu;
                break;
            case R.id.tab_battery:
                viewPager.setCurrentItem(2);
            case 2:
                ivBattery.setSelected(true);
                ivCurrent = ivBattery;
                break;
            case R.id.tab_storage:
                viewPager.setCurrentItem(3);
            case 3:
                ivStorage.setSelected(true);
                ivCurrent = ivStorage;
                break;

            default:
                Log.d(TAG, "changeTab: id找不到");
                break;
        }
    }
}