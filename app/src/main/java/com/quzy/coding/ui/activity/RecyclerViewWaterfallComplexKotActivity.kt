package com.quzy.coding.ui.activity

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.CartRankingBean
import com.quzy.coding.bean.ItemActivityCardBean
import com.quzy.coding.bean.NewCartRankingBean
import com.quzy.coding.bean.ProductBlock
import com.quzy.coding.bean.WaterFullGuessYouLikeBean
import com.quzy.coding.bean.model.CartItemType
import com.quzy.coding.bean.model.MemberType
import com.quzy.coding.bean.model.MemberTypeWithData
import com.quzy.coding.databinding.ActivityRecyclerviewBinding
import com.quzy.coding.ui.adapter.WaterfallComplexKotAdapter
import com.quzy.coding.util.ICartView
import com.quzy.coding.util.JsonUtils

/**
 * CreateDate:2021/10/19 14:50
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: Kotlin-多条目瀑布流的RecyclerView
 */
class RecyclerViewWaterfallComplexKotActivity : BaseActivity(),ICartView {

    var newMemberLayoutManager: StaggeredGridLayoutManager? = null
    var activityRecyclerviewBinding: ActivityRecyclerviewBinding? = null
    var newMemberAdapter: WaterfallComplexKotAdapter? = null
    var assetInfo: AssetInfo? = null
    var memberDatas: ArrayList<MemberTypeWithData>? = null
    private var activityCardDatas: ArrayList<ItemActivityCardBean>? = null // 天天逛 享优惠 活动卡片datas

    override fun onViewCreated() {
        assetInfo = JsonUtils.analysisAssetInfoJsonFile(this, "aeestinfo")
        newMemberLayoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        newMemberAdapter = WaterfallComplexKotAdapter()
        newMemberAdapter?.fragment = this
        activityRecyclerviewBinding?.recyclerView?.layoutManager = newMemberLayoutManager
        activityRecyclerviewBinding?.recyclerView?.adapter = newMemberAdapter
        activityRecyclerviewBinding?.recyclerView?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                val params = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
                val spanPosition = params.spanIndex
                val fullSpan = params.isFullSpan
                if (!fullSpan) {
                    // 给不是一整行的item(天天逛享优惠活动卡片和猜你喜欢商品)添加左右边距
                    if (spanPosition == 0) {
                        outRect.left = 12f.dpOfInt
                        outRect.right = 4.5f.dpOfInt
                    } else {
                        outRect.left = 4.5f.dpOfInt
                        outRect.right = 12f.dpOfInt
                    }
                } else {
                    outRect.left = 0
                    outRect.right = 0
                }
            }
        })
        memberDatas = ArrayList()
        // 模拟兜底数据展示
//        memberDatas?.add(MemberTypeWithData(MemberType.USERHEADER))
//        memberDatas?.add(MemberTypeWithData(MemberType.DEFAULT_CUSTOMER_PROPERTIES))
//        memberDatas?.add(MemberTypeWithData(MemberType.ORDERFORM))
        newMemberAdapter?.datas = memberDatas
        assetInfo?.let {
            newMemberAdapter?.notifyDataSetChanged()
            memberDatas?.add(MemberTypeWithData(MemberType.USERHEADER))
            memberDatas?.add(MemberTypeWithData(MemberType.ORDERFORM))
            if (it.payConfigInfos?.size ?: 0 > 0) {
                memberDatas?.add(MemberTypeWithData(MemberType.YHJR))
            }
            if (it.doubleElevenPromotions?.size ?: 0 > 0) {
                memberDatas?.add(MemberTypeWithData(MemberType.DOUBLE_ELEVEN_PROMOTION))
            }
            newMemberAdapter?.assetInfo = it
            if (it.shopDiscountsVo != null) {
                memberDatas?.add(MemberTypeWithData(MemberType.STOREMSG))
            }
            if (!it.cmsPagePit.isNullOrEmpty()) {
                memberDatas?.add(MemberTypeWithData(MemberType.GALLERYAD))
            }
            if (it.promotionsAndFunctions?.size ?: 0 > 0) {
                memberDatas?.add(MemberTypeWithData(MemberType.SERVICEHELP))
            }
            // 添加 天天逛 享优惠 活动卡片信息
            addActivityCardDatas(it)
            addProductCardDatas(it.results)
            newMemberAdapter?.topSize = memberDatas?.size ?: 0
            // 这里把notifyItemRangeInserted 改成了notifyDataSetChanged，是解决 由活动卡片数量是4个的门店切到活动卡片是2个的门店时，2个卡片的瀑布流排成了2行的问题
            newMemberAdapter?.notifyDataSetChanged()
        }
    }

    private fun addProductCardDatas(productBlocks: ArrayList<ProductBlock>) {
        productBlocks?.forEachIndexed { index, productBlock ->
            when(productBlock.blockType) {
                1 -> {
                    val favItemCartBean = WaterFullGuessYouLikeBean(
                        CartItemType.ITEM_VIEW_TYPE_WATER_FULL_GUESS_YOU_LIKE,
                        productBlock.skuBlock
                    )
                    memberDatas?.add(MemberTypeWithData(MemberType.ITEM_VIEW_TYPE_WATER_FULL_GUESS_YOU_LIKE, favItemCartBean))
                }
                5 -> {
                    productBlock.rankList?.let {
                        val skus = it.skus
                        if (!skus.isNullOrEmpty()) {
                            if (skus.size == 3) {
                                memberDatas?.add(MemberTypeWithData(MemberType.ITEM_VIEW_TYPE_CART_RANKING_ENTRANCE, CartRankingBean(it)))
                            } else if (skus.size > 3) {
                                // 如果返回的数量大于三个，则客户端截取前三个
                                val newSkus = skus.subList(0, 3)
                                it.skus = ArrayList(newSkus)
                                memberDatas?.add(MemberTypeWithData(MemberType.ITEM_VIEW_TYPE_CART_RANKING_ENTRANCE, CartRankingBean(it)))
                            }
                        }
                    }
                }
                13 -> {
                    productBlock.rankList?.let {
                        val skus = it.skus
                        if (!skus.isNullOrEmpty() && skus?.size?:0 > 3) {
                            if (skus.size < 7){
                                // 如果返回的数量小于7个，则只展示前四个
                                val newSkus = skus.subList(0, 4)
                                it.skus = ArrayList(newSkus)
                                memberDatas?.add(MemberTypeWithData(MemberType.ITEM_VIEW_TYPE_CART_RANKING_ENTRANCE_NEW, NewCartRankingBean(it)))
                            } else {
                                memberDatas?.add(MemberTypeWithData(MemberType.ITEM_VIEW_TYPE_CART_RANKING_ENTRANCE_NEW, NewCartRankingBean(it)))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addActivityCardDatas(it: AssetInfo) {
        activityCardDatas = it.activityArea?.activities
        if (it.activityArea?.activities != null && it.activityArea?.activities?.size ?: 0 > 0) {
            memberDatas?.add(
                MemberTypeWithData(
                    MemberType.ACTIVITY_CARD_TITLE,
                    it.activityArea.title
                )
            )
            it.activityArea.welfareRemind?.apply {
                memberDatas?.add(
                    MemberTypeWithData(
                        MemberType.ACTIVITY_CARD_INNER_TITLE,
                        it.activityArea.welfareRemind
                    )
                )
            }
            for (i in 0 until it.activityArea?.activities!!.size) {
                if (it.activityArea?.activities!![i] == null) {
                    continue
                }
                var itemActivityCard = it.activityArea?.activities!![i]
                when (itemActivityCard.type) {
                    1 -> memberDatas?.add(
                        MemberTypeWithData(
                            MemberType.ACTIVITY_CARD_TYPE_ONE,
                            itemActivityCard
                        )
                    )
                    2 -> memberDatas?.add(
                        MemberTypeWithData(
                            MemberType.ACTIVITY_CARD_TYPE_TWO,
                            itemActivityCard
                        )
                    )
                    3 -> memberDatas?.add(
                        MemberTypeWithData(
                            MemberType.ACTIVITY_CARD_TYPE_THREE,
                            itemActivityCard
                        )
                    )
                    4 -> {
                        itemActivityCard?.isRefresh = true
                        memberDatas?.add(
                            MemberTypeWithData(
                                MemberType.ACTIVITY_CARD_TYPE_LIVE,
                                itemActivityCard
                            )
                        )
                    }
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        activityRecyclerviewBinding = ActivityRecyclerviewBinding.inflate(LayoutInflater.from(this))
        activityRecyclerviewBinding?.root?.let {
            return it.rootView
        }
        return null
    }

    override fun getAty(): FragmentActivity? {
        return this
    }


    override fun notifyDataSetChanged() {
    }

    override fun notifyItemChanged(position: Int) {
    }
}
