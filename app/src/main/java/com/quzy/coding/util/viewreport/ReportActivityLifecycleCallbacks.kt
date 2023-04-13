package com.quzy.coding.util.viewreport

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.util.Constants
import kotlinx.coroutines.*
import java.lang.ref.SoftReference

/**
 * CreateDate:2023/1/4 16:52
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.viewreport
 * @Description: Activity监控回调
 */
class ReportActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    private val fragmentLifeCycle =
        ReportFragmentLifecycleCallbacks()

    private val scope by lazy {
        CoroutineScope(
            SupervisorJob() + Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
               // YHLog.e("TypefaceLoadError", throwable)
                LogUtils.tag(Constants.LOG_TAG).d("------------------------------------加载错误")
            }
        )
    }


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        LogUtils.tag(Constants.LOG_TAG).d("-----Activity is " + activity?.javaClass?.name)
        if (activity is FragmentActivity) {
            LogUtils.tag(Constants.LOG_TAG).d("zongyangactf", "-----Activity is " + activity.javaClass.name)
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                fragmentLifeCycle,
                true
            )
        }
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        scope.launch {
            val weakActivity: SoftReference<Activity> = SoftReference(activity)
            if (ReportViewUtils.getRepostConfigIsOpen() && ReportViewUtils.reportPageIsMatch(
                    weakActivity.get()
                )
            ) {
                //val view = weakActivity.get()?.window?.decorView;
                val view = weakActivity.get()?.window?.decorView?.findViewById<View>(android.R.id.content)
                var count = ReportViewUtils.traverseViewGroup(weakActivity.get(), view)
                LogUtils.tag(Constants.LOG_TAG).d(
                    "-----最终Activity count " + weakActivity.get()?.javaClass?.name + "======" + count
                )
                if (weakActivity.get() is FragmentActivity) {
                    LogUtils.tag(Constants.LOG_TAG).d(
                        "-----Activity is match is " + weakActivity.get()?.javaClass?.name
                    )
                    (weakActivity.get() as? FragmentActivity)?.supportFragmentManager?.unregisterFragmentLifecycleCallbacks(
                        fragmentLifeCycle
                    )
                }
            }
        }
    }

    companion object {
        val TAG = ReportActivityLifecycleCallbacks::class.java.simpleName
    }



}