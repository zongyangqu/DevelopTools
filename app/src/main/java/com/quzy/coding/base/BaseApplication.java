package com.quzy.coding.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.quzy.coding.R;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：https://github.com/Sunzxyong/Recovery
 */

public class BaseApplication extends MultiDexApplication {

    private static Application appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
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

}
