package com.quzy.coding.ui.widget.bottomnavigationbar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.SparseIntArray
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.util.set
import androidx.core.view.ViewCompat
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.util.bottomnavigationbar.OnTabSelectedListener
import com.quzy.coding.util.extend.click
import com.quzy.coding.util.extend.ctx
import com.quzy.coding.util.extend.gone
import com.quzy.coding.util.extend.isShowing
import com.quzy.coding.util.extend.show
import org.jetbrains.anko.forEachChild
import org.jetbrains.anko.forEachChildWithIndex
import java.lang.ref.SoftReference

/**
 * CreateDate:2023/6/16 17:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget.bottomnavigationbar
 * @Description:
 */
class BottomNavigationBar(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    val DEFAULT_ELEVATION = 20F
    val DEFAULT_TRANSY = 1.5F
    var mViewList: ArrayList<BottomNavigationItem> = ArrayList()
    var mTabSelectedListener: OnTabSelectedListener? = null
    var mCurrentTag: Int = -1
    var layoutParams: LinearLayout.LayoutParams? = null
    var weightSum: Int = 0
    var mMaxSum = 5
    var currentInnerPosition = 0 // 当前首页中顶部tab位置，比如第一个tab对应的值为0
    var currentInnerScroll = 0 // 0--普通图标 1--回到顶部图标

    private val scrollHomeArray by lazy { SparseIntArray() }

    companion object {
        private const val SCROLL_TO_TOP: Int = 1
    }

    private var scrollListener: ScrollListener? = null

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private class ScrollListener(bar: BottomNavigationBar) : BottomScrollListener {
        private val reference = SoftReference<BottomNavigationBar>(bar)

        override fun onChange(bean: ChangeHomeEvent) {
            reference.get()?.handleScrollChange(bean)
        }
    }

    init {
        setBackgroundColor(Color.WHITE)
        orientation = HORIZONTAL
        ViewCompat.setElevation(this, DEFAULT_ELEVATION)
        ViewCompat.setTranslationY(this, DEFAULT_TRANSY)
        scrollListener = ScrollListener(this)
    }

    fun initScrollListener(key: String) {
        BottomScrollManager.map[key] = scrollListener as BottomScrollListener
    }

    fun addItem(item: BottomNavigationItem) {
        addView(item)
        mViewList.add(item)
        weightSum++
        item.click {
            selectTab(item.tabTag)
        }
    }

    fun hideItem(tag: Int, defaultTag: Int) {
        weightSum--
        forEachChildWithIndex { i, view -> if (tag == i) view.gone() }
        if (mCurrentTag == tag) selectTab(defaultTag, false)
        initialise()
    }

    fun showItem(tag: Int) {
        weightSum++
        if (weightSum > mMaxSum) {
            weightSum--
            return
        }
        forEachChildWithIndex { i, view -> if (tag == i) view.show() }
        initialise()
    }

    fun isItemShowing(tag: Int): Boolean {
        forEachChildWithIndex { i, view -> if (tag == i) return view.isShowing() }
        return false
    }

    fun selectTab(tag: Int, isFristSelect: Boolean = false) {
        if (mCurrentTag == tag) {
            mTabSelectedListener?.onTabReselected(tag)
//            if (mCurrentTag == HomeTabPosition.HOME && scrollHomeArray[currentInnerPosition] == SCROLL_TO_TOP) {
//                BusUtil.post(ChangeTopEvent())
//            }
        } else {
            handleItemSelect(tag)
            if (!isFristSelect) mTabSelectedListener?.onTabSelected(tag)
            mCurrentTag = tag
        }
    }

    fun handleItemSelect(tag: Int) {
        mViewList.forEach {
            it.isSelected = it.tabTag == tag
            if (it.tabTag == tag || it.tabTag == mCurrentTag) it.imageBySelect()
        }
//        if (mCurrentTag >= 0) {
//            mViewList[mCurrentTag].isSelected = false
//            mViewList[mCurrentTag].imageBySelect()
//        }
    }

    fun setFirstSelectedTab(tag: Int): BottomNavigationBar {
        selectTab(tag, true)
        return this
    }

    fun getTabView(tag: Int): View? {
        mViewList.forEach { if (tag == it.tabTag) return it }
        return null
    }

    fun initialise() {
        val params = LinearLayoutCompat.LayoutParams(UIUtils.getWindowWidth(ctx) / weightSum, ViewGroup.LayoutParams.WRAP_CONTENT)
        forEachChild { it.layoutParams = params }
    }

    private fun handleScrollChange(bean: ChangeHomeEvent) {
        if (currentInnerPosition != bean.innerPosition && currentInnerScroll != bean.scrollhome) {
            // 在首页中，切换顶部tab，因为滚动状态不同，所以需要更新滚动状态对应的tab UI
            currentInnerPosition = bean.innerPosition
            scrollHomeArray[bean.innerPosition] = bean.scrollhome
            currentInnerScroll = bean.scrollhome
            mViewList[0].changeState(scrollHomeArray[bean.innerPosition])
            return
        }
        currentInnerPosition = bean.innerPosition
        if (scrollHomeArray[bean.innerPosition] == bean.scrollhome) {
            return
        }
        scrollHomeArray[bean.innerPosition] = bean.scrollhome
        currentInnerScroll = bean.scrollhome
        mViewList[0].changeState(scrollHomeArray[bean.innerPosition])
    }

    fun release(key: String) {
        BottomScrollManager.map.remove(key)
    }
}
