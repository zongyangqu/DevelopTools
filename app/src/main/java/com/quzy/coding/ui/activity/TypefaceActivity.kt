package com.quzy.coding.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityTypeFaceBinding
import com.quzy.coding.util.extend.singleClick
import java.io.File

/**
 * CreateDate:2022/9/23 15:40
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 字体库使用
 */
class TypefaceActivity : BaseActivity() {

    var viewBinding: ActivityTypeFaceBinding? = null
    override fun onViewCreated() {
        viewBinding?.jump?.singleClick {
            activity.startActivity(Intent(activity, ActivityAnimaActivity::class.java))
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityTypeFaceBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }
}
