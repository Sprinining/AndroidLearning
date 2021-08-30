package com.example.deviceinfo.util;

import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class CPUInfo {

    private static final String TAG = "cpuInfo";

    // 指令集
    public static String getCpuABI() {
        return Build.CPU_ABI;
    }

    // 第二个指令集
    public static String getCpuABI2() {
        return Build.CPU_ABI2;
    }

    // TODO: 2021/8/27 安卓10cpufreq文件夹下没有相关频率的文件，安卓9和11都有 
    // CPU最大频率（kHz）
    public static String getMaxCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            cmd = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
            // 用线程执行shell命令
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] buffer = new byte[24];
            // 存到缓冲区，read返回此次读取的字节数
            while (in.read(buffer) != -1) {
                result += new String(buffer);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = "unknow";
        }
        return result.trim();
    }

    // CPU最小频率（kHz）
    public static String getMinCpuFreq() {
        String result = "";
        ProcessBuilder cmd;
        try {
            cmd = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
            // 用线程执行shell命令
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] buffer = new byte[24];
            // 存到缓冲区，read返回此次读取的字节数
            while (in.read(buffer) != -1) {
                result += new String(buffer);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = "unknow";
        }
        return result.trim();
    }

    // CPU当前频率（kHz）
    public static String getCurCpuFreq() {

        // 安卓10cpufreq文件夹下没有相关信息，但可以在/proc/cpuinfo里查到当前频率cpu MHz         : 2999.997
//        String str = getCpuInfoByRegex("cpu.*MHz");
//        if (str == "unknow"){
//            return "unknow";
//        }else {
//            return String.valueOf(Float.parseFloat(getCpuInfoByRegex("cpu.*MHz")) * 1000);
//        }


        // 有的安卓版本，该文件不存在
        String result = "";
        ProcessBuilder cmd;
        try {
            cmd = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            // 用线程执行cmd命令
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] buffer = new byte[24];
            // 存到缓冲区，read返回此次读取的字节数
            while (in.read(buffer) != -1) {
                result += new String(buffer);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = "unknow";
        }
        return result.trim();
    }

    // processor
//    public static String getCpuName() {
//        try {
//            FileReader fileReader = new FileReader("/proc/cpuinfo");
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            // 读第一行processor： 0
//            String text = bufferedReader.readLine();
//            // 以冒号分开
//            String[] array = text.split(":\\s+", 2);
//            // 返回冒号右边的
//            return array[1];
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    // processor
    public static String getCpuName() {
        return getCpuInfoByRegex("processor");
    }

    // CPU制造商
    public static String getCpuVender() {
        return getCpuInfoByRegex("vendor_id");
    }

    // cpu family
    public static String getCpuFamily() {
        return getCpuInfoByRegex("cpu.*family");
    }

    // model
    public static String getCpuModel() {
        return getCpuInfoByRegex("model");
    }

    // model name
    public static String getCpuModelName() {
        return getCpuInfoByRegex("model.*name");
    }

    // stepping
    public static String getCpuStepping() {
        return getCpuInfoByRegex("stepping");
    }

    // cache size
    public static String getCpuCacheSize() {
        return getCpuInfoByRegex("cache.*size");
    }

    // clflush size
    public static String getCpuClflushSize() {
        return getCpuInfoByRegex("clflush.*size");
    }

    // cache_alignment
    public static String getCpuCacheAlignment() {
        return getCpuInfoByRegex("cache_alignment");
    }

    // physical id
    public static String getCpuPhysicalId() {
        return getCpuInfoByRegex("physical.*id");
    }

    // siblings
    public static String getCpuSiblings() {
        return getCpuInfoByRegex("siblings");
    }

    // cpu cores
    public static String getCpuCores() {
        return getCpuInfoByRegex("cpu.*cores");
    }

    // fpu
    public static String getCpuFpu() {
        return getCpuInfoByRegex("fpu");
    }

    // fpu_exception
    public static String getCpuFpuException() {
        return getCpuInfoByRegex("fpu_exception");
    }

    // address sizes
    public static String getCpuAddressSizes() {
        return getCpuInfoByRegex("address.*sizes");
    }

    // 从/proc/cpuinfo搜索相关信息，但有的设备cpuinfo文件里数据不一样
    public static String getCpuInfoByRegex(String str) {
        String result = "";
        ProcessBuilder cmd;
        try {
            // 取grep搜索匹配正则表达式的第一行
            cmd = new ProcessBuilder("/bin/sh", "-c", "grep -e" + str + ".* /proc/cpuinfo" + " | head -n 1");
            // 用线程执行shell命令
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            // 缓冲区太小会追加些奇怪的东西
            byte[] buffer = new byte[100];
            // 存到缓冲区，read返回此次读取的字节数
            while (in.read(buffer) != -1) {
//                Log.d(TAG, "getDetail: " + new String(buffer));
                result += new String(buffer);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "unknow";
        }

        // 取被冒号分隔的右边
        try {
            String[] array = result.trim().split(":\\s+");
            return array[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "unknow";
        }
    }


}
