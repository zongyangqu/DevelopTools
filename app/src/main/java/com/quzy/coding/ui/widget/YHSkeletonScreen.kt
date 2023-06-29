package com.quzy.coding.ui.widget

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.ethanhua.skeleton.SkeletonScreen
import com.ethanhua.skeleton.ViewReplacer
import com.ethanhua.skeleton.ViewSkeletonScreen
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.quzy.coding.R
import com.quzy.coding.util.PhoneBrandUtils

/**
 * CreateDate:2023/6/16 16:35
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget
 * @Description:
 */
class YHSkeletonScreen : SkeletonScreen {

    private val TAG = ViewSkeletonScreen::class.java.name
    private var mViewReplacer: ViewReplacer? = null
    private var mActualView: View? = null
    private var mSkeletonResID = 0
    private var mShimmerColor = 0
    private var mShimmer = false
    private var mShimmerDuration = 0
    private var mShimmerAngle = 0
    var innerView: View? = null
    var skeletonLoadingView: View? = null

    constructor(builder: Builder) {
        mActualView = builder.mView
        mSkeletonResID = builder.mSkeletonLayoutResID
        mShimmer = builder.mShimmer
        mShimmerDuration = builder.mShimmerDuration
        mShimmerAngle = builder.mShimmerAngle
        mShimmerColor = builder.mShimmerColor
        mViewReplacer = ViewReplacer(builder.mView)
    }

    private fun generateShimmerContainerLayout(parentView: ViewGroup): ShimmerFrameLayout? {
        val shimmerLayout = LayoutInflater.from(mActualView!!.context)
            .inflate(R.layout.layout_shimmer, parentView, false) as ShimmerFrameLayout

        val colorHighlightBuilder = Shimmer.ColorHighlightBuilder().also {
            it.setBaseColor(mActualView?.context?.resources?.getColor(R.color.black) ?: 0)
            it.setWidthRatio(0.8f)
        }
        val alphaHighlightBuilder = Shimmer.AlphaHighlightBuilder().also {
            it.copyFrom(colorHighlightBuilder.build())
            it.setBaseAlpha(0.5f)
        }
        alphaHighlightBuilder.setTilt(mShimmerAngle.toFloat())
        alphaHighlightBuilder.setDuration(mShimmerDuration.toLong())
        shimmerLayout.setShimmer(alphaHighlightBuilder.build())
        innerView =
            LayoutInflater.from(mActualView?.context).inflate(mSkeletonResID, shimmerLayout, false)
        val lp = innerView?.layoutParams
        if (lp != null) {
            shimmerLayout.layoutParams = lp
        }
        shimmerLayout.addView(innerView)
        return shimmerLayout
    }

    private fun generateSkeletonLoadingView(): View? {
        val viewParent = mActualView!!.parent
        return if (viewParent == null) {
            Log.e(TAG, "the source view have not attach to any view")
            null
        } else {
            val parentView = viewParent as ViewGroup
            (
                    if (mShimmer) generateShimmerContainerLayout(parentView) else LayoutInflater.from(
                        mActualView?.context
                    ).inflate(mSkeletonResID, parentView, false)
                    )
        }
    }

    override fun show() {
        skeletonLoadingView = generateSkeletonLoadingView()
        if (skeletonLoadingView != null) {
            if (getImageSrcID() != -1) {
                val img = skeletonLoadingView?.findViewWithTag<ImageView>("skeleton")
                // 从bugly上报的错误  只出现在小米手机并且版本号大于9.0的系统上出现  顾对其屏蔽
                if (isXiaomi()) {
                    img?.setImageResource(getImageSrcID())
                } else {
                    val bitmapDrawable = img?.drawable as? BitmapDrawable
                    if (bitmapDrawable?.bitmap == null || bitmapDrawable?.bitmap?.isRecycled == true) {
                        skeletonLoadingView?.context?.resources?.let {
                            val bitmap = BitmapFactory.decodeResource(it, getImageSrcID())
                            img?.setImageBitmap(bitmap)
                        }
                    }
                }
            }
            mViewReplacer!!.replace(skeletonLoadingView)
        }
    }

    private fun getImageSrcID(): Int {
        when (mSkeletonResID) {
//            R.layout.layout_skeleton_cart -> {
//                return R.drawable.bg_cart_skeleton
//            }
//            R.layout.layout_skeleton_category -> {
//                return R.drawable.bg_category_skeleton
//            }
//            R.layout.layout_skeleton_prddetail -> {
//                return R.drawable.bg_productdetail_skeleton
//            }
//            R.layout.layout_skeleton_home -> {
//                return R.drawable.bg_home_skeleton
//            }
//            R.layout.layout_skeleton_home_fragment -> {
//                return R.drawable.bg_home_fragment_skeleton
//            }
//            R.layout.layout_skeleton_sub_tab_home -> {
//                return R.drawable.bg_sub_home_skeleton_without_tab
//            }
//            R.layout.layout_skeleton_subhome -> {
//                return R.drawable.bg_sub_home_skeleton_with_tab
//            }
            else -> {
                return -1
            }
        }
    }

    override fun hide() {
        if (mViewReplacer!!.targetView is ShimmerFrameLayout) {
            (mViewReplacer?.targetView as ShimmerFrameLayout).stopShimmer()
        }
        val img = skeletonLoadingView?.findViewWithTag<ImageView>("skeleton")
        val bitmapDrawable = img?.drawable as? BitmapDrawable
        // 从bugly上报的错误  只出现在小米手机并且版本号大于9.0的系统上出现  顾对其屏蔽
        if (!isXiaomi() && bitmapDrawable?.bitmap?.isRecycled == false) {
            bitmapDrawable?.bitmap?.recycle()
            img?.setImageDrawable(null)
        }
        skeletonLoadingView = null
        mViewReplacer?.restore()
    }

    /**
     * 判断是否是小米设备  并且版本号大于9.0
     * */
    private fun isXiaomi(): Boolean {
        return PhoneBrandUtils.isXiaomi() && Build.VERSION.SDK_INT > Build.VERSION_CODES.P
    }

    class Builder(val mView: View) {
        var mSkeletonLayoutResID = 0
        var mShimmer = true
        var mShimmerColor: Int
        var mShimmerDuration = 1000
        var mShimmerAngle = 20
        fun load(@LayoutRes skeletonLayoutResID: Int): Builder {
            mSkeletonLayoutResID = skeletonLayoutResID
            return this
        }

        fun color(@ColorRes shimmerColor: Int): Builder {
            mShimmerColor = ContextCompat.getColor(mView.context, shimmerColor)
            return this
        }

        fun shimmer(shimmer: Boolean): Builder {
            mShimmer = shimmer
            return this
        }

        fun duration(shimmerDuration: Int): Builder {
            mShimmerDuration = shimmerDuration
            return this
        }

        fun angle(@IntRange(from = 0L, to = 30L) shimmerAngle: Int): Builder {
            mShimmerAngle = shimmerAngle
            return this
        }

        fun show(): YHSkeletonScreen {
            val skeletonScreen = YHSkeletonScreen(this)
            skeletonScreen.show()
            return skeletonScreen
        }

        init {
            mShimmerColor = ContextCompat.getColor(mView.context, R.color.shimmer_color)
        }
    }
}
