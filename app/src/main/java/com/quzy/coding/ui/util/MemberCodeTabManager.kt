package com.quzy.coding.ui.util

import android.os.Bundle
import android.util.ArrayMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.quzy.coding.R
import com.quzy.coding.ui.fragment.MemberCodeStoreCenterFragment
import com.quzy.coding.ui.fragment.MemberCodeWelfareCenterFragment

/**
 * CreateDate:2023/6/16 18:50
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.util
 * @Description:
 */
class MemberCodeTabManager {

    fun getDefaultFragment(): String = TabConstant.STORE_CENTER_FRAGMENT
    private val fragments = ArrayMap<String, Fragment>()
    var mActiveTab: String? = null

    fun getPositionByClassName(className: String): Int {
        return when {
            className.contains(TabConstant.STORE_CENTER_FRAGMENT) -> {
                TabPosition.TAB_STORE_CENTER
            }
            className.contains(TabConstant.WELFARE_CENTER_FRAGMENT) -> {
                TabPosition.TAB_WELFARE_CENTER
            }
            else -> {
                TabPosition.TAB_STORE_CENTER
            }
        }
    }

    fun getFragmentNameByTag(tag: Int): String? {
        when (tag) {
            TabPosition.TAB_STORE_CENTER -> return TabConstant.STORE_CENTER_FRAGMENT
            TabPosition.TAB_WELFARE_CENTER -> return TabConstant.WELFARE_CENTER_FRAGMENT
        }
        return null
    }

    /**
     * 切换首页Fragment
     */
    fun switchTabFragment(activityName: String, fragmentManager: FragmentManager, bundle: Bundle? = null) {
        val fragment: Fragment?
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (fragments[activityName] == null) {
            fragment = when(activityName) {
                TabConstant.STORE_CENTER_FRAGMENT ->
                    MemberCodeStoreCenterFragment()

                TabConstant.WELFARE_CENTER_FRAGMENT ->
                    MemberCodeWelfareCenterFragment()

                else ->
                    MemberCodeStoreCenterFragment()
            }
            fragment?.let {
                fragments[activityName] = it
                fragmentTransaction.add(
                    R.id.fragment_container,
                    it,
                    it.javaClass.simpleName
                )
            }
        } else {
            fragment = fragments[activityName]
        }
        hideFragment(fragmentTransaction) // 如果a的fragment存在 但是现在要显示b的fragment 就需要将a的fragment做detach  反之亦然
        fragment?.let {
            fragmentTransaction.show(it)
            fragmentTransaction.commitNowAllowingStateLoss()
        }
    }

    /**
     * 清理
     */
    fun release() {
        mActiveTab = null
        fragments.clear()
    }

    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragment(ft: FragmentTransaction) {
        for ((key, value) in fragments) {
            if (value?.isHidden == false) ft.hide(value)
        }
    }
}