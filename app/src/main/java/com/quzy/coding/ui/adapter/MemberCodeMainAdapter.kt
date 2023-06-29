package com.quzy.coding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.ui.holder.BannerViewHolder

/**
 * CreateDate:2023/6/17 15:38
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class MemberCodeMainAdapter (
    context: FragmentActivity? = null,
    fragment: Fragment? = null,
    var lifecycleOwner: LifecycleOwner? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var stickItemPosition = 4
    var hasCard = false
    companion object {
        const val VIEW_BUY_CARD = 0
        const val VIEW_TOOLS_MENU = 1
        const val VIEW_STORE_TITLE = 2
        const val VIEW_NEW_MEMBER = 3
        const val VIEW_STORE_BANNER = 4
        const val VIEW_STORE_BANNER_1V1 = 5
        const val VIEW_STORE_PROMOTION = 6
        const val VIEW_COUPONS = 7
        const val VIEW_RECOMMEND_PRODUCT = 8
        const val VIEW_PRE_KIND_COUPON = 9
        const val VIEW_MEMBER_PRICE_PRODUCT = 10
        const val VIEW_PAY_TYPE_CMB = 11
        const val VIEW_PAY_TYPE_CARD = 12
        const val VIEW_PAY_TYPE_BALANCE = 13
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.member_code_section_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}