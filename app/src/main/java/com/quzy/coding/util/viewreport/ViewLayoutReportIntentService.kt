package com.quzy.coding.util.viewreport

import android.app.IntentService
import android.content.Intent
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.util.Constants

/**
 * CreateDate:2023/1/13 10:27
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.viewreport
 * @Description:
 */
class ViewLayoutReportIntentService : IntentService("ViewLayoutReportIntentService") {


    override fun onCreate() {
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        LogUtils.tag(Constants.LOG_TAG).d("---------------->start ViewLayoutReportIntentService")
        var activityLifecycleCallbacks = ReportActivityLifecycleCallbacks();
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}