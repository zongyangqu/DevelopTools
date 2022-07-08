package github.leavesczy.robustwebview

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.coding.qzy.baselibrary.utils.AbToastUtil
import com.coding.qzy.baselibrary.utils.AbToastUtil.SHOW_TOAST
import com.coding.qzy.baselibrary.utils.AbToastUtil.showToast
import com.quzy.coding.base.BaseApplication
import extension.log

/**
 * @Author: leavesCZY
 * @Date: 2021/9/21 15:08
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class JsInterface(var context: Context) {

    @JavascriptInterface
    fun showToastByAndroid(log: String) {
        log("showToastByAndroid:$log")
        showToast(context,log)
    }




}