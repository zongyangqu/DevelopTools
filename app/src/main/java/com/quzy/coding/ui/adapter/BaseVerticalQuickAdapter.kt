package com.quzy.coding.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.quzy.coding.R
import com.quzy.coding.bean.Fruit
import kotlinx.android.synthetic.main.item2.view.*

/**
 * CreateDate:2021/11/5 11:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class BaseVerticalQuickAdapter :BaseQuickAdapter<Fruit,BaseViewHolder>(R.layout.item2) {

    override fun convert(viewHolder: BaseViewHolder, fruit: Fruit?) {
        viewHolder?.itemView.tv_item_lv.text = fruit?.name
        viewHolder?.itemView.iv_item_lv.setImageResource(fruit?.Imid?:0)
    }
}