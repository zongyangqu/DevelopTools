package com.quzy.coding.util.widget

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation

/**
 * CreateDate:2021/10/20 11:39
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.widget
 * @Description:
 */
class CouponShakeAnimation(view: View?) {

    val targetView = view
    private var repeatCount: Int = 0
    private var rotateAnimation: RotateAnimation? = null

    fun buildRotateAnimation() {
        if (rotateAnimation == null) {
            rotateAnimation = RotateAnimation(7f, -7f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1f)
            rotateAnimation?.duration = 70
            rotateAnimation?.repeatCount = 2
            rotateAnimation?.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    targetView?.postDelayed(Runnable {
                        if (targetView.visibility == View.VISIBLE) {
                            targetView.startAnimation(rotateAnimation)
                        }
                    }, 1500)
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
        }
        targetView?.animation = rotateAnimation
        rotateAnimation?.start()
    }

    fun stopAnimation() {
        targetView?.visibility = View.GONE
        // rotateAnimation?.cancel()
    }
}