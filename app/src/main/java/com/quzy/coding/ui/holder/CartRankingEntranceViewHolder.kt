package com.quzy.coding.ui.holder

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.bean.CartBaseBean
import com.quzy.coding.bean.CartRankingBean
import com.quzy.coding.bean.RankListDataBean
import com.quzy.coding.databinding.CartItemRankListLayoutBinding
import com.quzy.coding.ui.adapter.CartRankingListProductAdapter
import com.quzy.coding.util.ICartView
import com.quzy.coding.util.ResourceUtil
import com.quzy.coding.util.extend.singleClick
import com.quzy.coding.util.widget.CartRankProductSwitcher

/**
 * CreateDate:2023-06-30 15:47
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description: 老榜单
 */
class CartRankingEntranceViewHolder (val mICartView: ICartView?, itemView: View) : RecyclerViewHolder(itemView),ICartViewHolder {
    private val viewBinding by lazy {
        CartItemRankListLayoutBinding.bind(itemView)
    }
    private var dataBean: RankListDataBean? = null
    init {
        val params = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        params.width = UIUtils.getWindowWidth(itemView.context) / 2
        itemView.layoutParams = params
        itemView.singleClick {
            //Navigation.startSchema(itemView.context, dataBean?.action)
        }
    }

    override fun onBindView(itemView: View, mICartView: ICartView) {
    }

    override fun onBindData(cartBaseBean: CartBaseBean?, position: Int) {
        if (cartBaseBean is CartRankingBean) {
            setData(cartBaseBean.rankingData)
        }
    }

    private fun setData(rankListDataBean: RankListDataBean) {
        dataBean = rankListDataBean
        Glide.with(itemView.context).load(rankListDataBean.bgImgUrl).into(viewBinding.ivBg).apply { RequestOptions.errorOf(R.mipmap.icon_common_placeholder) }
        viewBinding.tvTitle.text = rankListDataBean.title
        viewBinding.tvSubTitle.text = rankListDataBean.subTitle
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(itemView.context)
        viewBinding.recyclerView.setBackgroundColor(ResourceUtil.getColor(R.color.white))
        val adapter = CartRankingListProductAdapter(mICartView)
        val totalRankList = rankListDataBean.skus
        if (totalRankList.isNullOrEmpty()) {
            viewBinding.recyclerView.visibility = View.GONE
        } else {
            viewBinding.recyclerView.visibility = View.VISIBLE
            adapter.dataList.addAll(totalRankList)
        }
        viewBinding.recyclerView.adapter = adapter
    }

}