package com.quzy.coding.util.viewreport

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.util.Constants

/**
 * CreateDate:2023/1/4 16:51
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.viewreport
 * @Description: 开启服务 监听Activity或者Fragment生命周期
 */
class ViewLayoutReportService : Service() {


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtils.tag(Constants.LOG_TAG).d("---------------->start servic")
        var activityLifecycleCallbacks = ReportActivityLifecycleCallbacks();
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        return super.onStartCommand(intent, flags, startId)
    }
}