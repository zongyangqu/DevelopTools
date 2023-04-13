package com.quzy.coding.ui.adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.quzy.coding.bean.SecKillCategoryVO
import com.quzy.coding.bean.SecKillRoundFloors
import com.quzy.coding.ui.fragment.SecKillCategoryFragment

/**
 * CreateDate:2023/2/14 11:05
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class CategoryPageAdapter(
    private val code: String,
    private val round: Int,
    private val shopId: String,
    fragmentManager: FragmentManager,
    private val callback: SecKillCategoryFragment.CategoryCallback) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var firstPageProducts: SecKillRoundFloors? = null
    private val data: MutableList<SecKillCategoryVO> = mutableListOf()

    override fun getCount(): Int {
        return data.size
    }


    override fun getItem(position: Int): Fragment {
        val fragment = SecKillCategoryFragment()
        val categoryId = data.getOrNull(position)?.categoryId
        val categoryName = data.getOrNull(position)?.categoryName
        fragment.initParams(
            code,
            categoryId,
            round,
            position < count - 1,
            shopId,
            categoryName,
            position.toString(),
            callback
        )
        if (categoryId.isNullOrEmpty()) {
            fragment.bindFirstPageProduct(firstPageProducts)
        }
        return fragment
    }

    fun submitData(list: List<SecKillCategoryVO>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

}