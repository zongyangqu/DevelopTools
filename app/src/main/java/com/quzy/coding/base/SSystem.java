package com.quzy.coding.base;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/24
 * desc   :
 * version: 1.0
 */


public class SSystem {


    private static int versionCode = 0;

    private static String versionName = null;

    private static String appName = null;

    /**
     * 版本名
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        if(versionName==null){
            return getPackageInfo(context).versionName;
        }
        return versionName;
    }


    /**
     * 版本号
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        if(versionCode==0){
            return getPackageInfo(context).versionCode;
        }else{
            return versionCode;
        }
    }

    /**
     * 获取app名称
     * @return
     */
    public static String getAppName(Context context){
        if(appName==null){
            getPackageInfo(context);
        }
        return appName;
    }


    /**
     * 获取版本信息
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            versionCode = pi.versionCode;
            versionName = pi.versionName;
            appName = context.getApplicationInfo().loadLabel(pm).toString();
            return pi;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return pi;
    }



    /**
     * ro.serialno的用处是来保存唯一设备号
     * @return
     */
    public static String getOnlyId(){
        return getAndroidOsSystemProperties("ro.serialno");
    }
    /**
     *
     * @param key
     * @return
     */
    private static String getAndroidOsSystemProperties(String key) {
        String ret = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            ret = (String) get.invoke(c, key);
        }  catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (ClassNotFoundException e) {
        }
        return ret;
    }

    public static final String getOsInfo() {
        return Build.VERSION.RELEASE;
    }

    public static final String getPhoneModelWithManufacturer() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    /**
     * 判断是否有sd卡
     * @return
     */
    public static boolean isSdcard(){
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否有这个软件包
     * @param packageName
     * @return
     */
    public static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    /**
     * 通过进程id获取包名
     * @param pID
     * @return
     */
    public static String getAppName(int pID) {
        String processName = null;
        android.app.ActivityManager am = (android.app.ActivityManager) BaseApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            android.app.ActivityManager.RunningAppProcessInfo info = (android.app.ActivityManager.RunningAppProcessInfo) (i.next());
            if (info.pid == pID) {
                processName = info.processName;
                return processName;
            }
        }
        return processName;
    }

    /**
     * 判断当前进程是否是主进程
     * @param context
     * @return
     */
    public static boolean inMainProcess(Context context) {
        String packageName = context.getPackageName();
        String processName = getProcessName(context);
        return packageName.equals(processName);
    }

    /**
     * 获取当前进程名
     * @param context
     * @return 进程名
     */
    public static final String getProcessName(Context context) {
        String processName = null;
        android.app.ActivityManager am = ((android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        for (android.app.ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
            if (info.pid == android.os.Process.myPid()) {
                processName = info.processName;
                break;
            }
        }
        if (!TextUtils.isEmpty(processName)) {
            return processName;
        }else{
            return null;
        }
    }

    public static String getClassName(Class clazz){
        return clazz.getSimpleName() + ".java";
    }

    public static void sysMemoryInfo(){

        android.app.ActivityManager activityManager=(android.app.ActivityManager) BaseApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        android.app.ActivityManager.MemoryInfo memInfo=new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memInfo);
//        ULog.it("memInfo","availMem:",memInfo.availMem/1024+" kb","\n",
//                "threshold:",memInfo.threshold/1024+" kb","\n",
//                "totalMem:"+memInfo.totalMem/1024+" kb","\n",
//                "lowMemory:"+memInfo.lowMemory);
    }


}

