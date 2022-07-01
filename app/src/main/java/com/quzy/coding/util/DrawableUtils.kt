package com.quzy.coding.util

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.coding.qzy.baselibrary.utils.extend.dp
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication

/**
 * CreateDate:2021/10/28 10:12
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
object DrawableUtils {

    private val context by lazy {
        BaseApplication.appContext
    }

    /**
     * @method createCornerDrawable
     * @description 通过代码创建drawable
     * @date: 2020/12/7 6:04 PM
     * @author: ZhaoXuan.Zeng
     * @param [leftTop, rightTop, rightBottom, leftBottom, bgColor]
     * @return
     */
    fun createCornerDrawable(
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float,
        bgColor: Int
    ): Drawable {
        val drawable = GradientDrawable()
        drawable.setColor(ContextCompat.getColor(context, bgColor))
        val corners = floatArrayOf(
            leftTop.dp,
            leftTop.dp,
            rightTop.dp,
            rightTop.dp,
            rightBottom.dp,
            rightBottom.dp,
            leftBottom.dp,
            leftBottom.dp
        )
        drawable.cornerRadii = corners
        return drawable
    }

    /**
     * @description 根据单一背景色创建圆角背景，圆角自定义，单位px
     *
     * */
    fun createDrawble(
        color: Int,
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float
    ): Drawable? {
        return createDrawble(intArrayOf(color, color), leftTop, rightTop, rightBottom, leftBottom)
    }

    /**
     * @description 根据单一背景色创建圆角背景，圆角统一，单位px
     *
     * */
    fun createDrawble(color: Int, corner: Float): Drawable? {
        return createDrawble(intArrayOf(color, color), corner)
    }

    /**
     * @description 根据单一背景色创建圆角背景，上下圆角，单位px
     *
     * */
    fun createDrawbleTopBottom(color: Int, top: Float, bottom: Float): Drawable? {
        return createDrawbleTopBottom(intArrayOf(color, color), top, bottom)
    }

    /**
     * @description 根据单一背景色创建圆角背景，左右圆角，单位px
     *
     * */
    fun createDrawbleLeftRight(color: Int, left: Float, right: Float): Drawable? {
        return createDrawbleLeftRight(intArrayOf(color, color), left, right)
    }

    /**
     * @description 根据渐变背景色创建圆角背景，圆角统一，单位px
     *
     * */
    fun createDrawble(colors: IntArray, corner: Float): Drawable? {
        return createDrawble(colors, corner, corner, corner, corner)
    }

    /**
     * @description 根据渐变背景色创建圆角背景，上下圆角，单位px
     *
     * */
    fun createDrawbleTopBottom(colors: IntArray, top: Float, bottom: Float): Drawable? {
        return createDrawble(colors, top, top, bottom, bottom)
    }

    /**
     * @description 根据渐变背景色创建圆角背景，左右圆角，单位px
     *
     * */
    fun createDrawbleLeftRight(colors: IntArray, left: Float, right: Float): Drawable? {
        return createDrawble(colors, left, right, right, left)
    }

    /**
     * @method createDrawble
     * @description 根据渐变背景色创建圆角背景
     * @param colors 渐变色起始和终止的颜色值，二元数组
     * @param leftTop 左上角圆角，单位px
     * @param rightTop 右上角圆角，单位px
     * @param rightBottom 右下角圆角，单位px
     * @param leftBottom 左下角圆角，单位px
     * */
    fun createDrawble(
        colors: IntArray,
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float,
        orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT
    ): Drawable? {
        val drawable = GradientDrawable(orientation, colors)
        val corners = floatArrayOf(
            leftTop,
            leftTop,
            rightTop,
            rightTop,
            rightBottom,
            rightBottom,
            leftBottom,
            leftBottom
        )
        drawable.cornerRadii = corners
        return drawable
    }

    /**
     * @method createDrawble
     * @description 根据渐变背景色创建圆角背景
     * @param colors 渐变色起始和终止的颜色值，二元数组
     * @param allinLeft 若true 则所有半径都取leftTopOrAll
     * @param leftTopOrAll 左上角圆角，或代表全部半径，单位px
     * @param rightTop 右上角圆角，单位px
     * @param rightBottom 右下角圆角，单位px
     * @param leftBottom 左下角圆角，单位px
     * */
    fun createDrawable(
        allinLeft: Boolean = false,
        leftTopOrAll: Float = 0f,
        rightTop: Float = 0f,
        rightBottom: Float = 0f,
        leftBottom: Float = 0f,
        orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT,
        vararg colors: Int
    ): Drawable {
        var rt = rightTop
        var rb = rightBottom
        var lb = leftBottom
        if (allinLeft) {
            rt = leftTopOrAll
            rb = leftTopOrAll
            lb = leftTopOrAll
        }
        val drawable = GradientDrawable(orientation, colors)
        val corners = floatArrayOf(leftTopOrAll, leftTopOrAll, rt, rt, rb, rb, lb, lb)
        drawable.cornerRadii = corners
        return drawable
    }

    /**
     * @method createCornerLeftRightDrawable
     * @description 通过代码创建渐变色的drawable
     * @date: 2020/12/25 10:38 AM
     * @author: ZhaoXuan.Zeng
     * @param [leftTop, rightTop, rightBottom, leftBottom, startColor, endColor]
     * @return
     */
    fun createCornerLeftRightDrawable(
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float,
        startColor: Int,
        endColor: Int
    ): Drawable {
        val colors = intArrayOf(startColor, endColor)
        val drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colors)
        val corners = floatArrayOf(
            leftTop.dp,
            leftTop.dp,
            rightTop.dp,
            rightTop.dp,
            rightBottom.dp,
            rightBottom.dp,
            leftBottom.dp,
            leftBottom.dp
        )
        drawable.cornerRadii = corners
        return drawable
    }

    fun createCornerTopLeftToBottomRightDrawable(
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float,
        startColor: Int,
        endColor: Int
    ): Drawable {
        val colors = intArrayOf(startColor, endColor)
        val drawable = GradientDrawable(GradientDrawable.Orientation.TL_BR, colors)
        val corners = floatArrayOf(
            leftTop.dp,
            leftTop.dp,
            rightTop.dp,
            rightTop.dp,
            rightBottom.dp,
            rightBottom.dp,
            leftBottom.dp,
            leftBottom.dp
        )
        drawable.cornerRadii = corners
        return drawable
    }

    /**
     * @method createCornerTopBottomDrawable
     * @description 通过代码创建渐变色的drawable
     * @date: 2020/12/25 10:38 AM
     * @author: ZhaoXuan.Zeng
     * @param [leftTop, rightTop, rightBottom, leftBottom, startColor, endColor]
     * @return
     */
    fun createCornerTopBottomDrawable(
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float,
        startColor: Int,
        endColor: Int
    ): Drawable {
        val colors = intArrayOf(startColor, endColor)
        val drawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
        val corners = floatArrayOf(
            leftTop.dp,
            leftTop.dp,
            rightTop.dp,
            rightTop.dp,
            rightBottom.dp,
            rightBottom.dp,
            leftBottom.dp,
            leftBottom.dp
        )
        drawable.cornerRadii = corners
        return drawable
    }

    /**
     * @method createCornerTopBottomDrawable
     * @description 通过代码创建渐变色的drawable
     * @date: 2020/12/25 10:38 AM
     * @author: ZhaoXuan.Zeng
     * @param [leftTop, rightTop, rightBottom, leftBottom, colors]
     * @return
     */
    fun createCornerTopBottomDrawable(
        leftTop: Float,
        rightTop: Float,
        rightBottom: Float,
        leftBottom: Float,
        colors: IntArray
    ): Drawable {
        val drawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
        val corners = floatArrayOf(
            leftTop.dp,
            leftTop.dp,
            rightTop.dp,
            rightTop.dp,
            rightBottom.dp,
            rightBottom.dp,
            leftBottom.dp,
            leftBottom.dp
        )
        drawable.cornerRadii = corners
        return drawable
    }

    /**
     * @method tintDrawable
     * @description 对图片着色
     * @date: 2021/3/2 5:35 PM
     * @author: ZhaoXuan.Zeng
     * @param [drawable, color]
     * @return
     */
    fun tintDrawable(drawable: Int, color: Int): Drawable? {
        val img = ResourcesCompat.getDrawable(context.resources, drawable, context.theme)
        return if (img == null) {
            img
        } else {
            val tintIcon = DrawableCompat.wrap(img).mutate()
            DrawableCompat.setTint(tintIcon, color)
            tintIcon
        }
    }

    /**
     * 起始颜色（当前position的背景色），目标颜色（要切换到的下一张轮播图的背景色），rate（当前变化的比例）
     */
    fun calculateColor(startColor: Int, endColor: Int, rate: Float): Int {
        var startRed: Int = (startColor and 0xff0000) shr 16
        var startGreen: Int = (startColor and 0x00ff00) shr 8
        var startBlue: Int = (startColor and 0x0000ff)

        var endRed: Int = (endColor and 0xff0000) shr 16
        var endGreen: Int = (endColor and 0x00ff00) shr 8
        var endBlue: Int = (endColor and 0x0000ff)

        var resultRed: Int = (startRed + ((endRed - startRed) * rate)).toInt()
        var resultGreen: Int = (startGreen + ((endGreen - startGreen) * rate)).toInt()
        var resultBlue: Int = (startBlue + ((endBlue - startBlue) * rate)).toInt()
        return Color.rgb(resultRed, resultGreen, resultBlue)
    }

    fun parseColor(colorString: String): Int {
        var color: Int = 0
        color = try {
            Color.parseColor(colorString)
        } catch (e: Exception) {
            ResourceUtil.getColor(R.color.white)
        }
        return color
    }
}
