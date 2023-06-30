package com.quzy.coding.util.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.widget.roundview.RoundConstraintLayout
import com.quzy.coding.R
import com.quzy.coding.bean.RankListProductBean
import com.quzy.coding.util.extend.gone
import com.quzy.coding.util.extend.show

/**
 * CreateDate:2023-06-29 23:34
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.widget
 * @Description:购物车新榜单组件商品switcher
 */
abstract class CartRankProductSwitcher<T : RecyclerView.Adapter<*>> : FrameLayout {

    private var currentIndex = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        reset()
    }

    var onAnimationListener: OnAnimationListener? = null
    var itemDecorationSize :Int = 0

    fun reset() {
        currentIndex = 0
        removeAllViews()
        itemDecorationSize = getDecorationItemSize()
        addView(initChildView(), LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        addView(initChildView(), LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
    }

    protected abstract fun createLayoutManager(): RecyclerView.LayoutManager

    protected abstract fun createAdapter(): T

    protected abstract fun updateData(adapter: T, dataList: ArrayList<RankListProductBean>)

    protected open fun createItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }


    protected open fun getDecorationItemSize(): Int {
        return 0
    }

    /**
     * 初始化子view
     */
    private fun initChildView(): View {
        val childView = LayoutInflater.from(context).inflate(R.layout.cart_rank_product_switcher_child_layout, this, false)
        val root = childView.findViewById<RoundConstraintLayout>(R.id.root_view)
        val recyclerView = childView.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = createLayoutManager()
        createItemDecoration()?.let {
            recyclerView.addItemDecoration(it)
        }
        return childView
    }

    fun showSingleType(dataList: ArrayList<RankListProductBean>) {
        if (getChildAt(1).visibility != View.GONE) {
            getChildAt(1).visibility = View.GONE
        }
        val childView = getChildAt(0)
        val childRecyclerView = childView.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = createAdapter()
        updateData(adapter, dataList)
        childRecyclerView?.adapter = adapter
    }

    fun showNext(dataList: ArrayList<RankListProductBean>, inAnimRes: Int, outAnimRes: Int) {
        if (currentIndex != 0) {
            val preChildView = getChildAt((currentIndex - 1) % 2)
            val nextChildView = getChildAt(currentIndex % 2)
            val nextRecyclerView = nextChildView?.findViewById<RecyclerView>(R.id.recycler_view)
            if (outAnimRes == 0) {
                onAnimationListener?.onOutAnimationStart(preChildView)
                nextChildView?.postDelayed(
                    {
                        preChildView?.gone()
                        val adapter = createAdapter()
                        updateData(adapter, dataList)
                        nextChildView.show()
                        nextRecyclerView?.adapter = adapter
                        if (inAnimRes != 0) {
                            val inAnim = AnimationUtils.loadAnimation(context, inAnimRes)
                            nextChildView.startAnimation(inAnim)
                        }
                        onAnimationListener?.onInAnimationStart(nextChildView)
                    },
                    animationDelay()
                )
            } else {
                val outAnim = AnimationUtils.loadAnimation(context, outAnimRes)
                outAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        nextChildView?.postDelayed(
                            {
                                val adapter = createAdapter()
                                updateData(adapter, dataList)
                                nextRecyclerView?.adapter = adapter
                                if (inAnimRes != 0) {
                                    val inAnim = AnimationUtils.loadAnimation(context, inAnimRes)
                                    nextChildView.startAnimation(inAnim)
                                }
                                onAnimationListener?.onInAnimationStart(nextChildView)
                            },
                            animationDelay()
                        )
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }
                })
                preChildView?.startAnimation(outAnim)
                onAnimationListener?.onOutAnimationStart(preChildView)
            }
        } else {
            val nextChildView = getChildAt(currentIndex)
            val nextRecyclerView = nextChildView?.findViewById<RecyclerView>(R.id.recycler_view)
            val adapter = createAdapter()
            updateData(adapter, dataList)
            nextRecyclerView?.adapter = adapter
            if (inAnimRes != 0) {
                nextChildView?.startAnimation(AnimationUtils.loadAnimation(context, inAnimRes))
            }
            onAnimationListener?.onInAnimationStart(nextChildView)
        }
        currentIndex++
    }


    /**
     * 进入动画延迟时间
     */
    protected open fun animationDelay(): Long {
        return 250L
    }

    interface OnAnimationListener {
        fun onInAnimationStart(view: View)
        fun onOutAnimationStart(view: View)
    }


}