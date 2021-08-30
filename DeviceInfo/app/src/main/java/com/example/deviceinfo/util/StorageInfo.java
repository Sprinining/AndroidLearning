package com.example.deviceinfo.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;

public class StorageInfo {
    private static final int INTERNAL_STORAGE = 0; // 内部ROM
    private static final int EXTERNAL_STORAGE = 1; // 外置SD卡
    private static final String TAG = "storageInfo";

    // RAM
    public static String getRAMInfo(Context context) {
        long totalSize = 0;
        long availableSize = 0;

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 全局内存的使用信息
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        totalSize = memoryInfo.totalMem; // 单位B
        availableSize = memoryInfo.availMem;

        return "可用/总共："
                + Formatter.formatFileSize(context, availableSize)
                + "/" + Formatter.formatFileSize(context, totalSize);
    }

    // 判断SD是否挂载
    public static boolean isSDCardMount() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    // 获取ROM信息
    public static String getROMInfo(Context context){
        // getDataDirectory是内部存储
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath()); // /storage/emulated/0
        long totalCounts = statFs.getBlockCountLong(); // 总块数
        long availableCounts = statFs.getAvailableBlocksLong() ; // 可用块数
        long size = statFs.getBlockSizeLong(); // 块大小
        long availROMSize = availableCounts * size;
        long totalROMSize = totalCounts *size;

        return "可用/总共："
                + Formatter.formatFileSize(context, availROMSize) + "/"
                + Formatter.formatFileSize(context, totalROMSize);
    }

    // SD卡
    public static String getSDCardInfo(Context context){
        if (isSDCardMount()){
            /**
             * Android 4.4 开始，系统将机身存储划分为内部存储和外部存储，这样在没有扩展SD卡时，
             * 外部存储就是机身存储的一部分，指向/storage/emulated/0。
             * 当有扩展SD卡插入时，系统将获得两个外部存储路径。
             */
            // TODO: 2021/8/27 有sd卡但还是指向 /storage/emulated/0
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath()); // /storage/emulated/0  正确路径是/storage/0AF1-3D1B
//            StatFs statFs = new StatFs("/storage/0AF1-3D1B");
//            StatFs statFs = new StatFs("/sdcard");
//            StatFs statFs = new StatFs("/mnt/sdcard");
            Log.d(TAG, "getSDCardInfo: " + Environment.getExternalStorageDirectory().getPath());
            long totalCounts = statFs.getBlockCountLong(); // 总块数
            long availableCounts = statFs.getAvailableBlocksLong() ; // 可用块数
            long size = statFs.getBlockSizeLong(); // 块大小
            long availROMSize = availableCounts * size;
            long totalROMSize = totalCounts *size;

            return "可用/总共："
                    + Formatter.formatFileSize(context, availROMSize) + "/"
                    + Formatter.formatFileSize(context, totalROMSize);
        } else {
            return "无外置SD卡";
        }
    }

    // 获取ROM信息 0：内部ROM，1：外置SD卡
    public static String getStorageInfo(Context context, int type) {
        String path = getStoragePath(context, type);
        // 判断是否有外置SD卡
        if (!isSDCardMount() || TextUtils.isEmpty(path) || path == null) {
            return "无外置SD卡";
        }

        File file = new File(path);
        StatFs statFs = new StatFs(file.getPath());

        long blockCount = statFs.getBlockCountLong(); // 总块数
        long availableBlocks = statFs.getAvailableBlocksLong(); // 可用块数
        long blockSize = statFs.getBlockSizeLong(); // 块大小
        long totalSpace = blockSize * blockCount;
        long availableSpace = availableBlocks * blockSize;

        return "可用/总共："
                + Formatter.formatFileSize(context, availableSpace) + "/"
                + Formatter.formatFileSize(context, totalSpace);

    }

    // TODO: 2021/8/27
    // 获取手机存储路径
    public static String getStoragePath(Context context, int type) {
        StorageManager sm = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
        try {
            Method getPathsMethod = sm.getClass().getMethod("getVolumePaths", (Class<?>[]) null);
            String[] path = (String[]) getPathsMethod.invoke(sm, (Object[]) null);
            switch (type) {
                case INTERNAL_STORAGE:
                    return path[type];
                case EXTERNAL_STORAGE:
                    if (path.length > 1) {
                        return path[type];
                    } else {
                        return null;
                    }
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
