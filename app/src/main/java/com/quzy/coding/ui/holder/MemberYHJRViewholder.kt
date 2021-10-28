package com.quzy.coding.ui.holder

import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.ScreenUtils
import com.quzy.coding.R
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.databinding.ViewContentYhjrBinding
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import com.quzy.coding.util.view.YHJRMemberView

/**
 * CreateDate:2021/10/25 10:59
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberYHJRViewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ViewContentYhjrBinding = ViewContentYhjrBinding.bind(itemView)
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }

    fun bindView(
            assetInfo: AssetInfo?,
            fragment: RecyclerViewWaterfallComplexKotActivity?
    ) {
        this.fragment = fragment
        binding.llYhjrContent.removeAllViews()
        val size: Int = assetInfo?.payConfigInfos?.size ?: return
        val width: Int =
                ((ScreenUtils.getScreenWidth(itemView.context) - (12f.dp * 2)) / size).toInt()
        val height: Int =
                itemView.context.resources.getDimensionPixelSize(R.dimen.yhjr_member_item_height)
        assetInfo.payConfigInfos?.forEachIndexed { index, config ->
            val params = LinearLayout.LayoutParams(width, height)
            val yhjrMemberView = YHJRMemberView(itemView.context)
            yhjrMemberView.contentDescription = fragment?.getString(R.string.member_bury_yhjr)
            yhjrMemberView.setData(config)

            if (!TextUtils.isEmpty(config.cornerMarkText)) {
                val gap = (width - 26f.dpOfInt) / 2
                val tagLeft = (gap + 26f.dpOfInt - 8f.dpOfInt) + index * width
                // val tag = ImageLoaderView(itemView.context)
                val tag = TextView(itemView.context)
                tag.text = config.cornerMarkText
                tag.gravity = Gravity.CENTER
                tag.minWidth = 34f.dpOfInt
                tag.setTextSize(TypedValue.COMPLEX_UNIT_PX, 10f.dp)
                tag.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                tag.background = ContextCompat.getDrawable(itemView.context, R.drawable.gift_item_bg)
                val tagParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        16f.dpOfInt
                )
                tagParams.leftMargin = tagLeft
                tagParams.topMargin = 4f.dpOfInt
                binding.yhjrListView.addView(tag, tagParams)
            }

            binding.llYhjrContent.addView(yhjrMemberView, params)
            yhjrMemberView.setOnClickListener {

            }
        }

        val image = binding.yhjrListView.findViewById<ImageView>(R.id.yhjr_member_arrow_id)
        if (image != null) {
            binding.yhjrListView.removeView(image)
        }
        val frameParams =
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, height, Gravity.RIGHT)
        frameParams.rightMargin = width - 6f.dpOfInt // 6dp 是图片大约的宽度除以2
        binding.yhjrListView.addView(
                ImageView(itemView.context).apply {
                    id = R.id.yhjr_member_arrow_id
                    setImageResource(R.mipmap.ic_member_my_wallet_arrow)
                },
                frameParams
        )
    }

    fun bindData(
            assetInfo: AssetInfo?,
            fragment: RecyclerViewWaterfallComplexKotActivity?
    ) {
//        bindView(assetInfo, mLoginStatus, fragment)
        binding.yhjrListView.visibility = View.VISIBLE
    }

}