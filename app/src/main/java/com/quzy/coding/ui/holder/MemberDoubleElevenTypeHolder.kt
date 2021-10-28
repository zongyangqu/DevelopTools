package com.quzy.coding.ui.holder

import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.apkfuns.logutils.LogUtils
import com.bumptech.glide.Glide
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.databinding.ViewDoubleElevenPromotionsBinding
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import com.quzy.coding.util.Constants

/**
 * CreateDate:2021/10/28 14:00
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberDoubleElevenTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }

    val viewBinding by lazy {
        ViewDoubleElevenPromotionsBinding.bind(itemView)
    }


    fun bindData(assetInfo: AssetInfo?, activity: RecyclerViewWaterfallComplexKotActivity?) {
        assetInfo?.doubleElevenPromotions?.forEachIndexed { index, doubleElevenPromotions ->
            when (index) {
                0 -> {
                    viewBinding.ivDoubleElevenBg.setOnClickListener {
                        handleClick(doubleElevenPromotions?.jumpUrl)
                    }

                    activity?.let {
                        Glide.with(it).load(doubleElevenPromotions?.img).into(viewBinding.ivDoubleElevenBg)
                        LogUtils.tag(Constants.LOG_TAG).d(doubleElevenPromotions?.img)
                    }
                }
                1 -> {
                    viewBinding.ivDoubleElevenLeft.setOnClickListener {
                        handleClick(doubleElevenPromotions?.jumpUrl)
                    }
                    activity?.let { Glide.with(it).load(doubleElevenPromotions?.img).into(viewBinding.ivDoubleElevenLeft) }
                }
                2 -> {
                    activity?.let { Glide.with(it).load(doubleElevenPromotions?.img).into(viewBinding.ivDoubleElevenRight) }
                    viewBinding.ivDoubleElevenRight.setOnClickListener {
                        handleClick(doubleElevenPromotions?.jumpUrl)
                    }
                }
            }
        }


    }

    fun handleClick(jumpUrl: String?) {

    }


}