package com.coding.qzy.baselibrary.utils.background.drawable

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import com.coding.qzy.baselibrary.R
import com.coding.qzy.baselibrary.utils.background.drawable.DrawableFactory.getDrawable

class PressDrawableCreator internal constructor(private val drawable: GradientDrawable, private val typedArray: TypedArray, private val pressTa: TypedArray) : ICreateDrawable {
    @Throws(Exception::class)
    override fun create(): Drawable? {
        val stateListDrawable = StateListDrawable()
        for (i in 0 until pressTa.indexCount) {
            val attr = pressTa.getIndex(i)
            if (attr == R.styleable.background_press_bl_pressed_color) {
                val color = pressTa.getColor(attr, 0)
                val pressDrawable = getDrawable(typedArray)
                pressDrawable.setColor(color)
                stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), pressDrawable)
            } else if (attr == R.styleable.background_press_bl_unpressed_color) {
                val color = pressTa.getColor(attr, 0)
                drawable.setColor(color)
                stateListDrawable.addState(intArrayOf(-android.R.attr.state_pressed), drawable)
            }
        }
        return stateListDrawable
    }
}