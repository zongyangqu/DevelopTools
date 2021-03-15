package com.coding.qzy.baselibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/03/15
 * desc   :
 * version: 1.0
 */


public class AppVersionUtils {


    public static int currentApkVersionCode(Context context) {
        int versionCode = 0;

        try {
            PackageManager e = context.getPackageManager();
            PackageInfo pi = e.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
        } catch (Exception var3) {
            ;
        }

        return versionCode;
    }

    public static String currentApkVersionName(Context context) {
        String versionName = "";

        try {
            PackageManager e = context.getPackageManager();
            PackageInfo pi = e.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception var3) {
            ;
        }

        return versionName;
    }
}
