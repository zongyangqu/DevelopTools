package com.coding.qzy.baselibrary.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2021/03/15
 * desc   : 插件化工具类
 * version: 1.0
 */


public class PluginLoadUtil {


    private static volatile PluginLoadUtil pluginLoadUtil;

    private PluginLoadUtil() {
    }

    public static  PluginLoadUtil getInstance() {
        if (pluginLoadUtil == null) {
            synchronized (PluginLoadUtil.class) {
                if (pluginLoadUtil == null) {
                    pluginLoadUtil = new PluginLoadUtil();
                }
            }
        }
        return pluginLoadUtil;
    }

    /**
     * 热修复工具类
     * @param dexName
     * @param context
     */
    public void loadHotFix(String dexName, Context context){
        File apk = new File(context.getCacheDir() + dexName);
        if (apk.exists()) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                Class loaderClass = BaseDexClassLoader.class;
                Field dexPathListField = loaderClass.getDeclaredField("pathList");
                dexPathListField.setAccessible(true);
                Object pathListObject = dexPathListField.get(classLoader);
                Class pathListClass = pathListObject.getClass();
                Field dexElementsField = pathListClass.getDeclaredField("dexElements");
                dexElementsField.setAccessible(true);
                Object dexElementsObject = dexElementsField.get(pathListObject);

                // classLoader.pathList.dexElements = ???;
                PathClassLoader newClassLoader;
                if(Build.VERSION.SDK_INT<Build.VERSION_CODES.N){
                    newClassLoader = new PathClassLoader(apk.getPath(), classLoader);
                }else{
                    newClassLoader = new PathClassLoader(apk.getPath(), null);
                }
                Object newPathListObject = dexPathListField.get(newClassLoader);
                Object newDexElementsObject = dexElementsField.get(newPathListObject);

                int oldLength = Array.getLength(dexElementsObject);
                int newLength = Array.getLength(newDexElementsObject);
                Object concatDexElementsObject = Array.newInstance(dexElementsObject.getClass().getComponentType(), oldLength + newLength);
                for (int i = 0; i < newLength; i++) {
                    Array.set(concatDexElementsObject, i, Array.get(newDexElementsObject, i));
                }
                for (int i = 0; i < oldLength; i++) {
                    Array.set(concatDexElementsObject, newLength + i, Array.get(dexElementsObject, i));
                }

                dexElementsField.set(pathListObject, concatDexElementsObject);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
