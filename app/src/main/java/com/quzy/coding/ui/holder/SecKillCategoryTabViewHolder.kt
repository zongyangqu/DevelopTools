package com.quzy.coding.ui.holder

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.quzy.coding.R
import com.quzy.coding.bean.MyData
import com.quzy.coding.bean.SecKillCategoryInfoVO
import com.quzy.coding.bean.SecKillCategoryVO
import com.quzy.coding.bean.SecKillRoundFloors
import com.quzy.coding.databinding.ItemBudgetListBinding
import com.quzy.coding.databinding.ItemSeckillCategoryTabBinding
import com.quzy.coding.databinding.LayoutTabSecKillItemBinding
import com.quzy.coding.ui.adapter.CategoryPageAdapter
import com.quzy.coding.ui.fragment.SecKillCategoryFragment
import com.quzy.coding.util.NestedCeilingHelper
import com.quzy.coding.util.ResourceUtil
import kotlinx.android.synthetic.main.activity_text_sample.view.*
import org.jetbrains.anko.collections.forEachWithIndex

/**
 * CreateDate:2023/2/14 10:53
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class SecKillCategoryTabViewHolder(
    code: String,
    round: Int,
    shopId: String,
    var fragmentManager: FragmentManager,
    private val callback: CategoryHolderCallback?,
    var view: ItemSeckillCategoryTabBinding
) : RecyclerView.ViewHolder(view.root), SecKillCategoryFragment.CategoryCallback {
    private var pageAdapter: CategoryPageAdapter
    var currentPosition = 0
    init {
        pageAdapter = CategoryPageAdapter(code, round, shopId, fragmentManager, this)
        NestedCeilingHelper.setNestedChildContainerTag(itemView)
        view.categoryViewPager.adapter = pageAdapter
        view.categoryTab.setupWithViewPager(view.categoryViewPager)
        view.categoryTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
                tabIsSelect(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tabDisSelect(tab)
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                currentPosition = tab.position
                tabIsSelect(tab)
                val layoutParams = view.categoryViewPager.layoutParams
                layoutParams.height = 0
                view.categoryViewPager.layoutParams = layoutParams
            }
        })
    }

//    val viewBinding by lazy {
//        ItemSeckillCategoryTabBinding.bind(view.root)
//    }

    fun transmitFirstPageData(bean: SecKillRoundFloors?) {
        //pageAdapter.bindFirstPageData(bean)
    }


    fun bindData(entity: SecKillCategoryInfoVO?) {
        val list = entity?.categories
        if (list.isNullOrEmpty()) {
            return
        }
        var lastItem = view.categoryViewPager.currentItem
        currentPosition = lastItem
        pageAdapter.submitData(list)
        view.categoryViewPager.offscreenPageLimit = list.size
        list.forEachWithIndex { i, categoryVO ->
            val tab = view.categoryTab.getTabAt(i) ?: view.categoryTab.newTab()
            tab.customView = tabInit(categoryVO, i.toString())

            if (currentPosition == i) {
                tabIsSelect(tab, true)
            } else {
                tabDisSelect(tab)
            }
        }
        //view.categoryViewPager.currentItem = 1
        view.categoryViewPager.currentItem = lastItem
        view.categoryViewPager.postDelayed({
            val layoutParams = view.categoryViewPager.layoutParams
            layoutParams.height = 0
            view.categoryViewPager.layoutParams = layoutParams
        }, 50)

    }

    /**
     * 移除页面销毁前添加进去的Fragment
     */
    private fun removeAllFragmentIfExist(restoreCouponsTabCount: Int?) {
        restoreCouponsTabCount?.let {
            val transaction = fragmentManager.beginTransaction()
            for (i in 0 until it) {
                findFragment(view.categoryViewPager, i)?.let { fragment ->
                    transaction.remove(fragment)
                }
            }
            transaction.commitNowAllowingStateLoss()
        }
    }
    private fun findFragment(viewPager: ViewPager, position: Int): Fragment? {
        // tag 和 makeFragmentName中设置的tag一致
        val tag = "android:switcher:" + viewPager.id + ":" + position
        return fragmentManager.findFragmentByTag(tag)
    }


    private fun tabInit(
        item: SecKillCategoryVO,
        position: String
    ): View {
        val view: LayoutTabSecKillItemBinding =
            LayoutTabSecKillItemBinding.inflate(LayoutInflater.from(itemView.context))
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

    interface CategoryHolderCallback {
        fun notifyCanGotoTop(can: Boolean)
    }

    override fun ifCanGotoTop(can: Boolean) {
    }

    override fun turnToNextPage() {
    }

}