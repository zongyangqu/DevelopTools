package com.quzy.coding.util.webview

import android.app.Application
import android.content.Context
import com.coding.qzy.baselibrary.utils.AbToastUtil
import com.quzy.coding.base.BaseApplication
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import extension.log

/**
 * CreateDate:2022/1/18 17:46
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.webview
 * @Description:
 */
object WebViewInitTask {
    fun init(application: Application) {
        initWebView(application)
        WebViewCacheHolder.init(BaseApplication.getContext() as Application)
        WebViewInterceptRequestProxy.init(application)
    }

    private fun initWebView(context: Context) {
        QbSdk.setDownloadWithoutWifi(true)
        val map = mutableMapOf<String, Any>()
        map[TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(map)
        val cb: QbSdk.PreInitCallback = object : QbSdk.PreInitCallback {
            override fun onViewInitFinished(arg0: Boolean) {
                AbToastUtil.showToast(BaseApplication.getContext(),"onViewInitFinished: $arg0")
                log("onViewInitFinished: $arg0")
            }

            override fun onCoreInitFinished() {
                log("onCoreInitFinished")
            }
        }
        QbSdk.initX5Environment(context, cb)
    }

}