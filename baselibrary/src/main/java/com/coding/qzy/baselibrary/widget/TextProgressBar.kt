package com.coding.qzy.baselibrary.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.coding.qzy.baselibrary.R

/**
 * CreateDate:2022/7/29 9:15
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description:
 */
class TextProgressBar : View {

    private val colorArray = IntArray(2)
    private val positionArray = floatArrayOf(
        0f,
        1f
    )
    private var mBarHeight = 8
    private var mTrackColor = Color.parseColor("#d6eafe")
    private var mStartColor = Color.parseColor("#4a6cfb")
    private var mEndColor = Color.parseColor("#819af2")
    private var mTextColor = Color.GRAY
    private lateinit var mTrackPaint: Paint
    private lateinit var mBarPaint: Paint

    private var mMaxProgress = 0f
    private var mCurrentProgress = 0f
    private val animateTime = 0L

    // 使用this委托给两个参数的构造函数
    constructor(context: Context) : this(context, null)

    // 使用this委托给三个参数的构造函数
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    // 使用super调用父类的构造函数，初始化其基类
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.TextProgressBar)
        mBarHeight = ta.getDimensionPixelOffset(R.styleable.TextProgressBar_barHeight1, context.dp2px(mBarHeight))
        mTrackColor = ta.getColor(R.styleable.TextProgressBar_trackColor1, mTrackColor)
        mTextColor = ta.getColor(R.styleable.TextProgressBar_barTextColor1, mTextColor)
        mStartColor = ta.getColor(R.styleable.TextProgressBar_barStartColor1, mStartColor)
        mEndColor = ta.getColor(R.styleable.TextProgressBar_barEndColor1, mEndColor)
        colorArray[0] = mStartColor
        colorArray[1] = mEndColor
        ta.recycle()

        initPaint()
    }

    private fun initPaint() {
        mTrackPaint = Paint().apply {
            color = mTrackColor
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            strokeWidth = mBarHeight.toFloat()
            style = Paint.Style.FILL_AND_STROKE
        }

        mBarPaint = Paint().apply {
            strokeCap = Paint.Cap.ROUND
            isAntiAlias = true
            strokeWidth = mBarHeight.toFloat()
            style = Paint.Style.FILL_AND_STROKE
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        if (heightMode == MeasureSpec.AT_MOST) {
            height = mBarHeight
        }
        setMeasuredDimension(width, height)
    }

    fun initColor(startColor: String, endColor: String, bgColor: String) {
        mTrackColor = Color.parseColor(bgColor)
        mStartColor = Color.parseColor(startColor)
        mEndColor = Color.parseColor(endColor)
        colorArray[0] = mStartColor
        colorArray[1] = mEndColor
        initPaint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 画底部进度滑轨
        canvas?.drawLine(
            10f,
            height / 2f,
            width - 10f,
            height / 2f,
            mTrackPaint
        )

        val currentX = (mCurrentProgress / mMaxProgress) * width - mBarHeight / 2 // 为滑轨设置渐变色
        val gradient = LinearGradient(
            0f,
            height / 2f,
            currentX + mBarHeight / 2,
            height / 2f,
            colorArray,
            positionArray,
            Shader.TileMode.REPEAT
        )
        mBarPaint.shader = gradient
        // 画进度条滑轨
        canvas?.drawLine(
            10f,
            height / 2f,
            currentX,
            height / 2f,
            mBarPaint
        )
    }

    fun setMaxProgress(maxProgress: Float) {
        this.mMaxProgress = maxProgress
    }

    fun setCurrentProgress(progress: Float) {
        val animator = ValueAnimator.ofFloat(0f, progress)
        animator.addUpdateListener {
            mCurrentProgress = it.animatedValue as Float
            invalidate()
        }
        animator.interpolator = AccelerateInterpolator()
        animator.duration = animateTime
        animator.start()
    }

    /**
     * dp转为px
     */
    private fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
