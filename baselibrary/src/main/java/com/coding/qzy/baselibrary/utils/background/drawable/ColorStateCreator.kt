package com.coding.qzy.baselibrary.utils.background.drawable

import android.content.res.ColorStateList
import android.content.res.TypedArray
import androidx.annotation.AttrRes
import com.coding.qzy.baselibrary.R

class ColorStateCreator internal constructor(private val textTa: TypedArray) {
    private var states = arrayOf<IntArray?>()
    private var colors = intArrayOf()
    private var index = 0
    fun create(): ColorStateList {
        states = arrayOfNulls(textTa.indexCount)
        colors = IntArray(textTa.indexCount)
        for (i in 0 until textTa.indexCount) {
            val attr = textTa.getIndex(i)
            if (attr == R.styleable.text_selector_bl_checkable_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_checkable)
            } else if (attr == R.styleable.text_selector_bl_unCheckable_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_checkable)
            } else if (attr == R.styleable.text_selector_bl_checked_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_checked)
            } else if (attr == R.styleable.text_selector_bl_unChecked_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_checked)
            } else if (attr == R.styleable.text_selector_bl_enabled_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_enabled)
            } else if (attr == R.styleable.text_selector_bl_unEnabled_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_enabled)
            } else if (attr == R.styleable.text_selector_bl_selected_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_selected)
            } else if (attr == R.styleable.text_selector_bl_unSelected_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_selected)
            } else if (attr == R.styleable.text_selector_bl_pressed_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_pressed)
            } else if (attr == R.styleable.text_selector_bl_unPressed_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_pressed)
            } else if (attr == R.styleable.text_selector_bl_focused_textColor) {
                setStateColor(textTa, attr, android.R.attr.state_focused)
            } else if (attr == R.styleable.text_selector_bl_unFocused_textColor) {
                setStateColor(textTa, attr, -android.R.attr.state_focused)
            }
        }
        return ColorStateList(states, colors)
    }

    private fun setStateColor(selectorTa: TypedArray, attr: Int, @AttrRes functionId: Int) {
        states[index] = intArrayOf(functionId)
        colors[index] = selectorTa.getColor(attr, 0)
        index++
    }
}