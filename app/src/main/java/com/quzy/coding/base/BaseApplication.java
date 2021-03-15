package com.quzy.coding.base;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDexApplication;

import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.coding.qzy.baselibrary.widget.external_resource.SkinManager;
import com.quzy.coding.R;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.PathClassLoader;


/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：https://github.com/Sunzxyong/Recovery
 */

public class BaseApplication extends MultiDexApplication {

    public static BaseApplication appContext;

    //外部资源包APK地址
    private File apk;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        //加载外部资源包  start
        apk = new File(getCacheDir() + "/resource.apk");
        SkinManager.getInstance().init(this);
        if(apk.exists()){
            //加载资源包
            SkinManager.getInstance().loadResourceApk(apk.getPath());
        }
        //加载外部资源包  end

        Bugly.init(getApplicationContext(), "f402a023c2", true);
        //CrashReport.initCrashReport(getApplicationContext(), "f402a023c2", false);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LibActivityLifecycleCallbacks libActivityLifecycleCallbacks = new LibActivityLifecycleCallbacks();
        libActivityLifecycleCallbacks.setiActivityLifecycleCallbacks(new RealActivityLifecycleCallbacks());
        registerActivityLifecycleCallbacks(libActivityLifecycleCallbacks);
        LeakCanary.install(this);
        LogTools.init(true, getString(R.string.app_name));
    }

    public static Context getContext() {
        return appContext;
    }

    public  File getResourceApk(){
        return apk;
    }


    //热修复加载 合并补丁dex文件
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        File apk = new File(getCacheDir() + "/hotfix.dex");
        if (apk.exists()) {
            try {
                ClassLoader classLoader = getClassLoader();
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
