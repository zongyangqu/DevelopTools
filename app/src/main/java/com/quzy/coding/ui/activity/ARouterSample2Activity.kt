package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityTestArouter2Binding

/**
 * CreateDate:2022/6/17 11:42
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
@Route(path = "/test/ARouterSample2Activity")
class ARouterSample2Activity : BaseActivity() {

    @Autowired(name = "msg")
    @JvmField
    var msg: String ? = null
    var viewBinding: ActivityTestArouter2Binding? = null
    override fun onViewCreated() {
        ARouter.getInstance().inject(this)
        viewBinding?.tvARouter?.text = msg
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityTestArouter2Binding.inflate(LayoutInflater.from(this))
        viewBinding?.root?.let {
            return it.rootView
        }
        return null
    }
}
