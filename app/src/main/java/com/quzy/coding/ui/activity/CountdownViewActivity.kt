package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityCountViewBinding
import com.quzy.coding.util.widget.ScrollCountdownView


/**
 * CreateDate:2023/5/24 15:04
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 显示指定时间戳倒计时
 */
class CountdownViewActivity: BaseActivity() {

    var viewBinding : ActivityCountViewBinding?= null
    override fun onViewCreated() {
        val mCurrentTime = System.currentTimeMillis()
        val oneDayTimeMillis = 1000 * 60 * 60 * 24

        viewBinding?.adCountdownView?.setEndTime(mCurrentTime + oneDayTimeMillis*2)
        viewBinding?.adCountdownView?.startCountDown()

        viewBinding?.adCountdownView?.setOnCountDownListener(object :ScrollCountdownView.OnCountDownListener{
            override fun onStop() {
                // 当前场次倒计时结束了
            }

            override fun onStartNext() {
                // 到达预约开始倒计时的时间了
            }

            override fun onCountDown(leftTime: Long) {
                //每次倒数回调一次
            }

        })
    }


    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View?{
        viewBinding = ActivityCountViewBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

}