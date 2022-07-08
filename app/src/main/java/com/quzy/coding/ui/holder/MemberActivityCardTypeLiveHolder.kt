package com.quzy.coding.ui.holder

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import cn.yonghui.hyd.member.wigets.memberloop.CommonLoopView
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.widget.roundview.RoundConstraintLayout
import com.quzy.coding.R
import com.quzy.coding.bean.ItemActivityCardBean
import com.quzy.coding.bean.LiveProductBean
import com.quzy.coding.databinding.ViewContentMemberActivityCardTypeLiveBinding
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import com.quzy.coding.ui.adapter.MemberActivityCardLiveAdapter
import com.quzy.coding.util.widget.IconFont

/**
 * CreateDate:2021/10/28 21:19
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
open class MemberActivityCardTypeLiveHolder(var lifecycle: Lifecycle?, itemView: View) :
        MemberActivityCardBaseHolder(itemView) {

    var adapter: MemberActivityCardLiveAdapter? = null
    var itemActivityCardBean: ItemActivityCardBean? = null
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null

    protected val viewBinding by lazy {
        ViewContentMemberActivityCardTypeLiveBinding.bind(itemView)
    }


    fun bindData(cardBean: ItemActivityCardBean?,fragment: RecyclerViewWaterfallComplexKotActivity?) {
        this.fragment = fragment
        if(cardBean?.isRefresh == true){
            cardBean?.isRefresh = false
            viewBinding.liveViewpager.apply {

                setAutoPlayInterval(5000)
                lifecycle?.let { life -> setLifecycleRegistry(life) }
                cardBean?.contentInfo?.liveRooms?.let { setData(layoutId = R.layout.member_item_card_live, models = it) }
                setOnBannerItemClickListener(object : CommonLoopView.OnBannerItemClickListener<LiveProductBean> {
                    override fun onBannerItemClick(banner: CommonLoopView, model: LiveProductBean, position: Int) {
                    }
                })
                setAdapter(object : CommonLoopView.Adapter<LiveProductBean> {
                    override fun fillBannerItem(
                            banner: CommonLoopView,
                            view: View,
                            model: LiveProductBean,
                            position: Int
                    ) {
                        var tv_card_live_title: TextView = view.findViewById(R.id.tv_card_live_title)
                        var tv_card_live_status: TextView = view.findViewById(R.id.tv_card_live_status)
                        var tv_card_live_enter: TextView = view.findViewById(R.id.tv_card_live_enter)
                        var tv_card_live_desc: TextView = view.findViewById(R.id.tv_card_live_desc)
                        var live_view_status: RoundConstraintLayout = view.findViewById(R.id.live_view_status)
                        var iv_live_tips: ImageView = view.findViewById(R.id.iv_live_tips)
                        var live_entry_view: LinearLayout = view.findViewById(R.id.live_entry_view)
                        var icon_live_arrow_next: IconFont = view.findViewById(R.id.icon_live_arrow_next)
                        tv_card_live_title.text = model?.firstTitle
                        tv_card_live_desc.text = model?.secondTitle
                        tv_card_live_status.text = model?.liveStatusDesc
                        tv_card_live_enter.text = model?.desc
                        when(model?.liveStatus){
                            MemberActivityCardLiveAdapter.LIVEING ->{
                                fragment?.let { Glide.with(it).load(model?.liveIcon).into(iv_live_tips) }
                                live_view_status.visibility = View.VISIBLE
                                icon_live_arrow_next.visibility = View.VISIBLE
                            }
                            MemberActivityCardLiveAdapter.LIVE_NOTICE ->{
                                live_view_status.visibility = View.GONE
                                icon_live_arrow_next.visibility = View.GONE
                            }
                            MemberActivityCardLiveAdapter.LIVE_BACKUP ->{
                                live_view_status.visibility = View.GONE
                                icon_live_arrow_next.visibility = View.VISIBLE
                            }
                        }

                        live_entry_view.setOnClickListener {
                        }
                    }
                })
            }

            viewBinding.goodsPruView.removeAllViews()
            cardBean?.bottomInfo?.recommend?.forEach {
                var pruView = LayoutInflater.from(itemView.context)
                        .inflate(
                                R.layout.item_good_pru_recommend,
                                viewBinding.goodsPruView,
                                false
                        )
                var tv_recommend_des = pruView.findViewById<TextView>(R.id.tv_recommend_des)
                var iv_recomend_icon = pruView.findViewById<ImageView>(R.id.iv_recomend_icon)
                tv_recommend_des.text = it.text
                fragment?.let { it1 -> Glide.with(it1).load(it.icon).into(iv_recomend_icon) }
                viewBinding.goodsPruView.addView(pruView)
            }
            fragment?.let {
                Glide.with(it).load(cardBean?.contentInfo?.bgImg ?: "").into(viewBinding.ivContentBg)
                Glide.with(fragment).load(cardBean?.bottomInfo?.bgImg ?: "").into(viewBinding.ivRecommendBg)
                Glide.with(fragment).load(cardBean?.headInfo?.iconImg ?: "").into(viewBinding.ivHeadIcon)
            }
            viewBinding.tvHeadTitle.text = cardBean?.headInfo?.firstTitle?.activitytext
            viewBinding.tvRecommend.text = cardBean?.bottomInfo?.title

            viewBinding.rclContentTypeRecommend.setOnClickListener {
            }

        }

    }


}
