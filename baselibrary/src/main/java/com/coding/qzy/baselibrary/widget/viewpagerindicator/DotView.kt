package com.coding.qzy.baselibrary.widget.viewpagerindicator

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
/**
 * CreateDate:2023/4/14 14:29
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget.viewpagerindicator
 * @Description:
 */
internal class DotView {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var headPoint = Point()
        private set

    var footPoint = Point()
        private set

    var indicatorColor: Int = Color.WHITE
        set(value) {
            field = value
            headPoint.color = value
            footPoint.color = value
        }

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.strokeWidth = 1f
    }

    fun onDraw(canvas: Canvas) {
        canvas.drawCircle(headPoint.x, headPoint.y, headPoint.radius, paint)
        canvas.drawCircle(footPoint.x, footPoint.y, footPoint.radius, paint)
    }
}