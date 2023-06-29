package com.quzy.coding.base

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import android.widget.FrameLayout
import com.airbnb.lottie.LottieAnimationView
import com.quzy.coding.R
import com.quzy.coding.ui.widget.YHSkeletonScreen
import com.quzy.coding.util.extend.click
import com.quzy.coding.util.extend.show
import kotlinx.android.synthetic.main.view_new_global_loading.view.skeleton_replaceview

/**
 * CreateDate:2023/6/16 16:34
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
open class NewLoadingView : FrameLayout {
    private var display: NewLocalDisplay = AwaitNewLocalDisplay()
    private val stubView: ViewStub =
        LayoutInflater.from(context).inflate(R.layout.view_new_global_loading, this, true)
            .findViewById(R.id.loading_viewstub)
    private lateinit var progressView: LottieAnimationView
    private var enableSkeleton: Boolean = false
    var skeleton: YHSkeletonScreen.Builder? = null
    var screen: YHSkeletonScreen? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun skeleton(layout: Int) {
        enableSkeleton = true

        skeleton = YHSkeletonScreen.Builder(skeleton_replaceview).load(layout)
            .color(R.color.skeletonColor) // Skeleton.bind(skeleton_replaceview).load(layout).color(R.color.subWhiteColor)
        click { } // 增加一个点击事件，防止点击到底部的原始数据
    }

    fun hideSkeleton() {
        enableSkeleton = false
    }

    override fun setVisibility(visibility: Int) {
        setVisiblility(visibility, true)
    }

    fun setVisiblility(visibility: Int, immediately: Boolean) {
        if (visibility == View.VISIBLE) {
            super.setVisibility(visibility)
            if (enableSkeleton) {
                screen = skeleton?.show()
                return
            }
            display = LoadingNewLocalDisplay()
            val time = if (immediately) 0L else 1200L
            postDelayed(
                {
                    if (stubView.parent != null) {
                        progressView =
                            stubView.inflate()
                                .findViewById(R.id.global_loading_img) as LottieAnimationView
                    }
                    display.show(this, progressView)
                },
                time
            )
        } else {
            screen?.hide()
            display.hide()
            super.setVisibility(View.GONE)
            display = AwaitNewLocalDisplay()
        }
    }
}

abstract class NewLocalDisplay {
    var animator: ObjectAnimator? = null

    abstract fun show(rootView: View, view: View)

    abstract fun hide()
}

class LoadingNewLocalDisplay : NewLocalDisplay() {
    private var lottieAnimationView: LottieAnimationView? = null

    override fun hide() {
        animator?.cancel()
        lottieAnimationView?.cancelAnimation()
        lottieAnimationView?.progress = 0f
    }

    override fun show(rootView: View, view: View) {
        lottieAnimationView = view as LottieAnimationView
        lottieAnimationView?.show()
        startAnimation(lottieAnimationView)

//        animator = AnimatorUtil.showPropertyAnim(view, AnimatorUtil.ROTATION, 0f, 355f, 650, -1)
    }

    private fun startAnimation(lottieView: LottieAnimationView?) {
        lottieView?.setAnimation("newloading.json")

        lottieView?.repeatCount = ValueAnimator.INFINITE
        lottieView?.playAnimation()
    }
}

class AwaitNewLocalDisplay : NewLocalDisplay() {
    override fun show(rootView: View, view: View) {
    }

    override fun hide() {
    }
}
