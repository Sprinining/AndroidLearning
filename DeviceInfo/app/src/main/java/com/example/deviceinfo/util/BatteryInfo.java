package com.example.deviceinfo.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BatteryManager;

public class BatteryInfo extends BroadcastReceiver {

    SharedPreferences.Editor editor;

    public BatteryInfo(SharedPreferences.Editor editor) {
        this.editor = editor;
    }

    // 接收者
    @Override
    public void onReceive(Context context, Intent intent) {
        // 获取电池状态
        int batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        switch (batteryStatus) {
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                editor.putString("batteryStatus", "BATTERY_STATUS_UNKNOWN");
                break;
            case BatteryManager.BATTERY_STATUS_CHARGING:
                editor.putString("batteryStatus", "BATTERY_STATUS_CHARGING");
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                editor.putString("batteryStatus", "BATTERY_STATUS_DISCHARGING");
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                editor.putString("batteryStatus", "BATTERY_STATUS_NOT_CHARGING");
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                editor.putString("batteryStatus", "BATTERY_STATUS_FULL");
                break;

            default:
                break;
        }

        // 获取电池的健康程度
        int batteryHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
        switch (batteryHealth) {
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                editor.putString("batteryHealth", "BATTERY_HEALTH_UNKNOWN");
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                editor.putString("batteryHealth", "BATTERY_HEALTH_GOOD");
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                editor.putString("batteryHealth", "BATTERY_HEALTH_OVERHEAT");
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                editor.putString("batteryHealth", "BATTERY_HEALTH_DEAD");
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                editor.putString("batteryHealth", "BATTERY_HEALTH_OVER_VOLTAGE");
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                editor.putString("batteryHealth", "BATTERY_HEALTH_UNSPECIFIED_FAILURE");
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                editor.putString("batteryHealth", "BATTERY_HEALTH_COLD");
                break;
            default:
                break;
        }

        // 获取电池是否存在
        boolean batteryPresent = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
        editor.putString("batteryPresent", String.valueOf(batteryPresent));

        // 获取电池的当前电量和总电量
        int batteryCurrent = intent.getExtras().getInt("level");
        int batteryTotal = intent.getExtras().getInt("scale");
        editor.putString("batteryCurrent", String.valueOf(batteryCurrent));
        editor.putString("batteryTotal", String.valueOf(batteryTotal));

        // 获取电池充电方式
        int batteryPlugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        switch (batteryPlugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                editor.putString("batteryPlugged", "BATTERY_PLUGGED_AC");// 交流电
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                editor.putString("batteryPlugged", "BATTERY_PLUGGED_USB");// USB充电
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                editor.putString("batteryPlugged", "BATTERY_PLUGGED_WIRELESS");// 无线充电
                break;
            default:
                editor.putString("batteryPlugged", "未在充电");
                break;
        }

        // 获取电池的电压
        int batteryVoltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
        editor.putString("batteryVoltage", String.valueOf(batteryVoltage));

        // 获取电池的温度
        int batteryTemperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
        editor.putString("batteryTemperature", String.valueOf(batteryTemperature));

        // 获取电池的技术
        String batteryTechnology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
        editor.putString("batteryTechnology", batteryTechnology);

        // 提交
        editor.apply();
    }
}
