package com.quzy.coding.base;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.apkfuns.logutils.LogUtils;
import com.coding.qzy.baselibrary.utils.AppVersionUtils;
import com.coding.qzy.baselibrary.utils.PluginLoadUtil;
import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.coding.qzy.baselibrary.widget.external_resource.SkinManager;
import com.quzy.coding.R;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;

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
        int code = AppVersionUtils.currentApkVersionCode(base);
        String name = AppVersionUtils.currentApkVersionName(base);
        LogUtils.tag("code").d(code+"");
        LogUtils.tag("name").d(name+"");
        PluginLoadUtil.getInstance().loadHotFix("/hotfix.dex",this);
    }



}
