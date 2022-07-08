package com.coding.qzy.baselibrary.utils.background.drawable

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import org.xmlpull.v1.XmlPullParserException

/**
 * Created by xiaoqi on 2018/9/12
 */
object DrawableFactory {
    //获取shape属性的drawable
    @JvmStatic
    @Throws(XmlPullParserException::class)
    fun getDrawable(typedArray: TypedArray?): GradientDrawable {
        return GradientDrawableCreator(typedArray!!).create() as GradientDrawable
    }

    //获取selector属性的drawable
    @JvmStatic
    @Throws(Exception::class)
    fun getSelectorDrawable(typedArray: TypedArray?, selectorTa: TypedArray?): StateListDrawable {
        return SelectorDrawableCreator(typedArray!!, selectorTa!!).create() as StateListDrawable
    }

    //获取button 属性的drawable
    @JvmStatic
    @Throws(Exception::class)
    fun getButtonDrawable(typedArray: TypedArray?, buttonTa: TypedArray?): StateListDrawable {
        return ButtonDrawableCreator(typedArray!!, buttonTa!!).create() as StateListDrawable
    }

    //获取text selector属性关于text的color
    @JvmStatic
    fun getTextSelectorColor(textTa: TypedArray?): ColorStateList {
        return ColorStateCreator(textTa!!).create()
    }

    //适配早期版本的属性
    @JvmStatic
    @Throws(Exception::class)
    fun getPressDrawable(drawable: GradientDrawable?, typedArray: TypedArray?, pressTa: TypedArray?): StateListDrawable {
        return PressDrawableCreator(drawable!!, typedArray!!, pressTa!!).create() as StateListDrawable
    }

    //获取AnimationDrawable属性的drawable
    @JvmStatic
    @Throws(Exception::class)
    fun getAnimationDrawable(animTa: TypedArray?): AnimationDrawable {
        return AnimationDrawableCreator(animTa!!).create() as AnimationDrawable
    }
}