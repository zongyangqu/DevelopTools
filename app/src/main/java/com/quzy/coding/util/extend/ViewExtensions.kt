package com.quzy.coding.util.extend

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * CreateDate:2022/7/1 10:01
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
val View.ctx: Context
    get() = context

val View.parentView: ViewGroup
    get() = parent as ViewGroup
fun View.click(action: () -> Unit) = setOnClickListener {
    action()
}

fun ImageView.image(img: Int) = this.setImageResource(img)

fun ImageView.image(img: Drawable) = this.setImageDrawable(img)

fun View.bottomPadding(padding: Int) = setPadding(0, 0, 0, padding)

fun View.topPadding(padding: Int) = setPadding(0, padding, 0, 0)

fun View.rightPadding(padding: Int) = setPadding(0, 0, padding, 0)

fun View.leftPadding(padding: Int) = setPadding(padding, 0, 0, 0)

fun View.margins(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0) {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.setMargins(left, top, right, bottom)
    this.layoutParams = params
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.show(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.isShowing(): Boolean {
    return visibility == View.VISIBLE
}

fun View.isGone(): Boolean {
    return visibility == View.GONE
}

fun View.isHide(): Boolean {
    return visibility == View.INVISIBLE
}

fun View.showWithAlphaAnim(duration: Long = 300) {
    val animator = ObjectAnimator.ofFloat(this, "alpha", 0.0f, 1.0f)
    animator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationEnd(animation: Animator?) {}
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) = show()
    })
    animator.duration = duration
    animator.start()
}

fun View.goneWithAlphaAnim(duration: Long = 300) {
    val animator = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f)
    animator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationEnd(animation: Animator?) = gone()
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
    })
    animator.duration = duration
    animator.start()
}

fun View.showWithAnim(doubleScale: Boolean = false) {
    show()
    val animator = ObjectAnimator.ofFloat(this, "scaleY", 0.0f, 1.0f)
    animator.duration = 300
    animator.start()
}

// 触摸区域扩大
fun View.increaseTouchRange(range: Int = 10) {
    val scale = context.resources.displayMetrics.density
    val result = (range * scale + 0.5f).toInt()
    isEnabled = true
    if (this.parent is ViewGroup) {
        val group = this.parent as ViewGroup
        group.post {
            val rect = Rect()
            this.getHitRect(rect)
            rect.top -= result // increase top hit area
            rect.left -= result // increase left hit area
            rect.bottom += result // increase bottom hit area
            rect.right += result // increase right hit area
            group.touchDelegate = TouchDelegate(rect, this)
        }
    }
}

fun View.goneWithAnim() {
    val animator = ObjectAnimator.ofFloat(this, "scaleY", 1.0f, 0.0f)
    animator.duration = 300
    animator.start()
    animator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationEnd(animation: Animator?) = gone()
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
    })
}

inline fun <T : View> T.singleClick(time: Long = 500, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        val interval = currentTimeMillis - lastClickTime
        if (interval > time || interval < 0) {
            // 小于0是为了规避用户往前调手机时间
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}
// 兼容点击事件设置为this的情况
fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 500) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        val interval = currentTimeMillis - lastClickTime
        if (interval > time || interval < 0) {
            // 小于0是为了规避用户往前调手机时间
            lastClickTime = currentTimeMillis
            onClickListener.onClick(this)
        }
    }
}

var <T : View> T.lastClickTime: Long
    set(value) = setTag(Int.MAX_VALUE, value)
    get() = getTag(Int.MAX_VALUE) as? Long ?: 0
