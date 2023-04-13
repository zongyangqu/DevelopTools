package com.quzy.coding.ui.fragment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.quzy.coding.R
import com.quzy.coding.base.fragment.BaseYHFragment
import com.quzy.coding.bean.MultipleType
import com.quzy.coding.bean.SecKillRoundFloors
import com.quzy.coding.databinding.FragmentSeckillActivitiesNewBinding
import com.quzy.coding.ui.adapter.SecKillCategoryAdapter

/**
 * CreateDate:2023/2/14 11:40
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.fragment
 * @Description:
 */
class SecKillCategoryFragment :
    BaseYHFragment() {

    private lateinit var adapter: SecKillCategoryAdapter
    private lateinit var binding: FragmentSeckillActivitiesNewBinding
    private var isFirstResume = true
    private var shopId: String = ""
    private var categoryId: String? = null
    private var activityCode: String = ""
    private var roundCode: Int = 0
    private var hasNextCategory: Boolean = false
    private var categoryName: String ?= null
    private var position: String ?= null
    private var callback: CategoryCallback? = null
    fun initParams(
        code: String,
        categoryId: String?,
        round: Int,
        hasNextCategory: Boolean,
        shopId: String,
        categoryName: String?,
        position: String?,
        callback: CategoryCallback
    ) {
        this.activityCode = code
        this.categoryId = categoryId
        this.roundCode = round
        this.shopId = shopId
        this.hasNextCategory = hasNextCategory
        this.categoryName = categoryName
        this.position = position
        this.callback = callback
    }

    private var firstPageProducts: SecKillRoundFloors? = null

    fun bindFirstPageProduct(bean: SecKillRoundFloors?) {
        if (bean == null || bean.floors.isEmpty()) {
            return
        }
        val list = mutableListOf<MultipleType>()
        list.addAll(bean.floors)
        //firstPageProducts = SecKillRoundFloors(list, bean.isRefresh, bean.canLoadMore)
    }

    override fun getContentResource(): Int = R.layout.fragment_seckill_activities_new

    override fun initContentView(layoutView: View) {
        initView(layoutView)
    }

    private fun initView(view: View) {
        binding = FragmentSeckillActivitiesNewBinding.bind(view)

        adapter = SecKillCategoryAdapter(
            activityCode,
            roundCode,
            categoryName,
            position,
            viewLifecycleOwner,
            childFragmentManager
        )
        initData()
    }

    private fun initData() {
        //adapter.add(it.floors)

    }

    private fun refresh() {
    }

    private fun finishLoadMoreWithNoMoreData() {
    }



    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            refresh()
        } else {
            adapter.notifyAllCartNum()
        }
        isFirstResume = false
    }

    override fun putPageParam(key: String?, value: String?) {
    }

    override fun showLoadingView(show: Boolean) {
    }

    interface CategoryCallback {
        fun ifCanGotoTop(can: Boolean)
        fun turnToNextPage()
    }
}
