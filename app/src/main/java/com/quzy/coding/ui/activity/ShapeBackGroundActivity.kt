package com.quzy.coding.ui.activity

import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityShapeBackgroundBinding
import com.quzy.coding.util.DrawableUtils
import com.quzy.coding.util.ResourceUtil
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.tv_day_unit


/**
 * CreateDate:2023/5/24 15:04
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 显示指定时间戳倒计时
 */
class ShapeBackGroundActivity : BaseActivity() {


    private val blackColorSpan by lazy {
        ForegroundColorSpan(ContextCompat.getColor(this, R.color.subMediumBlackColor))
    }

    private val redColorSpan by lazy {
        ForegroundColorSpan(ContextCompat.getColor(this, R.color.orangeColorGradientTo))
    }

    var viewBinding: ActivityShapeBackgroundBinding? = null

    var str1 = "¥20\n满99可用"

    override fun onViewCreated() {
        setTextStr1()
        setTextBgStr2()
        setTextBgStr3()
        setTextBgStr4()
    }

    fun setTextBgStr2() {
        val gradient = LinearGradient(
            0f,
            0f,
            0f,
            UIUtils.sp2px(this, 10f) + 2f.dp,
            ResourceUtil.getColor(R.color.orangeColorGradientFrom),
            ResourceUtil.getColor(R.color.orangeColorGradientTo),
            Shader.TileMode.CLAMP
        )
        viewBinding?.str2?.paint?.shader = gradient
        viewBinding?.str2?.invalidate()
    }

    fun setTextBgStr3() {
        viewBinding?.str3?.background = DrawableUtils.createCornerTopLeftToBottomRightDrawable(
            3f,
            3f,
            3f,
            3f,
            ResourceUtil.getColor(R.color.orangeColorGradientFrom),
            ResourceUtil.getColor(R.color.orangeColorGradientTo)
        )
    }

    fun setTextBgStr4() {
        viewBinding?.str4?.background = DrawableUtils.createCornerTopLeftToBottomRightDrawable(
            12f, 2f, 12f, 2f,
            ResourceUtil.getColor(R.color.font_orange_color),
            ResourceUtil.getColor(R.color.orangeColorGradientTo)
        )
    }

    fun setTextStr1() {
        val spannableString = SpannableStringBuilder()
        spannableString.append(str1)
        //设置指定文字大小
        spannableString.setSpan(
            AbsoluteSizeSpan(UIUtils.sp2px(this, 14f)),
            0,
            1,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        spannableString.setSpan(
            AbsoluteSizeSpan(UIUtils.sp2px(this, 24f)),
            1,
            3,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        //设置指定文字颜色
        spannableString.setSpan(
            redColorSpan,
            0,
            3,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        // 设置优惠价格字体为指定字体包括¥
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            str1.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        viewBinding?.str1?.text = spannableString
    }


    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityShapeBackgroundBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

}