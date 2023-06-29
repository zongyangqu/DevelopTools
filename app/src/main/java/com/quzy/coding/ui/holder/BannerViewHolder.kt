package com.quzy.coding.ui.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.databinding.MemberCodeSectionBannerBinding

/**
 * CreateDate:2023/6/17 15:42
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class BannerViewHolder (v: View) : RecyclerView.ViewHolder(v) {


    val viewBinding by lazy {
        MemberCodeSectionBannerBinding.bind(v)
    }
}