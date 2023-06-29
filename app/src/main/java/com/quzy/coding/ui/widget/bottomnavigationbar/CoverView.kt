package com.quzy.coding.ui.widget.bottomnavigationbar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.view.View

/**
 * CreateDate:2023/6/16 17:51
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget.bottomnavigationbar
 * @Description:
 */
class CoverView(ctx: Context) : View(ctx) {

    private var bgPaint: Paint = Paint()
    private var paintarc: Paint = Paint()

    init {
        bgPaint.color = Color.WHITE

        val porterDuffXfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        paintarc.xfermode = porterDuffXfermode
        paintarc.isAntiAlias = true
        paintarc.color = Color.WHITE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val bitmap = Bitmap.createBitmap(canvas.width, canvas.height, Bitmap.Config.ARGB_4444)
        val temp = Canvas(bitmap)
        temp.drawRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), bgPaint)
        temp.drawCircle(width / 2f, height / 2f, height / 2 - 15f, paintarc)
        canvas.drawFilter = PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
        canvas.drawBitmap(bitmap, 0f, 0f, bgPaint)
        bitmap.recycle()
    }
}