package com.quzy.coding.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.bean.Fruit
import com.quzy.coding.databinding.Item2Binding

/**
 * CreateDate:2021/10/9 17:20
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class RecycVerticalKotlinVH(view: View) : RecyclerView.ViewHolder(view) {

    val viewBinding by lazy {
        Item2Binding.bind(view)
    }

    fun bindData(fruit: Fruit?) {
        viewBinding.tvItemLv.text = fruit?.name
        viewBinding.ivItemLv.setImageResource(fruit?.Imid ?: 0)
    }
}
