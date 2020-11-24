package com.quzy.coding.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/24
 * desc   :
 * version: 1.0
 */


public class LibActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks{

    public static final String TAG = LibActivityLifecycleCallbacks.class.getSimpleName();

    IActivityLifecycleCallbacks iActivityLifecycleCallbacks;

    public void setiActivityLifecycleCallbacks(IActivityLifecycleCallbacks iActivityLifecycleCallbacks) {
        this.iActivityLifecycleCallbacks = iActivityLifecycleCallbacks;
    }

    public interface IActivityLifecycleCallbacks {
        void onActivityCreated(Activity activity, Bundle savedInstanceState);
        void onActivityStarted(Activity activity);
        void onActivityResumed(Activity activity);
        void onActivityPaused(Activity activity);
        void onActivityStopped(Activity activity);
        void onActivitySaveInstanceState(Activity activity, Bundle outState);
        void onActivityDestroyed(Activity activity);
    }
    protected ActivityManager activityManager = ActivityManager.getInstance();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityManager.pushActivity(activity);
        ActivityManager.setCurrentActivity(activity);
        if(iActivityLifecycleCallbacks!=null){
            iActivityLifecycleCallbacks.onActivityCreated(activity,savedInstanceState);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

        if(iActivityLifecycleCallbacks!=null){
            iActivityLifecycleCallbacks.onActivityStarted(activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityManager.setCurrentActivity(activity);
        if(iActivityLifecycleCallbacks!=null){
            iActivityLifecycleCallbacks.onActivityResumed(activity);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ActivityManager.clearCurrentActivity(activity);
        if(iActivityLifecycleCallbacks!=null){
            iActivityLifecycleCallbacks.onActivityPaused(activity);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if(iActivityLifecycleCallbacks!=null){
            iActivityLifecycleCallbacks.onActivityStopped(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        if(iActivityLifecycleCallbacks!=null){
            iActivityLifecycleCallbacks.onActivitySaveInstanceState(activity,outState);
        }
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityManager.popActivity(activity);
        if(iActivityLifecycleCallbacks!=null){
            iActivityLifecycleCallbacks.onActivityDestroyed(activity);
        }
    }
}
