package com.coding.qzy.baselibrary.widget.vpindicator

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.coding.qzy.baselibrary.R

/**
 * CreateDate:2023/4/14 11:34
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget.vpindicator
 * @Description: viewPager指示器
 */
class ViewPagerIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {
    private var indView: View
    private var root_view: View
    var indViewWidth = 0
    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    fun setWithViewPager(viewPager: ViewPager) {
        //如果没有adapter，则隐藏不显示
        if (null == viewPager.adapter) {
            visibility = GONE
            Log.e(javaClass.simpleName, "no adapter")
            return
        }
        //获取viewPager中fragment的数量
        val count = viewPager.adapter!!.count
        if (count == 0) {
            return
        }

        //加载到window之后再进行view宽度的获取
        rootView.post { //获取当前滑块的宽度
            indViewWidth = width / count
            val layoutParams = indView.layoutParams as LayoutParams
            layoutParams.width = indViewWidth
            indView.layoutParams = layoutParams
        }
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            /**
             *
             * @param position
             * @param positionOffset [0，1]中的值，指示在位置处与页面的偏移百分比。
             * @param positionOffsetPixels 以像素为单位的值，表示与位置的偏移量。
             */
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //获取滑块距父布局左侧的距离
                val left = (position * indViewWidth + positionOffset * indViewWidth).toInt()

                //重新布局滑块view
                indView?.layout(left, indView?.top, left + indViewWidth, indView?.bottom)
            }

            override fun onPageSelected(position: Int) {}
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    init {
        val root = inflate(context, R.layout.app_viewpager_indicator, this)
        root_view = root.findViewById(R.id.root)
        indView = root.findViewById(R.id.ind_view)
    }
}
