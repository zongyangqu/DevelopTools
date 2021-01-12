package com.quzy.coding.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.quzy.coding.R;
import com.squareup.leakcanary.LeakCanary;


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
