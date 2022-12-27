package com.quzy.coding.ui.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.webkit.WebView
import com.quzy.coding.R

/**
 * CreateDate:2022/11/18 10:54
 * @author: zongyang qu
 * @Package： cn.yonghui.hyd.pay.membercode.view
 * @Description:
 */
class RoundedWebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs) {
    private var mWidth = 0
    private var mHeight = 0
    var isAllRounded = false
    var radius: Float = 0f
    var topLeftRadius: Float = 0f
    var topRightRadius: Float = 0f
    var bottomLeftRadius: Float = 0f
    var bottomRightRadius: Float = 0f
    var backColor: Int = 0

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedWebView)
        radius = typedArray.getDimension(R.styleable.RoundedWebView_radius, radius)
        topLeftRadius =
            typedArray.getDimension(R.styleable.RoundedWebView_topLeftRadius, topLeftRadius)
        topRightRadius =
            typedArray.getDimension(R.styleable.RoundedWebView_topRightRadius, topRightRadius)
        bottomLeftRadius =
            typedArray.getDimension(R.styleable.RoundedWebView_bottomLeftRadius, bottomLeftRadius)
        bottomRightRadius =
            typedArray.getDimension(R.styleable.RoundedWebView_bottomRightRadius, bottomRightRadius)
        backColor = typedArray.getColor(R.styleable.RoundedWebView_outBackground, backColor)
        isAllRounded = typedArray.getBoolean(R.styleable.RoundedWebView_isAllRounded, isAllRounded)
        typedArray.recycle()
    }

    override fun onSizeChanged(
        newWidth: Int,
        newHeight: Int,
        oldWidth: Int,
        oldHeight: Int
    ) {
        super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight)
        mWidth = newWidth
        mHeight = newHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val path = Path()
        path.fillType = Path.FillType.INVERSE_WINDING
        //顺时针自定义圆角
        val radii = if (isAllRounded) {
            floatArrayOf(
                radius,
                radius,
                radius,
                radius,
                radius,
                radius,
                radius,
                radius
            )
        } else {
            floatArrayOf(
                topLeftRadius,
                topLeftRadius,
                topRightRadius,
                topRightRadius,
                bottomRightRadius,
                bottomRightRadius,
                bottomLeftRadius,
                bottomLeftRadius
            )
        }
        path.addRoundRect(
            RectF(0f, getScrollY().toFloat(), mWidth.toFloat(), (getScrollY() + mHeight).toFloat()),
            radii,
            Path.Direction.CW
        )
        canvas.drawPath(path, createPorterDuffClearPaint())
    }

    //使用Porter Duff Xfer模式从屏幕"清除"该区域
    private fun createPorterDuffClearPaint(): Paint {
        val paint = Paint()
        paint.setColor(backColor)
        paint.setStyle(Paint.Style.FILL)
        paint.setAntiAlias(true)
        paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP))
        return paint
    }

}
