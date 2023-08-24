package com.coding.qzy.baselibrary.widget.smoothtransindicator

import android.content.Context
import android.graphics.RectF
import android.graphics.Canvas
import android.graphics.Paint
import android.provider.SyncStateContract
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.apkfuns.logutils.LogUtils
import com.coding.qzy.baselibrary.R

/**
 * CreateDate:2023/4/14 16:54
 *
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget.smoothtransindicator
 * @Description:
 */
class SmoothTransIndicator(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paintSelect: Paint
    private val paintDefault: Paint
    var mNum //个数
            = 0
    var mRadius //半径
            = 0f
    var mLength //线长
            = 0f
    var mHeight //线宽
            = 0f
    private var mOffset //偏移量
            = 0f
    private var mSelected_color //选中颜色
            = 0
    private var mDefault_color //默认颜色
            = 0
    private var mIndicatorType //点类型
            = 0
    private var mDistanceType //距离类型
            = 0
    private var mDistance //间隔距离
            = 0f
    private var mPosition //第几张
            = 0
    private var mPercent = 0f
    private var mIsLeft = false

    private var circleNum = 0

    /**
     * 初始化画笔
     */
    private fun initPaint() {
        //出现
        paintSelect.style = Paint.Style.FILL
        paintSelect.color = mSelected_color
        paintSelect.isAntiAlias = true
        paintSelect.strokeWidth = 3f
        //默认
        paintDefault.style = Paint.Style.FILL
        paintDefault.color = mDefault_color
        paintDefault.isAntiAlias = true
        paintDefault.strokeWidth = 3f
    }

    /**
     * 绘制   invalidate()后 执行
     *
     * @param canvas
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mNum <= 0) {
            return
        }
        val width = canvas.width
        val height = canvas.height
        canvas.translate((width / 2).toFloat(), (height / 2).toFloat())
        //初始化画笔
        initPaint()
        when (mDistanceType) {
            DistanceType.BY_DISTANCE -> {
            }
            DistanceType.BY_RADIUS -> mDistance = 3 * mRadius
            DistanceType.BY_LAYOUT -> mDistance = if (mIndicatorType == IndicatorType.CIRCLE_LINE) {
                (width / (mNum + 1)).toFloat()
            } else {
                (width / mNum).toFloat()
            }
        }
        when (mIndicatorType) {
            IndicatorType.CIRCLE -> {
                var i = 0
                while (i < mNum) {
                    //默认点 -(mNum - 1) * 0.5f * mDistance 第一个点
                    canvas.drawCircle(
                        -(mNum - 1) * 0.5f * mDistance + i * mDistance,
                        0f,
                        mRadius,
                        paintDefault
                    )
                    i++
                }
                //选中
                canvas.drawCircle(
                    -(mNum - 1) * 0.5f * mDistance + mOffset,
                    0f,
                    mRadius,
                    paintSelect
                )
            }
            IndicatorType.CIRCLE_LINE -> {
                val appearColor =
                    GradientColorUtil.caculateColor(mDefault_color, mSelected_color, mPercent)
                val dismissColor =
                    GradientColorUtil.caculateColor(mSelected_color, mDefault_color, mPercent)
                //第一个点增长
                val leftClose = -mNum * 0.5f * mDistance + mPosition * mDistance - mRadius
                val rightClose = leftClose + 2 * mRadius + mDistance - mOffset
                val tip = RectF(0f, -mRadius, 0f, mRadius)
                val offset = if (mIsLeft) 1 else 0
                //mPosition右边的圆点
                run {
                    var i = mPosition + 2 + offset
                    while (i <= mNum) {
                        tip.left = -mNum * 0.5f * mDistance + i * mDistance - mRadius
                        tip.right = -mNum * 0.5f * mDistance + i * mDistance + mRadius
                        canvas.drawRoundRect(tip, mRadius, mRadius, paintDefault)
                        i++
                    }
                }
                //mPosition左边的圆点
                var i = mPosition - 1 + offset
                while (i >= 0) {
                    tip.left = -mNum * 0.5f * mDistance + i * mDistance - mRadius
                    tip.right = -mNum * 0.5f * mDistance + i * mDistance + mRadius
                    canvas.drawRoundRect(tip, mRadius, mRadius, paintDefault)
                    i--
                }
                val rectClose = RectF(leftClose, -mRadius, rightClose, mRadius) // 设置个新的长方形
                paintSelect.color = dismissColor
                canvas.drawRoundRect(rectClose, mRadius, mRadius, paintSelect)
                //第二个点消失
                if (mPosition < mNum - 1) {
                    val rightOpen = -mNum * 0.5f * mDistance + (mPosition + 2) * mDistance + mRadius
                    val leftOpen = rightOpen - 2 * mRadius - mOffset
                    val rectOpen = RectF(leftOpen, -mRadius, rightOpen, mRadius) // 设置个新的长方形
                    paintSelect.color = appearColor
                    canvas.drawRoundRect(rectOpen, mRadius, mRadius, paintSelect)
                }
            }
        }
    }

    /**
     * xml 参数设置  选中颜色 默认颜色  点大小 长度 距离 距离类型 类型 真实个数(轮播)
     *
     * @param context
     * @param attrs
     */
    private fun setStyleable(context: Context, attrs: AttributeSet) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.SmoothTransIndicator)
        mSelected_color = array.getColor(R.styleable.SmoothTransIndicator_selected_color, -0x1)
        mDefault_color = array.getColor(R.styleable.SmoothTransIndicator_default_color, -0x323233)
        mRadius = array.getDimension(R.styleable.SmoothTransIndicator_radius, 20f) //px
        mLength = array.getDimension(R.styleable.SmoothTransIndicator_length, 2 * mRadius) //px
        mDistance = array.getDimension(R.styleable.SmoothTransIndicator_distance, 3 * mRadius) //px
        mDistanceType =
            array.getInteger(R.styleable.SmoothTransIndicator_distanceType, DistanceType.BY_RADIUS)
        mIndicatorType =
            array.getInteger(R.styleable.SmoothTransIndicator_indicatorType, IndicatorType.CIRCLE)
        mNum = array.getInteger(R.styleable.SmoothTransIndicator_num, 0)
        array.recycle()
        invalidate()
    }

    /**
     * 移动指示点
     *
     * @param percent  比例
     * @param position 第几个
     * @param isLeft   是否左滑
     */
    fun move(percent: Float, position: Int, isLeft: Boolean) {
        mPosition = position
        mPercent = percent
        mIsLeft = isLeft
        when (mIndicatorType) {
            IndicatorType.CIRCLE_LINE -> mOffset = percent * mDistance
            IndicatorType.CIRCLE -> mOffset = if (mPosition == mNum - 1) {
                (1 - percent) * (mNum - 1) * mDistance
            } else { //中间的
                (percent + mPosition) * mDistance
            }
        }
        invalidate()
    }

    /**
     * 个数
     *
     * @param num
     */
    fun setNum(num: Int): SmoothTransIndicator {
        mNum = num
        invalidate()
        return this
    }

    fun setCircleNum(num: Int) {
        circleNum = num
    }



    /**
     * 类型
     *
     * @param indicatorType
     */
    fun setType(indicatorType: Int): SmoothTransIndicator {
        mIndicatorType = indicatorType
        invalidate()
        return this
    }

    /**
     * 线,圆
     */
    interface IndicatorType {
        companion object {
            const val CIRCLE = 1
            const val CIRCLE_LINE = 2
        }
    }

    /**
     * 半径
     *
     * @param radius
     */
    fun setRadius(radius: Float): SmoothTransIndicator {
        mRadius = radius
        invalidate()
        return this
    }

    /**
     * 距离 在IndicatorDistanceType为BYDISTANCE下作用
     *
     * @param distance
     */
    fun setDistance(distance: Float): SmoothTransIndicator {
        mDistance = distance
        invalidate()
        return this
    }

    /**
     * 距离类型
     *
     * @param mDistanceType
     */
    fun setDistanceType(mDistanceType: Int): SmoothTransIndicator {
        this.mDistanceType = mDistanceType
        invalidate()
        return this
    }

    /**
     * 布局,距离,半径
     */
    interface DistanceType {
        companion object {
            //
            const val BY_RADIUS = 0
            const val BY_DISTANCE = 1
            const val BY_LAYOUT = 2
        }
    }

    fun setViewPager(viewPager: ViewPager?): SmoothTransIndicator {
        setViewPager(viewPager, viewPager?.adapter?.count?:0)
        return this
    }

    fun setViewPager(viewpager: ViewPager?, CycleNumber: Int): SmoothTransIndicator {
        mNum = CycleNumber
        viewpager?.addOnPageChangeListener(object : OnPageChangeListener {
            //记录上一次滑动的positionOffsetPixels值
            private var lastValue = -1
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                var tempPosition = position
                if (circleNum > 0) {
                    tempPosition %= circleNum
                    LogUtils.tag("LOG_TAG").d("onPageScrolled======${tempPosition}")
                }
                var isLeft = mIsLeft
                if (lastValue / 10 > positionOffsetPixels / 10) {
                    //右滑
                    isLeft = false
                } else if (lastValue / 10 < positionOffsetPixels / 10) {
                    //左滑
                    isLeft = true
                }
                if (mNum > 0) {
                    move(positionOffset, tempPosition % mNum, isLeft)
                }
                lastValue = positionOffsetPixels
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
        return this
    }

    companion object {
        /**
         * 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
         */
        private const val M = 0.551915024494f
    }

    init {
        setStyleable(context, attrs)
        paintDefault = Paint()
        paintSelect = Paint()
    }
}