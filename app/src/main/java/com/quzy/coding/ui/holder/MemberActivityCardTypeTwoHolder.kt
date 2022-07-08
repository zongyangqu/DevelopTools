package com.quzy.coding.ui.holder

import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.quzy.coding.bean.ItemActivityCardBean
import com.quzy.coding.databinding.ViewContentMemberActivityCardTypeTwoBinding
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity

/**
 * CreateDate:2021/10/28 20:08
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
open class MemberActivityCardTypeTwoHolder(itemView: View) : MemberActivityCardBaseHolder(itemView) {

    protected val viewBinding by lazy {
        ViewContentMemberActivityCardTypeTwoBinding.bind(itemView)
    }
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null

    fun bindData(cardBean: ItemActivityCardBean?,fragment: RecyclerViewWaterfallComplexKotActivity?) {
        this.fragment = fragment
        fragment?.let { Glide.with(it).load(cardBean?.bgImg ?: "").into(viewBinding.ivBg) }

        //头部信息
        Glide.with(itemView.context).load(cardBean?.headInfo?.iconImg ?: "").into(viewBinding.ivHeadIcon)
        viewBinding.tvHeadTitle.text = cardBean?.headInfo?.firstTitle?.activitytext
        setTitleInfo(cardBean?.headInfo?.firstTitle, viewBinding.tvHeadTitle)

        //中间部分
        viewBinding.tvContentTitle.text = cardBean?.contentInfo?.firstTitle?.activitytext
        fragment?.let { Glide.with(it).load(cardBean?.contentInfo?.bgImg ?: "").into(viewBinding.ivContentBg) }
        setTitleInfo(cardBean?.contentInfo?.firstTitle, viewBinding.tvContentTitle)
        setTitleInfo(cardBean?.contentInfo?.secondTitle, viewBinding.tvContentSubtitle)
        fragment?.let { Glide.with(it).load(cardBean?.contentInfo?.button?.bgImg ?: "").into(viewBinding.ivContentButton) }
        setImage(cardBean?.contentInfo?.contentImg, viewBinding.ivContentIcon)

        //contentInfo中jumpUrl不为空时，再添加点击事件
        if(!TextUtils.isEmpty(cardBean?.contentInfo?.jumpUrl)) {
            viewBinding.rclContentTypeTwo.setOnClickListener {
                handleClick(cardBean?.contentInfo?.jumpUrl, "")
            }
        }

        if(!TextUtils.isEmpty(cardBean?.contentInfo?.button?.jumpUrl)) {
            viewBinding.ivContentButton.setOnClickListener {
                handleClick(cardBean?.contentInfo?.button?.jumpUrl, "")
            }
        }

    }


}
