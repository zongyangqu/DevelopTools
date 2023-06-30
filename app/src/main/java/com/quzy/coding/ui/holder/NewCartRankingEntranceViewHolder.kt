package com.quzy.coding.ui.holder

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.ScreenUtils
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.bean.CartBaseBean
import com.quzy.coding.bean.NewCartRankingBean
import com.quzy.coding.bean.RankListDataBean
import com.quzy.coding.bean.RankListProductBean
import com.quzy.coding.databinding.CartItemNewRankListLayoutBinding
import com.quzy.coding.util.ICartView
import com.quzy.coding.util.LifecycleOperationHelper
import com.quzy.coding.util.extend.singleClick
import com.quzy.coding.util.widget.CartRankProductSwitcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * CreateDate:2023-06-29 21:00
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:新榜单viewhloder
 */
class NewCartRankingEntranceViewHolder (val mICartView: ICartView?,itemView: View) : RecyclerViewHolder(itemView),ICartViewHolder {

    private val viewBinding by lazy {
        CartItemNewRankListLayoutBinding.bind(itemView)
    }
    private var dataBean: RankListDataBean? = null

    init {

        val params = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        params.width = UIUtils.getWindowWidth(itemView.context) / 2
        itemView.layoutParams = params

        itemView.singleClick {
            //Navigation.startSchema(itemView.context, dataBean?.action)
        }

        viewBinding.productSwitcher.onAnimationListener = object :
            CartRankProductSwitcher.OnAnimationListener {
            override fun onInAnimationStart(view: View) {
            }

            override fun onOutAnimationStart(view: View) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstPosition = layoutManager.findFirstVisibleItemPosition()
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                for (position in firstPosition..lastPosition) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(position) as? CartRankSwitchProductViewHolder
                    viewHolder?.playOutAnimation()
                }
            }
        }
    }

    override fun onBindView(itemView: View, mICartView: ICartView) {
    }


    override fun onBindData(
        rankList: CartBaseBean?,
        position: Int
    ) {
        if (rankList is NewCartRankingBean) {
            rankList?.let { setData(it.rankingData,mICartView) }
        }
    }

    private var timingJob: Job? = null

    private val scope by lazy(LazyThreadSafetyMode.NONE) {
        MainScope()
    }

    private val helper by lazy(LazyThreadSafetyMode.NONE) {
        LifecycleOperationHelper()
    }

    private var lifecycleOwner: LifecycleOwner? = null

    companion object {
        private const val PRODUCT_SWITCH_INTERVAL = 4000L
    }

    private fun setData(rankListDataBean: RankListDataBean,mICartView: ICartView?) {
        this.lifecycleOwner = mICartView?.getAty()
        dataBean = rankListDataBean
        Glide.with(itemView.context).load(rankListDataBean?.skus?.get(0)?.leftTopImageUrl).into(viewBinding.ivTopLabel)
        Glide.with(itemView.context).load(rankListDataBean?.skus?.get(0)?.cover?.imageUrl).into(viewBinding.ivTopProduct)
        Glide.with(itemView.context).load(rankListDataBean.bgImgUrl).into(viewBinding.ivBg)
        viewBinding.tvTitle.text = rankListDataBean.title
        viewBinding.productSwitcher.reset()

        var productCount = rankListDataBean?.skus?.size?:0
        val productList = rankListDataBean?.skus?.subList(1,productCount)
        if (productList?.size?:0 >= 6) { //可以翻页
            timingJob?.cancel()
            timingJob = scope.launch {
                var index = 0
                while (true) {
                    helper.doLifecycleOperation(lifecycleOwner) {
                        val startIndex = index % 2 * 3
                        index += 1
                        val dataList = arrayListOf<RankListProductBean>().apply {
                            productList?.let {
                                addAll(productList.subList(startIndex, startIndex + 3))
                            }
                        }
                        viewBinding.productSwitcher.showNext(ArrayList(dataList), 0, 0)
                    }
                    delay(PRODUCT_SWITCH_INTERVAL)
                }
            }
        } else { //不可以翻页
            viewBinding.productSwitcher.showSingleType(ArrayList(productList))
        }
    }

    fun unBindData() {
        timingJob?.cancel()
        timingJob = null
    }


    fun trackExpo() {
       // YHAnalyticsAutoTrackHelper.trackViewOnExpo(itemView)
    }
}
