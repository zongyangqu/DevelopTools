package com.quzy.coding.ui.widget.bottomnavigationbar

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.R
import com.quzy.coding.util.ResourceUtil
import com.quzy.coding.util.SkinUtils
import com.quzy.coding.util.extend.ctx
import com.quzy.coding.util.extend.gone
import com.quzy.coding.util.extend.show
import kotlinx.android.synthetic.main.view_bottomitem.view.circle_badge
import kotlinx.android.synthetic.main.view_bottomitem.view.home_bottom_view
import kotlinx.android.synthetic.main.view_bottomitem.view.navbar_badge
import kotlinx.android.synthetic.main.view_bottomitem.view.navbar_badge_font
import kotlinx.android.synthetic.main.view_bottomitem.view.navbar_icon
import kotlinx.android.synthetic.main.view_bottomitem.view.navbar_text
import kotlinx.android.synthetic.main.view_bottomitem.view.nvabar_icon_layout

/**
 * CreateDate:2023/6/16 17:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget.bottomnavigationbar
 * @Description:
 */
open class BottomNavigationItem(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    var tabTag: Int
) : LinearLayoutCompat(context, attrs, defStyleAttr) {
    val mItemView: View
    var mItemData: NavigationItemBean? = null
    var hasBadgeItem: Boolean = false
    var mInActiveDrawable: Int = 0
    var mActiveDrawable: Int = 0
    lateinit var mBlowUpAnimator: ObjectAnimator
    lateinit var mShrinkAnimator: ObjectAnimator

    var activeColor: Int = SkinUtils.getColor(context, R.color.primary_color)

    constructor(context: Context, attrs: AttributeSet?, tabTag: Int) : this(
        context,
        attrs,
        0,
        tabTag
    )

    constructor(context: Context, tabTag: Int) : this(context, null, 0, tabTag)

    companion object {
        val DEFAULT_CORNER_RADIUS_DIP = 10
        private val DEFAULT_DURATION = 130L
    }

    init {
        mItemView = LayoutInflater.from(ctx).inflate(getItemLayoutResource(), this)
    }

    protected open fun getItemLayoutResource(): Int {
        return R.layout.view_bottomitem
    }

    fun setItemData(itemData: NavigationItemBean): BottomNavigationItem {
        mItemData = itemData
        getDefaultInActiveDrawable()
        initViewData()
        initAnimator()
        return this
    }

    private fun getDefaultInActiveDrawable() {
        mActiveDrawable = mItemData?.activeRes!!
        mInActiveDrawable = mItemData?.inActiveRes!!
    }

    private fun initViewData() {
        mItemView.navbar_icon.setImageDrawable(SkinUtils.getDrawable(context, mInActiveDrawable))
        mItemView.navbar_text.text = mItemData?.name
        mItemView.navbar_text.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.subRegularBlackColor
            )
        )
    }

    fun setBadgeItem(badgeItem: BadgeItem? = null): BottomNavigationItem {
        hasBadgeItem = true
        mItemView.navbar_badge.visibility = View.VISIBLE
        if (badgeItem != null) {
            // TODO 使用自定义的badgeItem
        }
        return this
    }

    /**
     * 实心红点提示，不能设置数字的
     */
    fun setRedDot(isShow: Boolean): BottomNavigationItem {
        hasBadgeItem = true
        mItemView.circle_badge.show(isShow)
        return this
    }

    fun getCartView(): View = mItemView.nvabar_icon_layout

    /**
     * @method setBadgeCount
     * @description 设置角标数字和背景
     * @date: 2020/6/17 10:42 AM
     * @author: ZhaoXuan.Zeng
     * @param [count]
     * @return
     */
    fun setBadgeCount(count: Int) {
        if (count > 99) {
            mItemView.navbar_badge.gone()
            mItemView.navbar_badge_font.show()

            mItemView.navbar_badge_font.text = ResourceUtil.getString(R.string.icon_more)
            mItemView.navbar_badge_font.setBackgroundResource(R.drawable.bg_bottom_cart_badge_rect)
        } else {
            mItemView.navbar_badge.show()
            mItemView.navbar_badge_font.gone()
            if (count < 10) {
                mItemView.navbar_badge.layoutParams.apply {
                    height = 16f.dpOfInt
                    width = 16f.dpOfInt
                }
                mItemView.navbar_badge.setBackgroundResource(R.drawable.bg_badge_circle_white_border)
            } else {
                mItemView.navbar_badge.layoutParams.apply {
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                    width = ViewGroup.LayoutParams.WRAP_CONTENT
                }
                mItemView.navbar_badge.setBackgroundResource(R.drawable.bg_badge_rect)
            }
            mItemView.navbar_badge.text = count.toString()
        }
    }

    /**
     * @method hideNumBadge
     * @description 隐藏数字角标
     * @date: 2020/8/7 10:49 AM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    fun hideNumBadge() {
        mItemView.navbar_badge.gone()
        mItemView.navbar_badge_font.gone()
    }

    /**
     * @method showNumBadge
     * @description 展示数字角标
     * @date: 2020/8/7 10:49 AM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    fun showNumBadge(count: Int) {
        if (count > 99) {
            mItemView.navbar_badge.gone()
            mItemView.navbar_badge_font.show()
        } else {
            mItemView.navbar_badge.show()
            mItemView.navbar_badge_font.gone()
        }
    }

    fun changeState(current: Int) {
        //mItemView.home_bottom_view.changeState(current)
    }

    fun imageBySelect() {
        when (isSelected) {
            true -> {
                mItemView.navbar_icon.setImageDrawable(
                    SkinUtils.getDrawable(
                        context,
                        mActiveDrawable
                    )
                )
                mItemView.navbar_text.setTextColor(activeColor)
                mBlowUpAnimator.start()
            }
            false -> {
                mShrinkAnimator.start()
                mItemView.navbar_icon.setImageDrawable(
                    SkinUtils.getDrawable(
                        context,
                        mInActiveDrawable
                    )
                )
                mItemView.navbar_text.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.subRegularBlackColor
                    )
                )
            }
        }
    }

    /**
     * @method initAnimator
     * @description 初始化放大缩小动画
     * @date: 2020/5/26 5:21 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    private fun initAnimator() {
        val pvX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.12f)
        val pvY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.12f)
        mBlowUpAnimator = ObjectAnimator.ofPropertyValuesHolder(mItemView, pvX, pvY)
        mBlowUpAnimator.duration = DEFAULT_DURATION
        val pvSX = PropertyValuesHolder.ofFloat("scaleX", 1.12f, 1.0f)
        val pvSY = PropertyValuesHolder.ofFloat("scaleY", 1.12f, 1.0f)
        mShrinkAnimator = ObjectAnimator.ofPropertyValuesHolder(mItemView, pvSX, pvSY)
        mShrinkAnimator.duration = DEFAULT_DURATION
    }
}
