package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.ItemActivityCardBean
import com.quzy.coding.bean.model.MemberType
import com.quzy.coding.bean.model.MemberTypeWithData
import com.quzy.coding.databinding.ActivityRecyclerviewBinding
import com.quzy.coding.ui.adapter.WaterfallComplexKotAdapter
import com.quzy.coding.util.JsonUtils

/**
 * CreateDate:2021/10/19 14:50
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: Kotlin-多条目瀑布流的RecyclerView
 */
class RecyclerViewWaterfallComplexKotActivity : BaseActivity() {

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
            newMemberAdapter?.topSize = memberDatas?.size ?: 0
            // 这里把notifyItemRangeInserted 改成了notifyDataSetChanged，是解决 由活动卡片数量是4个的门店切到活动卡片是2个的门店时，2个卡片的瀑布流排成了2行的问题
            newMemberAdapter?.notifyDataSetChanged()
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
}
