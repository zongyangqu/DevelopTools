package com.quzy.coding.util.viewreport

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.util.Constants

/**
 * CreateDate:2023/1/4 16:53
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.viewreport
 * @Description: Activity监控回调
 */
class ReportFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks(){

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        LogUtils.tag(Constants.LOG_TAG).d( "Destroyed------>" + f.javaClass.name)

        if (ReportViewUtils.getRepostConfigIsOpen() && ReportViewUtils.reportPageIsMatch(f)) {
            val view = f.view
            var count = ReportViewUtils.traverseViewGroup(f, view)
            LogUtils.tag(Constants.LOG_TAG).d("-----最终Fragment count " + f.javaClass.name + "======" + count)
        }

    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        LogUtils.tag(Constants.LOG_TAG).d( "Paused------>" + f.javaClass.name)
    }

    override fun onFragmentActivityCreated(
        fm: FragmentManager,
        f: Fragment,
        savedInstanceState: Bundle?
    ) {
        LogUtils.tag(Constants.LOG_TAG).d("onFragmentActivityCreated------>" + f.javaClass.name)
    }

    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        LogUtils.tag(Constants.LOG_TAG).d("onFragmentCreated------>" + f.javaClass.name)
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        LogUtils.tag(Constants.LOG_TAG).d("onFragmentDestroyed------>" + f.javaClass.name)
    }

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        f: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        LogUtils.tag(Constants.LOG_TAG).d( "onFragmentViewCreated------>" + f.javaClass.name)
    }


    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        LogUtils.tag(Constants.LOG_TAG).d( "onFragmentResumed------>" + f.javaClass.name)
    }


    companion object {
        val TAG = ReportFragmentLifecycleCallbacks::class.java.simpleName
    }

}