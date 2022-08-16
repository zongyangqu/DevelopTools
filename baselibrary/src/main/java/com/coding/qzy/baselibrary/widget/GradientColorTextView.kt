package com.coding.qzy.baselibrary.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * CreateDate:2022/8/3 11:30
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description:
 */
class GradientColorTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var mLinearGradient: LinearGradient? = null
    private var mPaint: Paint? = null
    private var mViewWidth = 0
    private val mTextBound: Rect = Rect()

    override fun onDraw(canvas: Canvas?) {
        mViewWidth = measuredWidth
        mPaint = paint
        val mTipText = text.toString()
        mPaint?.getTextBounds(mTipText, 0, mTipText.length, mTextBound)
        mLinearGradient = LinearGradient(
            0f, 0f, mViewWidth.toFloat(), 0f, intArrayOf(-0x1546, -0x4174b7),
            null, Shader.TileMode.REPEAT
        )
        mPaint?.setShader(mLinearGradient)
        canvas!!.drawText(
            mTipText,
            (measuredWidth / 2 - mTextBound.width() / 2).toFloat(),
            (measuredHeight / 2 + mTextBound.height() / 2).toFloat(), mPaint
        )
    }


}
