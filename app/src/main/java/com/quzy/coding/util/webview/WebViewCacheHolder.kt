package com.quzy.coding.util.webview

import android.app.Application
import android.content.Context
import android.content.MutableContextWrapper
import android.os.Looper
import extension.log
import github.leavesczy.robustwebview.base.RobustWebView
import java.util.*

/**
 * CreateDate:2022/1/18 17:50
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.webview
 * @Description:
 */
object WebViewCacheHolder {

    private val webViewCacheStack = Stack<RobustWebView>()

    private const val CACHED_WEB_VIEW_MAX_NUM = 4

    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
        prepareWebView()
    }

    fun prepareWebView() {
        if (webViewCacheStack.size < CACHED_WEB_VIEW_MAX_NUM) {
            Looper.myQueue().addIdleHandler {
                log("WebViewCacheStack Size: " + webViewCacheStack.size)
                if (webViewCacheStack.size < CACHED_WEB_VIEW_MAX_NUM) {
                    webViewCacheStack.push(createWebView(MutableContextWrapper(application)))
                }
                false
            }
        }
    }

    fun acquireWebViewInternal(context: Context): RobustWebView {
        if (webViewCacheStack.isEmpty()) {
            return createWebView(context)
        }
        val webView = webViewCacheStack.pop()
        val contextWrapper = webView.context as MutableContextWrapper
        contextWrapper.baseContext = context
        return webView
    }

    private fun createWebView(context: Context): RobustWebView {
        return RobustWebView(context)
    }

}