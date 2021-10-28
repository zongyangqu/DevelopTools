package com.quzy.coding.util.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.quzy.coding.R
import com.quzy.coding.bean.PayConfigInfo
import com.quzy.coding.databinding.LayoutYhjrMemberBinding

/**
 * CreateDate:2021/10/25 11:26
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.view
 * @Description:
 */
class YHJRMemberView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        ConstraintLayout(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val binding: LayoutYhjrMemberBinding =
            LayoutYhjrMemberBinding.bind(LayoutInflater.from(context).inflate(R.layout.layout_yhjr_member, this, true))

    fun setData(payConfigInfo: PayConfigInfo) {
        Glide.with(context).load(payConfigInfo.icon).into(binding.image)
        binding.tvTitle.text = payConfigInfo.copywriting
    }
}