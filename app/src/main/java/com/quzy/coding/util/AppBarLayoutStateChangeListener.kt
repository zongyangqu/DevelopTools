package com.quzy.coding.util

import com.google.android.material.appbar.AppBarLayout

/**
 * CreateDate:2021/10/17 10:35
 * @author: zongyang qu
 * @Package： com.quzy.coding.util
 * @Description:
 */
abstract class AppBarLayoutStateChangeListener : AppBarLayout.OnOffsetChangedListener {

    enum class State {
        EXPANDED,  //展开
        COLLAPSED,  //折叠
        INTERMEDIATE //中间状态
    }

    private var mCurrentState = State.INTERMEDIATE

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        mCurrentState = if (verticalOffset == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED, verticalOffset)
            }
            State.EXPANDED
        } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED, verticalOffset)
            }
            State.COLLAPSED
        } else {
            if (mCurrentState != State.INTERMEDIATE) {
                onStateChanged(appBarLayout, State.INTERMEDIATE, verticalOffset)
            }
            State.INTERMEDIATE
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout?, state: State?, verticalOffset: Int)
}

