package com.quzy.coding.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.base.ViewHolderTag
import com.quzy.coding.bean.CommonProductBean
import com.quzy.coding.bean.SecKillProductBean
import com.quzy.coding.databinding.ItemSecKillProductCommonBinding

/**
 * CreateDate:2023/2/14 13:52
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class SecKillProductViewHolder (
    private val code: String,
    private val round: Int,
    private val viewBinding: ItemSecKillProductCommonBinding,
    private val categoryName: String? = null,
    private val position: String? = null
) : RecyclerView.ViewHolder(viewBinding.root),
    ViewHolderTag<SecKillProductBean> {

    private var secKillProduct: SecKillProductBean? = null

    override fun setHolder(entity: SecKillProductBean) {
        this.secKillProduct = entity
        bindProductData(entity)
    }

    override fun setHolder(entity: SecKillProductBean, payloads: MutableList<Any>) {
        TODO("Not yet implemented")
    }

    fun bindProductData(productBean: CommonProductBean) {

    }

}