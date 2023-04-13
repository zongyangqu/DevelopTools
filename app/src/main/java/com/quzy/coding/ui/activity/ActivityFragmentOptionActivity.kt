package com.quzy.coding.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import com.apkfuns.logutils.LogUtils
import com.google.android.material.tabs.TabLayout
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.SecKillCategoryInfoVO
import com.quzy.coding.bean.SecKillCategoryVO
import com.quzy.coding.bean.event.DialogEvent
import com.quzy.coding.databinding.ActivityFragmentActionBinding
import com.quzy.coding.databinding.LayoutTabSecKillItemBinding
import com.quzy.coding.ui.adapter.CategoryPageAdapter
import com.quzy.coding.ui.fragment.SecKillCategoryFragment
import com.quzy.coding.ui.manager.CouponNewCustomerDialogManager
import com.quzy.coding.util.Constants
import com.quzy.coding.util.NestedCeilingHelper
import com.quzy.coding.util.ResourceUtil
import com.quzy.coding.util.extend.singleClick
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.collections.forEachWithIndex
import java.util.*
import kotlin.concurrent.timerTask

/**
 * CreateDate:2022/7/1 9:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 布局优化
 */
class ActivityFragmentOptionActivity : BaseActivity() , SecKillCategoryFragment.CategoryCallback {


    var lastLoadTime :Long = 0L

    var viewBinding: ActivityFragmentActionBinding ? = null
    private var pageAdapter: CategoryPageAdapter ?= null
    var currentPosition = 0

    var mGetBarCodeTimer: Timer? = null

    override fun onViewCreated() {
        pageAdapter = CategoryPageAdapter("",10,"", supportFragmentManager, this)
        viewBinding?.categoryViewPager?.adapter = pageAdapter
        viewBinding?.categoryTab?.setupWithViewPager(viewBinding?.categoryViewPager)
        viewBinding?.categoryTab?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
                tabIsSelect(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tabDisSelect(tab)
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                currentPosition = tab.position
                tabIsSelect(tab)
            }
        })

        val categories = mutableListOf<SecKillCategoryVO>()
        for (i in 1 until 2) {
            var sVo : SecKillCategoryVO = SecKillCategoryVO("001$i","通用会员$i","")
            categories.add(sVo)
        }
        var sivo = SecKillCategoryInfoVO()
        sivo?.categories = categories
        bindData(sivo)

        mGetBarCodeTimer = Timer()
        mGetBarCodeTimer?.schedule(
            timerTask {
                Log.d("zongyang","------------------------>")
            },2000,5000
        )
    }

    fun bindData(entity: SecKillCategoryInfoVO?) {
        val list = entity?.categories
        if (list.isNullOrEmpty()) {
            return
        }
        var lastItem = viewBinding?.categoryViewPager?.currentItem
        currentPosition = lastItem!!
        pageAdapter?.submitData(list)
        viewBinding?.categoryViewPager?.offscreenPageLimit = list.size
        list.forEachWithIndex { i, categoryVO ->
            val tab = viewBinding?.categoryTab?.getTabAt(i) ?: viewBinding?.categoryTab?.newTab()
            tab?.customView = tabInit(categoryVO, i.toString())

            if (currentPosition == i) {
                if (tab != null) {
                    tabIsSelect(tab, true)
                }
            } else {
                if (tab != null) {
                    tabDisSelect(tab)
                }
            }
        }
        //view.categoryViewPager.currentItem = 1
        viewBinding?.categoryViewPager?.currentItem = lastItem

    }

    private fun tabInit(
        item: SecKillCategoryVO,
        position: String
    ): View {
        val view: LayoutTabSecKillItemBinding =
            LayoutTabSecKillItemBinding.inflate(LayoutInflater.from(activity))
        view.tabItemTextview.text = item.categoryName
        return view.root
    }

    private fun tabIsSelect(tab: TabLayout.Tab, isFirstTime: Boolean = false) {
        tab.customView?.let {
            val tabView = LayoutTabSecKillItemBinding.bind(it)
            tabView.tabItemTextview.setTextColor(ResourceUtil.getColor(R.color.white))
            tabView.root.background =
                ResourceUtil.getDrawable(R.drawable.sec_kill_tab_bg)
        }
    }

    private fun tabDisSelect(tab: TabLayout.Tab) {
        tab.customView?.let {
            val tabView = LayoutTabSecKillItemBinding.bind(it)
            tabView.tabItemTextview.setTextColor(ResourceUtil.getColor(R.color.color_666666))
            tabView.root.background =
                ResourceUtil.getDrawable(R.drawable.sec_kill_tab_bg_while)
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityFragmentActionBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

    override fun ifCanGotoTop(can: Boolean) {
    }

    override fun turnToNextPage() {
    }

}
