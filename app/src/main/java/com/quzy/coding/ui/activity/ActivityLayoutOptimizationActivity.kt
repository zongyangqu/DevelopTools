package com.quzy.coding.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.event.DialogEvent
import com.quzy.coding.databinding.ActivityAnimaSessionBinding
import com.quzy.coding.databinding.ActivityDrawWithRichtextBinding
import com.quzy.coding.databinding.ActivityLayoutOptimizationBinding
import com.quzy.coding.ui.manager.CouponNewCustomerDialogManager
import com.quzy.coding.util.Constants
import com.quzy.coding.util.extend.singleClick
import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * CreateDate:2022/7/1 9:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 布局优化
 */
class ActivityLayoutOptimizationActivity : BaseActivity() {


    var lastLoadTime :Long = 0L

    var viewBinding: ActivityLayoutOptimizationBinding ? = null
    override fun onViewCreated() {
        var view : View? = viewBinding?.fragmentMemberOffLineLL?.inflate()
        view?.findViewById<AppCompatButton>(R.id.btnARouter)

        viewBinding?.changeHiddenBtn?.singleClick {
            if ((Date().time -lastLoadTime) > 10000) {
                lastLoadTime = Date().time
                LogUtils.tag(Constants.LOG_TAG).d(Date().time.toString())
            }

            view?.visibility = View.GONE
        }

        viewBinding?.changeShowBtn?.singleClick {
            view?.visibility = View.VISIBLE
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityLayoutOptimizationBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

}
