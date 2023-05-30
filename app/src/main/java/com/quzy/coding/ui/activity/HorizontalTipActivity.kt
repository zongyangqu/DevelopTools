package com.quzy.coding.ui.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.Anim
import com.quzy.coding.databinding.ActivityHorizontalTipBinding
import com.quzy.coding.databinding.ActivityTransIndicatorBinding
import com.quzy.coding.util.Constants
import com.quzy.coding.util.extend.click

/**
 * CreateDate:2023/4/13 17:41
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class HorizontalTipActivity : BaseActivity() {

    var viewBinding : ActivityHorizontalTipBinding?= null

    override fun onViewCreated() {

        viewBinding?.transindicatorBtn?.click {
            startActivity(Intent(activity,SmoothTransIndicatorActivity::class.java))
        }

        viewBinding?.indicatorBtn?.click {
            startActivity(Intent(activity,ViewPagerIndicatorActivity::class.java))
        }

        viewBinding?.recyclerBtn?.click {

        }

        viewBinding?.recyclerPagerBtn?.click {
            startActivity(Intent(activity,HorizontalViewPagerRvActivity::class.java))
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View?{
        viewBinding = ActivityHorizontalTipBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }
}