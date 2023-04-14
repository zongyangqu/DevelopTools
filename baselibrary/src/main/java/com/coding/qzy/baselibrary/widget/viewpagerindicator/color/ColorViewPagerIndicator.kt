package com.coding.qzy.baselibrary.widget.viewpagerindicator.color

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.coding.qzy.baselibrary.R
import com.coding.qzy.baselibrary.widget.viewpagerindicator.ViewPagerIndicator
import com.tlz.viewpagerindicator.color.ColorItem

/**
 * CreateDate:2023/4/14 14:41
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget.viewpagerindicator.color
 * @Description:
 */
class ColorViewPagerIndicator(ctx: Context, attrs: AttributeSet) : ViewPagerIndicator<ColorItem>(ctx, attrs) {

    private val itemShape: Int
    private val itemSize: Float
    private val itemGap: Int
    private val itemColor: Int
    private val itemSelectColor: Int
    private val itemRound: Float

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val argbEvaluator = ArgbEvaluator()

    init {
        val ta = resources.obtainAttributes(attrs, R.styleable.ColorViewPagerIndicator)

        itemShape = ta.getInt(R.styleable.ColorViewPagerIndicator_cvpi_shape, 0)

        itemSize = ta.getDimensionPixelSize(R.styleable.ColorViewPagerIndicator_cvpi_size, dimen(R.dimen.def_color_size)).toFloat()

        itemColor = ta.getColor(R.styleable.ColorViewPagerIndicator_cvpi_color, resources.getColor(R.color.def_color_normal))
        itemSelectColor = ta.getColor(R.styleable.ColorViewPagerIndicator_cvpi_select_color, resources.getColor(R.color.def_color_select))

        itemGap = ta.getDimensionPixelSize(R.styleable.ColorViewPagerIndicator_cvpi_gap, dimen(R.dimen.def_color_gap))
        itemRound = ta.getDimensionPixelSize(R.styleable.ColorViewPagerIndicator_cvpi_round, dimen(R.dimen.def_color_round)).toFloat()

        ta.recycle()

        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val itemCount = viewPager?.adapter?.count ?: 0
        val widthSpecMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = View.MeasureSpec.getSize(widthMeasureSpec)
        var widthSize = widthSpecSize
        if (widthSpecMode == MeasureSpec.UNSPECIFIED || widthSpecMode == MeasureSpec.AT_MOST) {
            widthSize = (paddingLeft + paddingRight + itemGap * (itemCount - 1) + itemSize * itemCount).toInt() + 2
        }
        val heightSpecMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = View.MeasureSpec.getSize(heightMeasureSpec)
        var heightSize = heightSpecSize
        if (heightSpecMode == MeasureSpec.UNSPECIFIED || heightSpecMode == MeasureSpec.AT_MOST) {
            heightSize = (paddingTop + paddingBottom + itemSize + 2).toInt()
        }
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onCustomDraw(cvs: Canvas) {
        if (viewPager != null && viewPager?.adapter?.count ?: 0 > 0) {
            items.forEach {
                paint.color = it.color
                when (itemShape) {
                    0 -> cvs.drawCircle(it.rectF.centerX(), it.rectF.centerY(), itemSize / 2f, paint)
                    1 -> cvs.drawRect(it.rectF, paint)
                    else -> cvs.drawRoundRect(it.rectF, itemRound, itemRound, paint)
                }
            }
        }
    }

    override fun calculate() {
        items.clear()
        val itemCount = viewPager?.adapter?.count ?: 0
        if (itemCount > 0) {
            val currentItem = viewPager?.currentItem ?: 0
            // 计算各个点的位置.
            val y = height / 2f
            // 得到最左边开始绘制的位置.
            var startX = width / 2 - (itemGap * (itemCount - 1) + itemSize * itemCount) / 2f
            (0 until itemCount).mapTo(items) {
                val l = startX
                val r = l + itemSize
                startX = r + itemGap
                ColorItem(RectF(l, y - itemSize / 2, r, y + itemSize / 2), if (it == currentItem) itemSelectColor else itemColor)
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (items.size > 1) {
            if (position < items.size - 1) {
                items[position + 1].color = getColorByFraction(positionOffset)
                items[position].color = getColorByFraction(1 - positionOffset)
            } else {
                items[position].color = itemSelectColor
                items[position - 1].color = itemColor
            }

            postInvalidate()
        }
    }

    override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
        // 清空数据
        items.clear()
    }

    private fun getColorByFraction(fraction: Float) = argbEvaluator.evaluate(fraction, itemColor, itemSelectColor).toString().toInt()

}