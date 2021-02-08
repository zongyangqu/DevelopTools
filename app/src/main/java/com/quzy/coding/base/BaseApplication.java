package com.quzy.coding.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.coding.qzy.baselibrary.widget.external_resource.SkinManager;
import com.quzy.coding.R;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;


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

        apk = new File(getCacheDir() + "/resource.apk");
        SkinManager.getInstance().init(this);
        if(apk.exists()){
            //加载资源包
            SkinManager.getInstance().loadResourceApk(apk.getPath());
        }

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

}
