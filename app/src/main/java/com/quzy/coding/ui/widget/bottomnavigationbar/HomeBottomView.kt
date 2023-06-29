package com.quzy.coding.ui.widget.bottomnavigationbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.util.SkinUtils
import com.quzy.coding.util.ThemeResource
import kotlinx.android.synthetic.main.view_bottomitem_layout.view.im_holder

/**
 * CreateDate:2023/6/16 17:43
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget.bottomnavigationbar
 * @Description:
 */
open class HomeBottomView(var ctx: Context, attr: AttributeSet) : RelativeLayout(ctx, attr) {

    private var recyc: RecyclerView? = null

    private var mAdapter: RecyclerAdapter? = null

    init {
        addView()
        background = ThemeResource.instance.createBgThemeGradientBtn()
    }

    fun changeState(current: Int) {
        recyc?.smoothScrollToPosition(current)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    protected open fun getImgSelect(): Drawable {
        return SkinUtils.getDrawable(ctx, R.drawable.home_selected)
    }

    private fun addView() {
        recyc = RecyclerView(ctx)
        mAdapter = RecyclerAdapter(ctx, getImgSelect())
        recyc?.adapter = mAdapter
        var param = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        recyc?.layoutParams = param
        recyc?.layoutManager = SmoothLinerLayoutManager(ctx)
        recyc?.itemAnimator = DefaultItemAnimator()
        addView(recyc)
        addView(CoverView(ctx))
    }

    class RecyclerAdapter(var ctx: Context, val imgSelect: Drawable) : RecyclerView.Adapter<HomeBottomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): HomeBottomViewHolder =
            HomeBottomViewHolder(
                LayoutInflater.from(ctx).inflate(
                    R.layout.view_bottomitem_layout,
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int = 2

        override fun onBindViewHolder(viewholder: HomeBottomViewHolder, position: Int) {
            var param =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.dip2px(ctx, 55f))
            viewholder.itemView.layoutParams = param
            if (position == 0) {
                viewholder.itemView.im_holder.setImageDrawable(imgSelect)
            } else {
                viewholder.itemView.im_holder.setImageResource(R.drawable.go_top_icon)
            }
        }
    }

    class HomeBottomViewHolder(v: View) : RecyclerView.ViewHolder(v)

    class SmoothLinerLayoutManager(var mContext: Context) : LinearLayoutManager(mContext) {
        override fun smoothScrollToPosition(
            recyclerView: RecyclerView?,
            state: RecyclerView.State?,
            position: Int
        ) {

            val linearSmoothScroller = ButtomLinearSmoothScroller(mContext)

            linearSmoothScroller.targetPosition = position

            this.startSmoothScroll(linearSmoothScroller)
        }
    }

    class ButtomLinearSmoothScroller(mContext: Context) : LinearSmoothScroller(mContext) {
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {

            return 550 / displayMetrics.densityDpi.toFloat()
        }
    }
}
