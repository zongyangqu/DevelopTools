package com.quzy.coding.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager

/**
 * CreateDate:2022/7/1 9:51
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
abstract class BaseDialogFragment : AppCompatDialogFragment {
    protected var mRootView: View? = null

    // 给弹窗设置id，埋点时用于区分
    var rootViewID: Int? = null

    var rootViewDescription: String? = null

    var dismissListener: DismissListener? = null

    constructor()

    constructor(rootViewDescription: String?) : this() {
        this.rootViewDescription = rootViewDescription
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        setDialogStyle(dialog)
        mRootView = LayoutInflater.from(context).inflate(getDialogResourceId(), null)

        rootViewID?.apply {
            mRootView?.id = this
        }

        mRootView?.let { view ->
            rootViewDescription?.let {
                view.contentDescription = it
            }
            dialog.setContentView(view)
            initView(view)
        }

        context?.let {
            updateSkinUI(it)
        }

        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        afterView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    open fun afterView() {}

    abstract fun getDialogResourceId(): Int

    abstract fun initView(view: View)

    protected open fun updateSkinUI(context: Context) {
    }

    open fun setDialogStyle(dialog: Dialog?) {
    }

    override fun dismiss() {
//        super.dismiss()
        dismissAllowingStateLoss()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            var fragment = manager.findFragmentByTag(tag)
            if (fragment == null || !fragment.isAdded) {
                // 在每个add事务前增加一个remove事务，防止连续的add
                manager.beginTransaction().remove(this).commitAllowingStateLoss()

                val trans = manager.beginTransaction()
                trans.add(this, tag)
                trans.commitAllowingStateLoss()
            }
        } catch (e: Exception) {
            // 同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace()
        }
    }

    fun setonDismissListener(action: () -> Unit) {
        dismissListener = object :
            DismissListener {
            override fun onDismiss() {
                action()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dismissListener?.onDismiss()
    }

    interface DismissListener {
        fun onDismiss()
    }
}
