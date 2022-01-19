package com.quzy.coding.ui.activity

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.coding.qzy.baselibrary.utils.AbToastUtil
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.util.webview.WebViewCacheHolder
import github.leavesczy.robustwebview.JsInterface
import github.leavesczy.robustwebview.base.RobustWebView
import github.leavesczy.robustwebview.base.WebViewListener
import kotlinx.android.synthetic.main.activity_webview_hybrid.*

/**
 * CreateDate:2022/1/19 15:25
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class WebViewhybridSampleActivity : BaseActivity() {

    var webview : WebView?= null


    override fun onViewCreated() {
        email_sign_in_button?.setOnClickListener {
            checkAccount()
        }
        initWebView()
    }

    private fun checkAccount() {
        val username = email!!.text.toString().trim { it <= ' ' }
        val psw = password!!.text.toString().trim { it <= ' ' }
        //判断非空
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(psw)) {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show()
        } else {
            login(username) // 登陆，传入用户名
        }
    }


    private fun login(username: String) {
        //将参数传到H5页面，注意括号中的书写格式
        webview?.loadUrl("javascript:javaCallJs('$username')")

        var msg = "我来自原生Native"
        webview?.evaluateJavascript("javascript:showFromAndroid('$msg')",object : ValueCallback<String> {
            override fun onReceiveValue(p0: String?) {
                Log.d("zongyang",p0)
            }
        })

        setContentView(webview) //使webView显示页面
    }

    private fun initWebView() {
        webview = WebView(this)
        val webSettings: WebSettings? = webview?.getSettings()
        //设置支持javaScript
        webSettings?.javaScriptEnabled = true
        //设置不调用系统自带的浏览器
        webview?.webViewClient = WebViewClient()
        webview?.addJavascriptInterface(JsInterface(this),"android")
        webview?.loadUrl("""file:/android_asset/javascript1.html""")
    }



    override fun getLayoutId(): Int {
        return R.layout.activity_webview_hybrid
    }

    override fun getLayoutView(): View? {
        return null
    }


}