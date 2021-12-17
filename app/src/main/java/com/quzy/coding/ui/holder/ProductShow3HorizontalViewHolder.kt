package com.quzy.coding.ui.holder

import android.view.View
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.Ware
import com.quzy.coding.databinding.ProductShowAdapter3HorizontalBinding
import com.quzy.coding.util.ISearchResult

/**
 * CreateDate:2021/12/17 11:04
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class ProductShow3HorizontalViewHolder (itemView: View, mISearchResult : ISearchResult) : RecyclerViewHolder(itemView) {

//    init {
//        initType()
//    }

    val viewBinding by lazy {
        ProductShowAdapter3HorizontalBinding.bind(itemView)
    }


    fun bindData(ware: Ware) {
        viewBinding.hotGoodsDescription.text = ware.name
        viewBinding.hotGoodsPrice.text = ware.price
        Glide.with(BaseApplication.getContext()).load(ware.imgUrl).into(viewBinding.hotGoodsImg)
    }

    /*fun initType(){
        val params = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        params.isFullSpan = ChangeRecyclerViewModeKotActivity.currentType == 1
        Log.d("zpngyang","AssortShowViewHolder1_isFullSpan")
    }*/
}