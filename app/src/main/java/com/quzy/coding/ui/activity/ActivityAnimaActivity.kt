package com.quzy.coding.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.event.DialogEvent
import com.quzy.coding.databinding.ActivityAnimaSessionBinding
import com.quzy.coding.databinding.ActivityDrawWithRichtextBinding
import com.quzy.coding.ui.manager.CouponNewCustomerDialogManager
import org.greenrobot.eventbus.EventBus

/**
 * CreateDate:2022/7/1 9:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: Activity转场动画
 */
class ActivityAnimaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.dialog_slid_up, R.anim.dialog_slid_down)
        super.onCreate(savedInstanceState)
    }

    var viewBinding: ActivityAnimaSessionBinding ? = null
    override fun onViewCreated() {
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityAnimaSessionBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.dialog_slid_up, R.anim.dialog_slid_down)
    }
}
