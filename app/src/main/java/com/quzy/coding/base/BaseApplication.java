package com.quzy.coding.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Config;
import android.util.Log;

import com.coding.qzy.baselibrary.utils.log.LogTools;
import com.quzy.coding.R;


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
        LogTools.init(true, getString(R.string.app_name));
    }

    public static Context getContext() {
        return appContext;
    }

}
