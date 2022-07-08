package com.coding.qzy.baselibrary.utils.background.drawable

import android.R
import android.annotation.TargetApi
import android.content.res.ColorStateList
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import java.util.*

/**
 * Created by xiaoqi on 2018/10/22
 */
class DrawableCreator {
    enum class Shape(var value: Int) {
        Rectangle(0), Oval(1), Line(2), Ring(3);
    }

    enum class Gradient(var value: Int) {
        Linear(0), Radial(1), Sweep(2);
    }

    class Builder {
        private var shape = Shape.Rectangle
        private var solidColor: Int? = null
        private var cornersRadius: Float? = null
        private var cornersBottomLeftRadius: Float? = null
        private var cornersBottomRightRadius: Float? = null
        private var cornersTopLeftRadius: Float? = null
        private var cornersTopRightRadius: Float? = null
        private var gradientAngle = -1
        private var gradientCenterX: Float? = null
        private var gradientCenterY: Float? = null
        private var gradientCenterColor: Int? = null
        private var gradientEndColor: Int? = null
        private var gradientStartColor: Int? = null
        private var gradientRadius: Float? = null
        private var gradient = Gradient.Linear
        private var useLevel = false
        private val padding = Rect()
        private var sizeWidth: Float? = null
        private var sizeHeight: Float? = null
        private var strokeWidth: Float? = null
        private var strokeColor: Int? = null
        private var strokeDashWidth = 0f
        private var strokeDashGap = 0f
        private var rippleEnable = false
        private var rippleColor: Int? = null
        private var checkableStrokeColor: Int? = null
        private var checkedStrokeColor: Int? = null
        private var enabledStrokeColor: Int? = null
        private var selectedStrokeColor: Int? = null
        private var pressedStrokeColor: Int? = null
        private var focusedStrokeColor: Int? = null
        private var unCheckableStrokeColor: Int? = null
        private var unCheckedStrokeColor: Int? = null
        private var unEnabledStrokeColor: Int? = null
        private var unSelectedStrokeColor: Int? = null
        private var unPressedStrokeColor: Int? = null
        private var unFocusedStrokeColor: Int? = null
        private var checkableSolidColor: Int? = null
        private var checkedSolidColor: Int? = null
        private var enabledSolidColor: Int? = null
        private var selectedSolidColor: Int? = null
        private var pressedSolidColor: Int? = null
        private var focusedSolidColor: Int? = null
        private var unCheckableSolidColor: Int? = null
        private var unCheckedSolidColor: Int? = null
        private var unEnabledSolidColor: Int? = null
        private var unSelectedSolidColor: Int? = null
        private var unPressedSolidColor: Int? = null
        private var unFocusedSolidColor: Int? = null
        private var checkableDrawable: Drawable? = null
        private var checkedDrawable: Drawable? = null
        private var enabledDrawable: Drawable? = null
        private var selectedDrawable: Drawable? = null
        private var pressedDrawable: Drawable? = null
        private var focusedDrawable: Drawable? = null
        private var focusedHovered: Drawable? = null
        private var focusedActivated: Drawable? = null
        private var unCheckableDrawable: Drawable? = null
        private var unCheckedDrawable: Drawable? = null
        private var unEnabledDrawable: Drawable? = null
        private var unSelectedDrawable: Drawable? = null
        private var unPressedDrawable: Drawable? = null
        private var unFocusedDrawable: Drawable? = null
        private var unFocusedHovered: Drawable? = null
        private var unFocusedActivated: Drawable? = null
        private var checkableTextColor: Int? = null
        private var checkedTextColor: Int? = null
        private var enabledTextColor: Int? = null
        private var selectedTextColor: Int? = null
        private var pressedTextColor: Int? = null
        private var focusedTextColor: Int? = null
        private var unCheckableTextColor: Int? = null
        private var unCheckedTextColor: Int? = null
        private var unEnabledTextColor: Int? = null
        private var unSelectedTextColor: Int? = null
        private var unPressedTextColor: Int? = null
        private var unFocusedTextColor: Int? = null
        private var textColorCount = 0
        private var hasSelectDrawable = false
        fun setShape(shape: Shape): Builder {
            this.shape = shape
            return this
        }

        fun setSolidColor(solidColor: Int): Builder {
            this.solidColor = solidColor
            return this
        }

        fun setCornersRadius(cornersRadius: Float): Builder {
            this.cornersRadius = cornersRadius
            return this
        }

        fun setCornersRadius(cornersBottomLeftRadius: Float, cornersBottomRightRadius: Float, cornersTopLeftRadius: Float, cornersTopRightRadius: Float): Builder {
            this.cornersBottomLeftRadius = cornersBottomLeftRadius
            this.cornersBottomRightRadius = cornersBottomRightRadius
            this.cornersTopLeftRadius = cornersTopLeftRadius
            this.cornersTopRightRadius = cornersTopRightRadius
            return this
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        fun setGradientAngle(gradientAngle: Int): Builder {
            this.gradientAngle = gradientAngle
            return this
        }

        fun setGradientCenterXY(gradientCenterX: Float, gradientCenterY: Float): Builder {
            this.gradientCenterX = gradientCenterX
            this.gradientCenterY = gradientCenterY
            return this
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        fun setGradientColor(startColor: Int, endColor: Int): Builder {
            gradientStartColor = startColor
            gradientEndColor = endColor
            return this
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        fun setGradientColor(startColor: Int, centerColor: Int, endColor: Int): Builder {
            gradientStartColor = startColor
            gradientCenterColor = centerColor
            gradientEndColor = endColor
            return this
        }

        fun setGradientRadius(gradientRadius: Float): Builder {
            this.gradientRadius = gradientRadius
            return this
        }

        fun setGradient(gradient: Gradient): Builder {
            this.gradient = gradient
            return this
        }

        fun setUseLevel(useLevel: Boolean): Builder {
            this.useLevel = useLevel
            return this
        }

        fun setPadding(paddingLeft: Float, paddingTop: Float, paddingRight: Float, paddingBottom: Float): Builder {
            padding.left = paddingLeft.toInt()
            padding.top = paddingTop.toInt()
            padding.right = paddingRight.toInt()
            padding.bottom = paddingBottom.toInt()
            return this
        }

        fun setSizeWidth(sizeWidth: Float): Builder {
            this.sizeWidth = sizeWidth
            return this
        }

        fun setSizeHeight(sizeHeight: Float): Builder {
            this.sizeHeight = sizeHeight
            return this
        }

        fun setStrokeWidth(strokeWidth: Float): Builder {
            this.strokeWidth = strokeWidth
            return this
        }

        fun setStrokeColor(strokeColor: Int): Builder {
            this.strokeColor = strokeColor
            return this
        }

        fun setStrokeDashWidth(strokeDashWidth: Float): Builder {
            this.strokeDashWidth = strokeDashWidth
            return this
        }

        fun setStrokeDashGap(strokeDashGap: Float): Builder {
            this.strokeDashGap = strokeDashGap
            return this
        }

        fun setRipple(rippleEnable: Boolean, rippleColor: Int): Builder {
            this.rippleEnable = rippleEnable
            this.rippleColor = rippleColor
            return this
        }

        fun setCheckableStrokeColor(checkableStrokeColor: Int, unCheckableStrokeColor: Int): Builder {
            this.checkableStrokeColor = checkableStrokeColor
            this.unCheckableStrokeColor = unCheckableStrokeColor
            return this
        }

        fun setCheckedStrokeColor(checkedStrokeColor: Int, unCheckedStrokeColor: Int): Builder {
            this.checkedStrokeColor = checkedStrokeColor
            this.unCheckedStrokeColor = unCheckedStrokeColor
            return this
        }

        fun setEnabledStrokeColor(enabledStrokeColor: Int, unEnabledStrokeColor: Int): Builder {
            this.enabledStrokeColor = enabledStrokeColor
            this.unEnabledStrokeColor = unEnabledStrokeColor
            return this
        }

        fun setSelectedStrokeColor(selectedStrokeColor: Int, unSelectedStrokeColor: Int): Builder {
            this.selectedStrokeColor = selectedStrokeColor
            this.unSelectedStrokeColor = unSelectedStrokeColor
            return this
        }

        fun setPressedStrokeColor(pressedStrokeColor: Int, unPressedStrokeColor: Int): Builder {
            this.pressedStrokeColor = pressedStrokeColor
            this.unPressedStrokeColor = unPressedStrokeColor
            return this
        }

        fun setFocusedStrokeColor(focusedStrokeColor: Int, unFocusedStrokeColor: Int): Builder {
            this.focusedStrokeColor = focusedStrokeColor
            this.unFocusedStrokeColor = unFocusedStrokeColor
            return this
        }

        fun setCheckableSolidColor(checkableSolidColor: Int, unCheckableSolidColor: Int): Builder {
            this.checkableSolidColor = checkableSolidColor
            this.unCheckableSolidColor = unCheckableSolidColor
            return this
        }

        fun setCheckedSolidColor(checkedSolidColor: Int, unCheckedSolidColor: Int): Builder {
            this.checkedSolidColor = checkedSolidColor
            this.unCheckedSolidColor = unCheckedSolidColor
            return this
        }

        fun setEnabledSolidColor(enabledSolidColor: Int, unEnabledSolidColor: Int): Builder {
            this.enabledSolidColor = enabledSolidColor
            this.unEnabledSolidColor = unEnabledSolidColor
            return this
        }

        fun setSelectedSolidColor(selectedSolidColor: Int, unSelectedSolidColor: Int): Builder {
            this.selectedSolidColor = selectedSolidColor
            this.unSelectedSolidColor = unSelectedSolidColor
            return this
        }

        fun setPressedSolidColor(pressedSolidColor: Int, unPressedSolidColor: Int): Builder {
            this.pressedSolidColor = pressedSolidColor
            this.unPressedSolidColor = unPressedSolidColor
            return this
        }

        fun setFocusedSolidColor(focusedSolidColor: Int, unFocusedSolidColor: Int): Builder {
            this.focusedSolidColor = focusedSolidColor
            this.unFocusedSolidColor = unFocusedSolidColor
            return this
        }

        fun setCheckableDrawable(checkableDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.checkableDrawable = checkableDrawable
            return this
        }

        fun setCheckedDrawable(checkedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.checkedDrawable = checkedDrawable
            return this
        }

        fun setEnabledDrawable(enabledDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.enabledDrawable = enabledDrawable
            return this
        }

        fun setSelectedDrawable(selectedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.selectedDrawable = selectedDrawable
            return this
        }

        fun setPressedDrawable(pressedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.pressedDrawable = pressedDrawable
            return this
        }

        fun setFocusedDrawable(focusedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.focusedDrawable = focusedDrawable
            return this
        }

        fun setFocusedHovered(focusedHovered: Drawable?): Builder {
            hasSelectDrawable = true
            this.focusedHovered = focusedHovered
            return this
        }

        fun setFocusedActivated(focusedActivated: Drawable?): Builder {
            hasSelectDrawable = true
            this.focusedActivated = focusedActivated
            return this
        }

        fun setUnCheckableDrawable(unCheckableDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.unCheckableDrawable = unCheckableDrawable
            return this
        }

        fun setUnCheckedDrawable(unCheckedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.unCheckedDrawable = unCheckedDrawable
            return this
        }

        fun setUnEnabledDrawable(unEnabledDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.unEnabledDrawable = unEnabledDrawable
            return this
        }

        fun setUnSelectedDrawable(unSelectedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.unSelectedDrawable = unSelectedDrawable
            return this
        }

        fun setUnPressedDrawable(unPressedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            this.unPressedDrawable = unPressedDrawable
            return this
        }

        fun setUnFocusedDrawable(unFocusedDrawable: Drawable?): Builder {
            hasSelectDrawable = true
            hasSelectDrawable = true
            this.unFocusedDrawable = unFocusedDrawable
            return this
        }

        fun setUnFocusedHovered(unFocusedHovered: Drawable?): Builder {
            hasSelectDrawable = true
            this.unFocusedHovered = unFocusedHovered
            return this
        }

        fun setUnFocusedActivated(unFocusedActivated: Drawable?): Builder {
            hasSelectDrawable = true
            this.unFocusedActivated = unFocusedActivated
            return this
        }

        fun setCheckableTextColor(checkableTextColor: Int): Builder {
            this.checkableTextColor = checkableTextColor
            textColorCount++
            return this
        }

        fun setCheckedTextColor(checkedTextColor: Int): Builder {
            this.checkedTextColor = checkedTextColor
            textColorCount++
            return this
        }

        fun setEnabledTextColor(enabledTextColor: Int): Builder {
            this.enabledTextColor = enabledTextColor
            textColorCount++
            return this
        }

        fun setSelectedTextColor(selectedTextColor: Int): Builder {
            this.selectedTextColor = selectedTextColor
            textColorCount++
            return this
        }

        fun setPressedTextColor(pressedTextColor: Int): Builder {
            this.pressedTextColor = pressedTextColor
            textColorCount++
            return this
        }

        fun setFocusedTextColor(focusedTextColor: Int): Builder {
            this.focusedTextColor = focusedTextColor
            textColorCount++
            return this
        }

        fun setUnCheckableTextColor(unCheckableTextColor: Int): Builder {
            this.unCheckableTextColor = unCheckableTextColor
            textColorCount++
            return this
        }

        fun setUnCheckedTextColor(unCheckedTextColor: Int): Builder {
            this.unCheckedTextColor = unCheckedTextColor
            textColorCount++
            return this
        }

        fun setUnEnabledTextColor(unEnabledTextColor: Int): Builder {
            this.unEnabledTextColor = unEnabledTextColor
            textColorCount++
            return this
        }

        fun setUnSelectedTextColor(unSelectedTextColor: Int): Builder {
            this.unSelectedTextColor = unSelectedTextColor
            textColorCount++
            return this
        }

        fun setUnPressedTextColor(unPressedTextColor: Int): Builder {
            this.unPressedTextColor = unPressedTextColor
            textColorCount++
            return this
        }

        fun setUnFocusedTextColor(unFocusedTextColor: Int): Builder {
            this.unFocusedTextColor = unFocusedTextColor
            textColorCount++
            return this
        }

        fun build(): Drawable? {
            var drawable: GradientDrawable? = null
            var stateListDrawable: StateListDrawable? = null
            if (hasSelectDrawable) {
                stateListDrawable = stateListDrawable
            } else {
                drawable = gradientDrawable
            }
            return if (rippleEnable && rippleColor != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val contentDrawable = stateListDrawable ?: drawable
                    RippleDrawable(ColorStateList.valueOf(rippleColor!!), contentDrawable, contentDrawable)
                } else {
                    val resultDrawable = StateListDrawable()
                    val unPressDrawable = gradientDrawable
                    unPressDrawable.setColor(rippleColor!!)
                    resultDrawable.addState(intArrayOf(-R.attr.state_pressed), drawable)
                    resultDrawable.addState(intArrayOf(R.attr.state_pressed), unPressDrawable)
                    resultDrawable
                }
            } else drawable ?: stateListDrawable
        }

        fun buildTextColor(): ColorStateList? {
            return if (textColorCount > 0) {
                colorStateList
            } else {
                null
            }
        }

        private val colorStateList: ColorStateList
            private get() {
                val states = arrayOfNulls<IntArray>(textColorCount)
                val colors = IntArray(textColorCount)
                var index = 0
                if (checkableTextColor != null) {
                    states[index] = intArrayOf(R.attr.state_checkable)
                    checkableTextColor?.let {
                        colors[index] = it
                        index++
                    }

                }
                if (unCheckableTextColor != null) {
                    states[index] = intArrayOf(-R.attr.state_checkable)
                    unCheckableTextColor?.let {
                        colors[index] = it
                        index++
                    }

                }
                if (checkedTextColor != null) {
                    states[index] = intArrayOf(R.attr.state_checked)
                    checkedTextColor?.let {
                        colors[index] = it
                        index++
                    }
                }
                if (unCheckedTextColor != null) {
                    states[index] = intArrayOf(-R.attr.state_checked)
                    unCheckedTextColor?.let {
                        colors[index] = it
                        index++
                    }

                }
                if (enabledTextColor != null) {
                    states[index] = intArrayOf(R.attr.state_enabled)
                    enabledTextColor?.let {
                        colors[index] = it
                        index++
                    }
                }
                if (unEnabledTextColor != null) {
                    states[index] = intArrayOf(-R.attr.state_enabled)
                    unEnabledTextColor?.let {
                        colors[index] = it
                        index++
                    }
                }
                if (selectedTextColor != null) {
                    states[index] = intArrayOf(R.attr.state_selected)
                    selectedTextColor?.let {
                        colors[index] = it
                        index++
                    }

                }
                if (unSelectedTextColor != null) {
                    states[index] = intArrayOf(-R.attr.state_selected)
                    unSelectedTextColor?.let {
                        colors[index] = it
                        index++
                    }
                }
                if (pressedTextColor != null) {
                    states[index] = intArrayOf(R.attr.state_pressed)
                    pressedTextColor?.let {
                        colors[index] = it
                        index++
                    }

                }
                if (unPressedTextColor != null) {
                    states[index] = intArrayOf(-R.attr.state_pressed)
                    unPressedTextColor?.let {
                        colors[index] = it
                        index++
                    }
                }
                if (focusedTextColor != null) {
                    states[index] = intArrayOf(R.attr.state_focused)
                    focusedTextColor?.let {
                        colors[index] = it
                        index++
                    }

                }
                if (unFocusedTextColor != null) {
                    states[index] = intArrayOf(-R.attr.state_focused)
                    unFocusedTextColor?.let {
                        colors[index] = it
                    }
                }
                return ColorStateList(states, colors)
            }
        private val stateListDrawable: StateListDrawable?
            private get() {
                var stateListDrawable: StateListDrawable? = null
                if (checkableDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_checkable), checkableDrawable)
                }
                if (unCheckableDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_checkable), unCheckableDrawable)
                }
                if (checkedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_checked), checkedDrawable)
                }
                if (unCheckedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_checked), unCheckedDrawable)
                }
                if (enabledDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_enabled), enabledDrawable)
                }
                if (unEnabledDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_enabled), unEnabledDrawable)
                }
                if (selectedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_selected), selectedDrawable)
                }
                if (unSelectedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_selected), unSelectedDrawable)
                }
                if (pressedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_pressed), pressedDrawable)
                }
                if (unPressedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_pressed), unPressedDrawable)
                }
                if (focusedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_focused), focusedDrawable)
                }
                if (unFocusedDrawable != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_focused), unFocusedDrawable)
                }
                if (focusedHovered != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_hovered), focusedHovered)
                }
                if (unFocusedHovered != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_hovered), unFocusedHovered)
                }
                if (focusedActivated != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(R.attr.state_activated), focusedActivated)
                }
                if (unFocusedActivated != null) {
                    stateListDrawable = getStateListDrawable(stateListDrawable)
                    stateListDrawable.addState(intArrayOf(-R.attr.state_activated), unFocusedActivated)
                }
                return stateListDrawable
            }
        private val gradientDrawable: GradientDrawable
            private get() {
                val drawable = GradientDrawable()
                drawable.shape = shape.value
                if (cornersRadius != null) {
                    drawable.cornerRadius = cornersRadius!!
                }
                if (cornersBottomLeftRadius != null && cornersBottomRightRadius != null && cornersTopLeftRadius != null && cornersTopRightRadius != null) {
                    val cornerRadius = FloatArray(8)
                    cornerRadius[0] = cornersTopLeftRadius ?:0.0f
                    cornerRadius[1] = cornersTopLeftRadius?:0.0f
                    cornerRadius[2] = cornersTopRightRadius?:0.0f
                    cornerRadius[3] = cornersTopRightRadius?:0.0f
                    cornerRadius[4] = cornersBottomRightRadius?:0.0f
                    cornerRadius[5] = cornersBottomRightRadius?:0.0f
                    cornerRadius[6] = cornersBottomLeftRadius?:0.0f
                    cornerRadius[7] = cornersBottomLeftRadius?:0.0f
                    drawable.cornerRadii = cornerRadius
                }
                if (gradient == Gradient.Linear && gradientAngle != -1) {
                    gradientAngle %= 360
                    if (gradientAngle % 45 == 0) {
                        var mOrientation = GradientDrawable.Orientation.LEFT_RIGHT
                        when (gradientAngle) {
                            0 -> mOrientation = GradientDrawable.Orientation.LEFT_RIGHT
                            45 -> mOrientation = GradientDrawable.Orientation.BL_TR
                            90 -> mOrientation = GradientDrawable.Orientation.BOTTOM_TOP
                            135 -> mOrientation = GradientDrawable.Orientation.BR_TL
                            180 -> mOrientation = GradientDrawable.Orientation.RIGHT_LEFT
                            225 -> mOrientation = GradientDrawable.Orientation.TR_BL
                            270 -> mOrientation = GradientDrawable.Orientation.TOP_BOTTOM
                            315 -> mOrientation = GradientDrawable.Orientation.TL_BR
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            drawable.orientation = mOrientation
                        }
                    }
                }
                if (gradientCenterX != null && gradientCenterY != null) {
                    drawable.setGradientCenter(gradientCenterX!!, gradientCenterY!!)
                }
                if (gradientStartColor != null && gradientEndColor != null) {
                    val colors: IntArray
                    if (gradientCenterColor != null) {
                        colors = IntArray(3)
                        colors[0] = gradientStartColor ?:0
                        colors[1] = gradientCenterColor?:0
                        colors[2] = gradientEndColor?:0
                    } else {
                        colors = IntArray(2)
                        colors[0] = gradientStartColor?:0
                        colors[1] = gradientEndColor?:0
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        drawable.colors = colors
                    }
                }
                if (gradientRadius != null) {
                    drawable.gradientRadius = gradientRadius!!
                }
                drawable.gradientType = gradient.value
                drawable.useLevel = useLevel
                if (!padding.isEmpty) {
                    try {
                        val paddingField = drawable.javaClass.getField("mPadding")
                        paddingField.isAccessible = true
                        paddingField[drawable] = padding
                    } catch (e: NoSuchFieldException) {
                        e.printStackTrace()
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }
                }
                if (sizeWidth != null && sizeHeight != null) {
                    drawable.setSize(sizeWidth!!.toInt(), sizeHeight!!.toInt())
                }
                if (strokeWidth != null && strokeWidth!! > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        var start = 0
                        var stateList: ArrayList<Int>? = ArrayList()
                        var colorList: ArrayList<Int>? = ArrayList()
                        if (pressedStrokeColor != null && unPressedStrokeColor != null) {
                            stateList!!.add(R.attr.state_pressed)
                            stateList.add(-R.attr.state_pressed)
                            colorList!!.add(pressedStrokeColor!!)
                            colorList.add(unPressedStrokeColor!!)
                        }
                        if (checkableStrokeColor != null && unCheckableStrokeColor != null) {
                            stateList!!.add(R.attr.state_checkable)
                            stateList.add(-R.attr.state_checkable)
                            colorList!!.add(checkableStrokeColor!!)
                            colorList.add(unCheckableStrokeColor!!)
                        }
                        if (checkedStrokeColor != null && unCheckedStrokeColor != null) {
                            stateList!!.add(R.attr.state_checked)
                            stateList.add(-R.attr.state_checked)
                            colorList!!.add(checkedStrokeColor!!)
                            colorList.add(unCheckedStrokeColor!!)
                        }
                        if (enabledStrokeColor != null && unEnabledStrokeColor != null) {
                            stateList!!.add(R.attr.state_enabled)
                            stateList.add(-R.attr.state_enabled)
                            colorList!!.add(enabledStrokeColor!!)
                            colorList.add(unEnabledStrokeColor!!)
                        }
                        if (selectedStrokeColor != null && unSelectedStrokeColor != null) {
                            stateList!!.add(R.attr.state_selected)
                            stateList.add(-R.attr.state_selected)
                            colorList!!.add(selectedStrokeColor!!)
                            colorList.add(unSelectedStrokeColor!!)
                        }
                        if (focusedStrokeColor != null && unFocusedStrokeColor != null) {
                            stateList!!.add(R.attr.state_focused)
                            stateList.add(-R.attr.state_focused)
                            colorList!!.add(focusedStrokeColor!!)
                            colorList.add(unFocusedStrokeColor!!)
                        }
                        if (stateList!!.size > 0) {
                            val state = arrayOfNulls<IntArray>(stateList.size)
                            val color = IntArray(stateList.size)
                            for (iState in stateList) {
                                state[start] = intArrayOf(iState)
                                color[start] = colorList!![start]
                                start++
                            }
                            val colorStateList = ColorStateList(state, color)
                            drawable.setStroke(strokeWidth!!.toInt(), colorStateList, strokeDashWidth, strokeDashGap)
                        } else if (strokeColor != null) {
                            drawable.setStroke(strokeWidth!!.toInt(), strokeColor!!, strokeDashWidth, strokeDashGap)
                        }
                        stateList = null
                        colorList = null
                    } else if (strokeColor != null) {
                        drawable.setStroke(strokeWidth!!.toInt(), strokeColor!!, strokeDashWidth, strokeDashGap)
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    var start = 0
                    var stateList: ArrayList<Int>? = ArrayList()
                    var colorList: ArrayList<Int>? = ArrayList()
                    if (pressedSolidColor != null && unPressedSolidColor != null) {
                        stateList!!.add(R.attr.state_pressed)
                        stateList.add(-R.attr.state_pressed)
                        colorList!!.add(pressedSolidColor!!)
                        colorList.add(unPressedSolidColor!!)
                    }
                    if (checkableSolidColor != null && unCheckableSolidColor != null) {
                        stateList!!.add(R.attr.state_checkable)
                        stateList.add(-R.attr.state_checkable)
                        colorList!!.add(checkableSolidColor!!)
                        colorList.add(unCheckableSolidColor!!)
                    }
                    if (checkedSolidColor != null && unCheckedSolidColor != null) {
                        stateList!!.add(R.attr.state_checked)
                        stateList.add(-R.attr.state_checked)
                        colorList!!.add(checkedSolidColor!!)
                        colorList.add(unCheckedSolidColor!!)
                    }
                    if (enabledSolidColor != null && unEnabledSolidColor != null) {
                        stateList!!.add(R.attr.state_enabled)
                        stateList.add(-R.attr.state_enabled)
                        colorList!!.add(enabledSolidColor!!)
                        colorList.add(unEnabledSolidColor!!)
                    }
                    if (selectedSolidColor != null && unSelectedSolidColor != null) {
                        stateList!!.add(R.attr.state_selected)
                        stateList.add(-R.attr.state_selected)
                        colorList!!.add(selectedSolidColor!!)
                        colorList.add(unSelectedSolidColor!!)
                    }
                    if (focusedSolidColor != null && unFocusedSolidColor != null) {
                        stateList!!.add(R.attr.state_focused)
                        stateList.add(-R.attr.state_focused)
                        colorList!!.add(focusedSolidColor!!)
                        colorList.add(unFocusedSolidColor!!)
                    }
                    if (stateList!!.size > 0) {
                        val state = arrayOfNulls<IntArray>(stateList.size)
                        val color = IntArray(stateList.size)
                        for (iState in stateList) {
                            state[start] = intArrayOf(iState)
                            color[start] = colorList!![start]
                            start++
                        }
                        val colorStateList = ColorStateList(state, color)
                        drawable.color = colorStateList
                    } else if (solidColor != null) {
                        drawable.setColor(solidColor!!)
                    }
                    stateList = null
                    colorList = null
                } else if (solidColor != null) {
                    drawable.setColor(solidColor!!)
                }
                return drawable
            }

        fun getStateListDrawable(stateListDrawable: StateListDrawable?): StateListDrawable {
            var stateListDrawable = stateListDrawable
            if (stateListDrawable == null) {
                stateListDrawable = StateListDrawable()
            }
            return stateListDrawable
        }
    }
}