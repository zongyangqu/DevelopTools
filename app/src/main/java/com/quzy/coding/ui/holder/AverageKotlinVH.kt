package com.quzy.coding.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.bean.Fruit
import com.quzy.coding.databinding.ItemAverageBinding

/**
 * CreateDate:2021/10/9 17:20
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class AverageKotlinVH(view: View) : RecyclerView.ViewHolder(view) {

    val viewBinding by lazy {
        ItemAverageBinding.bind(view)
    }

    fun bindData(fruit: Fruit?) {
        // 设计每个item间距  第一条不设置
        val lp = this.itemView.layoutParams as RecyclerView.LayoutParams
        val marginStart = if (position == 0) 0f.dpOfInt else 3f.dpOfInt
        lp.marginStart = marginStart

        viewBinding.tvItemLv.text = fruit?.name
        viewBinding.ivItemLv.setImageResource(fruit?.Imid ?: 0)
    }
}
