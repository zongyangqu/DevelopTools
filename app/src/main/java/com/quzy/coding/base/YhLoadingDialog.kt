package com.quzy.coding.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import com.quzy.coding.R
import com.quzy.coding.util.extend.singleClick

/**
 * CreateDate:2023/6/16 17:02
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
class YhLoadingDialog : Dialog {
    internal var context: Context? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(context: Context, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : super(context, cancelable, cancelListener)

    companion object {

        fun createDialog(context: Context): YhLoadingDialog {
            val dialog = YhLoadingDialog(context, R.style.loading_dialog)
            val view = View.inflate(context, R.layout.dialog_loading, null)
            dialog.setContentView(view)
            dialog.window?.attributes?.gravity = Gravity.CENTER
            view.singleClick {
                dialog.dismiss()
            }
            return dialog
        }
    }
}
