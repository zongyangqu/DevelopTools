package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityTestArouterBinding
import com.quzy.coding.ui.adapter.HorizontalRefreshItemViewAdapter
import kotlinx.android.synthetic.main.view_content_service_help.view.*

/**
 * CreateDate:2022/6/17 11:42
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class ARouterSampleActivity : BaseActivity() {

    var viewBinding: ActivityTestArouterBinding? = null

    override fun onViewCreated() {
        viewBinding?.btnARouter?.setOnClickListener {
            ARouter.getInstance().build("/test/ARouterSample2Activity")
                .withString("msg", "我是通过ARouter传递过来的值").navigation()
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityTestArouterBinding.inflate(LayoutInflater.from(this))
        viewBinding?.root?.let {
            return it.rootView
        }
        return null
    }
}
