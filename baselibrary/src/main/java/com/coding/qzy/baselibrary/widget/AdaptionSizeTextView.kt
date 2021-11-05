package com.coding.qzy.baselibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.ViewTreeObserver
import androidx.appcompat.widget.AppCompatTextView

/**
 * CreateDate:2021/10/21 10:40
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description: 自适应字体大小在一行显示的TextView
 */
class AdaptionSizeTextView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatTextView(context, attrs, defStyleAttr), ViewTreeObserver.OnGlobalLayoutListener {

    private var defaultSize = 30f

    init {
        viewTreeObserver.addOnGlobalLayoutListener(this)
        defaultSize = textSize
    }


    override fun onGlobalLayout() {
        val lineCount = lineCount
        var textSize = textSize
        if (lineCount > 1 && textSize > defaultSize / 2) {
            textSize--
            //重新设置大小,该方法会立即触发onGlobalLayout()
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        }
    }

}