package com.quzy.coding.ui.holder

import android.view.View
import com.quzy.coding.bean.CartBaseBean
import com.quzy.coding.util.ICartView

/**
 * CreateDate:2023-06-30 15:40
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
interface ICartViewHolder {

    fun onBindView(itemView: View, mICartView: ICartView)

    fun onBindData(cartBaseBean: CartBaseBean?, position: Int)
}