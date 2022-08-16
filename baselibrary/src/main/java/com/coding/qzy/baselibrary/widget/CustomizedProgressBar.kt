package com.coding.qzy.baselibrary.widget

import android.content.Context
import android.graphics.* // ktlint-disable no-wildcard-imports
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec
import com.coding.qzy.baselibrary.R

/**
 * CreateDate:2022/7/28 16:46
 *
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description:
 */
class CustomizedProgressBar : View {
    /***
     * 设置最大的进度值
     * @param maxCount 最大的进度值
     */
    var maxCount = 100f // 进度条最大值
    private var currentCount = // 进度条当前值
        0f

    // private Paint  mPaint ;
    private var mWidth = 0
    private var mHeight = 0
    private var mContext: Context? = null

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context) : super(context) {
        initView(context)
    }

    private fun initView(context: Context) {
        mContext = context
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val mPaint = Paint()
        mPaint.isAntiAlias = true
        val round = 12 // 半径
        mPaint.color = resources.getColor(R.color.result_view) // 设置边框背景颜色
        val rectBg = RectF(0f, 0f, mWidth.toFloat(), mHeight.toFloat())
        canvas.drawRoundRect(rectBg, round.toFloat(), round.toFloat(), mPaint) // 绘制 最外面的大 圆角矩形，背景为白色
        val section = currentCount / maxCount // 进度条的比例
        val rectProgressBg = RectF(0f, 0f, mWidth * section, mHeight.toFloat())
        Log.e("CustomizedProgressBar", currentCount.toString() + "")
        Log.e("CustomizedProgressBar", section.toString() + "")

        // Paint设置setColor(白色无透明)和setShader，只让setShader生效；不然前面setColor设置了透明度，透明度会生效，和setShader效果叠加
        mPaint.color = resources.getColor(R.color.white)
        mPaint.shader = linearGradient
        canvas.drawRoundRect(rectProgressBg, round.toFloat(), round.toFloat(), mPaint) // 最左边的圆角矩形
        if (maxCount != currentCount) { // 如果不是100%，绘制第三段矩形
            val rectProgressBg2 =
                RectF(mWidth * section - round, 0f, mWidth * section, mHeight.toFloat())
            mPaint.shader = linearGradient
            canvas.drawRect(rectProgressBg2, mPaint)
        }
    }

    // 根据R文件中的id获取到color
    private var linearGradient: LinearGradient? = null
        private get() {
            if (field == null) {
                field = LinearGradient(
                    0f, 0f, width.toFloat(), mHeight.toFloat(),
                    intArrayOf(
                        mContext!!.resources.getColor(R.color.progress_color_1),
                        mContext!!.resources.getColor(R.color.progress_color_2)
                    ),
                    null, Shader.TileMode.CLAMP
                ) // 根据R文件中的id获取到color
            }
            return field
        }

    private fun dipToPx(dip: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dip * scale + 0.5f * if (dip >= 0) 1 else -1).toInt()
    }

    /***
     * 设置当前的进度值
     * @param currentCount 当前进度值
     */
    fun setCurrentCount(currentCount: Float) {
        this.currentCount = if (currentCount > maxCount) maxCount else currentCount
        invalidate()
    }

    fun getCurrentCount(): Float {
        return currentCount
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)
        mWidth = if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            widthSpecSize
        } else {
            0
        }
        mHeight =
            if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
                dipToPx(18)
            } else {
                heightSpecSize
            }
        setMeasuredDimension(mWidth, mHeight)
    }
}
