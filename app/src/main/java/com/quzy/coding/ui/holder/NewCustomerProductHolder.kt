package com.quzy.coding.ui.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.bean.NewCustomerProductModel
import com.quzy.coding.ui.adapter.NewCustomerProductAdapter
import kotlinx.android.synthetic.main.common_coupon_product_layout.view.*

/**
 * CreateDate:2022/6/23 16:47
 * @author: zongyang qu
 * @Package： cn.yonghui.hyd.lib.style.newcustomer
 * @Description: 新人弹窗 商品部分   870需求
 */
class NewCustomerProductHolder(
    var layout: View,
    var context: Context
) : RecyclerView.ViewHolder(layout) {

    private var adapter: NewCustomerProductAdapter? = null
    fun bindData(
        mGoods: MutableList<NewCustomerProductModel>?
    ) {
        setGridLayoutManager(mGoods)
        mGoods?.let {
            if (!mGoods.isNullOrEmpty()) {
                if (adapter == null) {
                    adapter = NewCustomerProductAdapter()
                    setServiceAdapter(adapter)
                }
                adapter?.setData(
                    itemView.context,
                    mGoods
                )
            }
        }
    }

    private fun setGridLayoutManager(info: List<NewCustomerProductModel>?) {
        val totalSize = info?.size ?: 0
        if (totalSize >= 2) {
            val manager = GridLayoutManager(itemView.context, totalSize)
            itemView.product_gridview.layoutManager = manager
        } else {
            val manager = GridLayoutManager(itemView.context, 1)
            itemView.product_gridview.layoutManager = manager
        }
    }

    fun setServiceAdapter(adapter: NewCustomerProductAdapter?) {
        itemView.product_gridview.adapter = adapter
    }
}
