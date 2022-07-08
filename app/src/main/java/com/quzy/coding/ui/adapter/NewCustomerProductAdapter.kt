package com.quzy.coding.ui.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.quzy.coding.R
import com.quzy.coding.bean.NewCustomerProductModel
import com.quzy.coding.util.gone
import com.quzy.coding.util.show
import kotlinx.android.synthetic.main.common_coupon_product_item_layout.view.*

/**
 * CreateDate:2022/6/23 17:49
 * @author: zongyang qu
 * @Package： cn.yonghui.hyd.lib.style.newcustomer
 * @Description:
 */
class NewCustomerProductAdapter() : BaseQuickAdapter<NewCustomerProductModel, BaseViewHolder>(
    R.layout.common_coupon_product_item_layout
) {
    var ctx: Context ? = null
    fun setData(ctx: Context, mData: MutableList<NewCustomerProductModel>) {
        this.ctx = ctx
        this.data = mData
    }

    override fun convert(holder: BaseViewHolder, item: NewCustomerProductModel) {
        holder?.itemView?.tv_newcustomer_title?.visibility = if(item?.priceText.isNullOrEmpty()) View.GONE else View.VISIBLE
        holder?.itemView?.tv_newcustomer_title?.text = item?.priceText ?: ""
        item?.imgUrl?.let {
            Glide.with(holder.itemView.context).load(item.imgUrl).into(holder.itemView.product_newcustomer_img)
        }
        if (item?.inStock == 0) {
            holder?.itemView?.tv_sell_out?.show()
        } else {
            holder?.itemView?.tv_sell_out?.gone()
        }
    }
}
