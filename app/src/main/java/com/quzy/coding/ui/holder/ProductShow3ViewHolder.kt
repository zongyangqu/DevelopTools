package com.quzy.coding.ui.holder

import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.Ware
import com.quzy.coding.databinding.ProductShowAdapter3Binding
import com.quzy.coding.ui.activity.ChangeRecyclerViewModeKotActivity
import com.quzy.coding.util.ISearchResult

/**
 * CreateDate:2021/12/17 11:04
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class ProductShow3ViewHolder (itemView: View, mISearchResult : ISearchResult) : RecyclerViewHolder(itemView) {

    /*init {
        if(mISearchResult.getCurrentViewType() == ChangeRecyclerViewModeKotActivity.SHOW_TYPE_GRID){
            val params = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
            params.isFullSpan = true
        }
    }*/

    val viewBinding by lazy {
        ProductShowAdapter3Binding.bind(itemView)
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