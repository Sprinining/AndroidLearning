package com.example.deviceinfo.util;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import java.util.Locale;

public class DeviceInfo {

    private static final String TAG = "deviceInfo";

    // 设备宽度（px）
    public static int getDeviceWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    // 获取设备高度（px）
    public static int getDeviceHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    // 厂商名
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    // 产品名
    public static String getDeviceProduct() {
        return Build.PRODUCT;
    }

    // 手机品牌
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    // 手机型号
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    // 主板名
    public static String getDeviceBoard() {
        return Build.BOARD;
    }

    // 设备驱动
    public static String getDeviceDevice() {
        return Build.DEVICE;
    }

    // 唯一标识
    public static String getDeviceFingerprint() {
        return Build.FINGERPRINT;
    }

    // 硬件名
    public static String getDeviceHardware() {
        return Build.HARDWARE;
    }

    // 主机名
    public static String getDeviceHost() {
        return Build.HOST;
    }

    // 设备版本包ID
    public static String getDeviceDisplay() {
        return Build.DISPLAY;
    }

    // 设备版本号
    public static String getDeviceId() {
        return Build.ID;
    }

    // 设备版本类型
    public static String getDeviceType() {
        return Build.TYPE;
    }

    // 设备标签
    public static String getDeviceTag() {
        return Build.TAGS;
    }

    // 手机用户名
    public static String getDeviceUser() {
        return Build.USER;
    }

    // SDK
    public static int getDeviceSDK() {
        return Build.VERSION.SDK_INT;
    }

    // 安卓版本
    public static String getDeviceAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    // 获取AndroidID
    public static String getAndroidID(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    // 当前系统语言
    public static String getDeviceCurrentLanguage() {
        return Locale.getDefault().getLanguage();
    }

    // 系统语言列表
    public static String getDeviceSupportLanguage() {
        Locale[] locales = Locale.getAvailableLocales();
        for (int i = 0; i < locales.length; i++) {
            Log.d(TAG, locales[i].toString());
        }
        return Locale.getAvailableLocales().toString();
    }

    // 已运行时长
    public static String getElapsedRealtime() {
        long totalMillisecond = SystemClock.elapsedRealtime() / 1000;
        if (totalMillisecond == 0) {
            totalMillisecond = 1;
        }
        int s = (int) (totalMillisecond % 60); // s
        int m = (int) ((totalMillisecond / 60) % 60); // min
        int h = (int) ((totalMillisecond / 3600)); // hour
        return h + "h" + m + "m" + s + "s";
    }
}
