package com.quzy.coding.ui.activity

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.utils.AbToastUtil
import com.quzy.coding.Question
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.QuestionInfo
import com.quzy.coding.util.CardTransformer
import com.quzy.coding.util.JsonUtils
import com.quzy.coding.util.webview.WebViewCacheHolder
import com.quzy.coding.util.widget.NoSwipeViewPager
import github.leavesczy.robustwebview.base.RobustWebView
import github.leavesczy.robustwebview.base.WebViewListener
import java.util.*
import kotlin.collections.ArrayList

/**
 * CreateDate:2022/1/18 17:52
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: webView优化方案
 */
class WebViewRecyclerActivity :BaseActivity() {

    private val webViewContainer by lazy {
        findViewById<ViewGroup>(R.id.webViewContainer)
    }

    private val tvTitle by lazy {
        findViewById<TextView>(R.id.tvTitle)
    }

    private val tvProgress by lazy {
        findViewById<TextView>(R.id.tvProgress)
    }

    private val url1 = "https://juejin.cn/user/923245496518439/posts"

    private val url2 = "https://www.bilibili.com/"

    private val url3 =
            "https://p26-passport.byteacctimg.com/img/user-avatar/6019f80db5be42d33c31c98adaf3fa8c~300x300.image"

    private lateinit var webView: RobustWebView

    private val webViewListener = object : WebViewListener {
        override fun onProgressChanged(webView: RobustWebView, progress: Int) {
            tvProgress.text = progress.toString()
        }

        override fun onReceivedTitle(webView: RobustWebView, title: String) {
            tvTitle.text = title
        }

        override fun onPageFinished(webView: RobustWebView, url: String) {

        }
    }


    override fun onViewCreated() {
        webView = WebViewCacheHolder.acquireWebViewInternal(this)
        webView.webViewListener = webViewListener
        val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        webViewContainer.addView(webView, layoutParams)
        findViewById<View>(R.id.tvBack).setOnClickListener {
            onBackPressed()
        }
        findViewById<View>(R.id.btnOpenUrl1).setOnClickListener {
            webView.loadUrl(url1)
        }
        findViewById<View>(R.id.btnOpenUrl2).setOnClickListener {
            webView.loadUrl(url2)
        }
        findViewById<View>(R.id.btnOpenUrl3).setOnClickListener {
            webView.toLoadUrl(url3, "")
        }
        findViewById<View>(R.id.btnReload).setOnClickListener {
            webView.reload()
        }
        findViewById<View>(R.id.btnOpenHtml).setOnClickListener {
            webView.loadUrl("""file:/android_asset/javascript.html""")
        }
        findViewById<View>(R.id.btnCallJsByAndroid).setOnClickListener {
            val parameter = "\"业志陈\""
            webView.evaluateJavascript(
                    "javascript:callJsByAndroid(${parameter})"
            ) {
                AbToastUtil.showToast(this,"evaluateJavascript: $it")
            }
//            webView.loadUrl("javascript:callJsByAndroid(${parameter})")
        }
        findViewById<View>(R.id.btnShowToastByAndroid).setOnClickListener {
            webView.loadUrl("javascript:showToastByAndroid()")
        }
        findViewById<View>(R.id.btnCallJsPrompt).setOnClickListener {
            webView.loadUrl("javascript:callJsPrompt()")
        }
    }



    override fun getLayoutId(): Int {
        return R.layout.activity_webview_recycler
    }

    override fun getLayoutView(): View? {
        return null
    }


}