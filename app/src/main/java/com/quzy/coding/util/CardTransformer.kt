package com.quzy.coding.util

import android.annotation.TargetApi
import android.os.Build
import android.view.View
import androidx.viewpager.widget.ViewPager

/**
 * CreateDate:2021/12/3 15:24
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
class CardTransformer : ViewPager.PageTransformer {
    private val mOffset = 25
    private val mOffsetX = 40
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override  fun transformPage(page: View, position: Float) {
        if (position <= 0) {
            page.translationX = -position * page.width
            //缩放卡片并调整位置
            val scale = (page.width + mOffset * position) / page.width
            val scaleX = (page.width + mOffsetX * position) / page.width
            page.scaleX = scaleX
            page.scaleY = scale
            //移动Y轴坐标
            page.translationY = position * mOffset
            page.translationZ = position
        }
    }
}