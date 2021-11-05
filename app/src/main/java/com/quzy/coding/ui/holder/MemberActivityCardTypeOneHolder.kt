package com.quzy.coding.ui.holder

import android.text.TextUtils
import android.view.View
import com.bumptech.glide.Glide
import com.quzy.coding.bean.ItemActivityCardBean
import com.quzy.coding.databinding.ViewContentMemberActivityCardTypeOneBinding
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity

/**
 * CreateDate:2021/10/28 16:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
open class MemberActivityCardTypeOneHolder(itemView: View) : MemberActivityCardBaseHolder(itemView) {
    companion object {
        const val SIGN_IN = "signIn"
    }
    protected val viewBinding by lazy {
        ViewContentMemberActivityCardTypeOneBinding.bind(itemView)
    }

    var fragment: RecyclerViewWaterfallComplexKotActivity? = null

    fun bindData(cardBean: ItemActivityCardBean?,fragment: RecyclerViewWaterfallComplexKotActivity?){
        fragment?.let { Glide.with(it).load(cardBean?.bgImg ?: "").into(viewBinding.ivBg) }
        //头部信息
        fragment?.let { Glide.with(it).load(cardBean?.headInfo?.iconImg ?: "").into(viewBinding.ivHeadIcon) }
        setTitleInfo(cardBean?.headInfo?.firstTitle, viewBinding.tvHeadTitle)
        // 小红点
        if ((SIGN_IN == cardBean?.key && cardBean?.headInfo?.redSpot == 1)) {
            viewBinding.viRedDot.visibility = View.VISIBLE
        } else {
            viewBinding.viRedDot.visibility = View.GONE
        }

        //左下角部分
        viewBinding.tvBottomLeftText.text = cardBean?.bottomLeftInfo?.title


        fragment?.let { Glide.with(it).load(cardBean?.bottomLeftInfo?.bgImg ?: "").into(viewBinding.ivBottomLeftIcon) }
        setBubbleTitle(viewBinding.tvBottomLeftTag, cardBean?.bottomLeftInfo?.bubbleTitle)

        //右下角部分
        viewBinding.tvBottomRightText.text = cardBean?.bottomRightInfo?.title

        fragment?.let { Glide.with(it).load(cardBean?.bottomRightInfo?.bgImg ?: "").into(viewBinding.ivBottomRightIcon) }
        setBubbleTitle(viewBinding.tvBottomRightTag, cardBean?.bottomRightInfo?.bubbleTitle)

        //中间部分
        fragment?.let { Glide.with(it).load(cardBean?.contentInfo?.button?.bgImg).into(viewBinding.ivContentButton) }
        fragment?.let { Glide.with(it).load(cardBean?.contentInfo?.bgImg ?: "").into(viewBinding.ivContentBg) }
        setTitleInfo(cardBean?.contentInfo?.firstTitle, viewBinding.tvContentTitle)
        setTitleInfo(cardBean?.contentInfo?.secondTitle, viewBinding.tvContentSubtitle)


        //contentInfo中jumpUrl不为空时，再添加点击事件
        if(!TextUtils.isEmpty(cardBean?.contentInfo?.jumpUrl)) {
            viewBinding.rclContentTypeOne.setOnClickListener {
                handleClick(cardBean?.contentInfo?.jumpUrl, "")
            }
        }

        if(!TextUtils.isEmpty(cardBean?.contentInfo?.button?.jumpUrl)) {
            viewBinding.ivContentButton.setOnClickListener {
                handleClick(cardBean?.contentInfo?.button?.jumpUrl, "")
            }
        }


        viewBinding.ivBottomLeftIcon.setOnClickListener {
            handleClick(cardBean?.bottomLeftInfo?.jumpUrl, cardBean?.bottomLeftInfo?.toast)
        }

        viewBinding.tvBottomLeftTag.setOnClickListener {
            handleClick(cardBean?.bottomLeftInfo?.jumpUrl, cardBean?.bottomLeftInfo?.toast)
        }

        viewBinding.tvBottomLeftText.setOnClickListener {
            handleClick(cardBean?.bottomLeftInfo?.jumpUrl,  cardBean?.bottomLeftInfo?.toast)
        }

        viewBinding.ivBottomRightIcon.setOnClickListener {
            handleClick(cardBean?.bottomRightInfo?.jumpUrl, cardBean?.bottomRightInfo?.toast)
        }

        viewBinding.tvBottomRightTag.setOnClickListener {
            handleClick(cardBean?.bottomRightInfo?.jumpUrl, cardBean?.bottomRightInfo?.toast)
        }

        viewBinding.tvBottomRightText.setOnClickListener {
            handleClick(cardBean?.bottomRightInfo?.jumpUrl, cardBean?.bottomRightInfo?.toast)
        }

    }

}
