package com.quzy.coding.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.base.recyclerview.adapter.BaseRecyclerViewAdapter
import com.quzy.coding.R
import com.quzy.coding.bean.CouponMineDataBean
import com.quzy.coding.bean.NewCustomerProductModel
import com.quzy.coding.ui.holder.NewCustomerCouponHolder
import com.quzy.coding.ui.holder.NewCustomerProductHolder
import com.quzy.coding.util.singleClick

/**
 * CreateDate:2022/7/1 10:26
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class NewCustomerCouponUnitaryListAdapter(
    var mContext: Context?,
    var mData: List<CouponMineDataBean>?,
    var newPersonSkus: List<NewCustomerProductModel>?
) : BaseRecyclerViewAdapter<RecyclerView.ViewHolder>() {
    private val type_coupon = 1
    private val type_goods = 0

    override fun getViewHolder(itemView: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            type_coupon -> {
                NewCustomerCouponHolder(itemView, itemView.context)
            }
            type_goods -> {
                NewCustomerProductHolder(itemView, itemView.context)
            }
            else -> NewCustomerCouponHolder(itemView, itemView.context)
        }
    }

    override fun getViewHolder(itemView: View): NewCustomerCouponHolder = NewCustomerCouponHolder(itemView, mContext!!)

    override fun getItemView(viewType: Int, parent: ViewGroup?): View {
        return when (viewType) {
            type_coupon -> {
                LayoutInflater.from(mContext).inflate(R.layout.newcustomter_coupon_item_layout, parent, false)
            }
            type_goods -> {
                LayoutInflater.from(mContext).inflate(R.layout.common_coupon_product_layout, parent, false)
            }
            else -> LayoutInflater.from(mContext).inflate(R.layout.newcustomter_coupon_item_layout, parent, false)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewCustomerCouponHolder -> {
                val data = mData?.get(getRealPosition(position))
                if (mData != null) {
                    holder.bindData(data)
                    if (position == itemCount - 1) {
                        holder.setBottomMargin(0f)
                    } else {
                        holder.setBottomMargin(6f)
                    }
                }
            }
            is NewCustomerProductHolder -> {
                holder.bindData(newPersonSkus?.toMutableList())
            }
        }
        holder?.itemView.singleClick {
        }
    }
    override fun getItemCount(): Int {
        if (newPersonSkus.isNullOrEmpty()) {
            return mData?.size ?: 0
        } else {
            return mData?.size?.plus(1) ?: 0
        }
    }

    fun getRealPosition(position: Int): Int {
        return if (newPersonSkus.isNullOrEmpty()) {
            position
        } else {
            position - 1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && !newPersonSkus.isNullOrEmpty()) {
            type_goods
        } else {
            type_coupon
        }
    }
}
