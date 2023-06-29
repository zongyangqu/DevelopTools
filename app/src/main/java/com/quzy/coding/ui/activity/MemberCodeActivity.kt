package com.quzy.coding.ui.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.coding.qzy.baselibrary.utils.extend.dp
import com.quzy.coding.R
import com.quzy.coding.base.activity.BaseYHTitleActivity
import com.quzy.coding.databinding.ActivityMemberCodeNewBinding
import com.quzy.coding.ui.util.MemberCodeTabManager
import com.quzy.coding.ui.util.TabConstant
import com.quzy.coding.ui.util.TabPosition
import com.quzy.coding.ui.widget.bottomnavigationbar.BottomNavigationItem
import com.quzy.coding.ui.widget.bottomnavigationbar.NavigationItemBean
import com.quzy.coding.util.StatusBarUtil
import com.quzy.coding.util.bottomnavigationbar.OnTabSelectedListener
import com.quzy.coding.util.extend.increaseTouchRange
import com.quzy.coding.util.extend.show
import com.quzy.coding.util.extend.singleClick

/**
 * CreateDate:2023/6/16 17:27
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class MemberCodeActivity : BaseYHTitleActivity(), OnTabSelectedListener {
    val viewBinding: ActivityMemberCodeNewBinding by lazy { ActivityMemberCodeNewBinding.bind(findViewById(R.id.member_code_new_layout)) }
    //是否有多个Fragment标识
    private var hasTab: Boolean = true
    private var mClickBottomButtonName = ""
    private val tabManager = MemberCodeTabManager()
    private var tabInit: Boolean = false
    private var inTabInAnimation: Boolean = false
    override fun initView() {
        super.initView()
        initTitle()
        StatusBarUtil.showLightStatusBarIcon(this)
        switchContent(tabManager.getDefaultFragment())
        setConfigCodeInfo()
    }

    fun setConfigCodeInfo(){
        hasTab = true
        if (!tabInit) {
            tabInit = true
            initBottomBar()
        }
    }




    /**
     * 切换Fragment
     */
    private fun switchFragment(activityName: String?) {
        activityName?.let {
            tabManager.switchTabFragment(it, supportFragmentManager)
        }
    }

    /**
     * 左右切换动作
     */
    private fun switchContent(fragment: String?) {
        synchronized(this) {
            setCurrentTabName(fragment)
            switchFragment(fragment)
            tabManager.mActiveTab = fragment
        }
    }

    /**
     * 切换TabName
     */
    private fun setCurrentTabName(to: String?) {
        mClickBottomButtonName = when (to) {
            TabConstant.STORE_CENTER_FRAGMENT -> {
                getTabName(TabPosition.TAB_STORE_CENTER)
            }
            TabConstant.WELFARE_CENTER_FRAGMENT -> {
                getTabName(TabPosition.TAB_WELFARE_CENTER)
            }
            else -> "到店"
        }
    }

    override fun getToolbarTitle(): Int {
        return R.string.in_shop_shopping
    }

    companion object {
        // tabIndex之所以从100开始,是以为BottomNavigationBar对index=0的tab有特殊的选中效果处理，这里不需要
        // 见TabPosition，BottomNavigationBar
        const val TAB_POSITION_DIFF = 100
        // -1表示没有红点提示在展示
        const val RED_DOT_INDEX_NONE = -1
        // -1表示没有气泡提示在展示
        const val MST_TIPS_INDEX_NONE = -1
        const val TAB_ANIMATION_DURATION = 400L
        const val YHJR_BIND_FAILED = "1001"
        const val YHJR_BIND_SUCCESS = "1002"
    }

    /**
     * 底部tab隐藏显示动画
     */
    fun tabHide(hide: Boolean) {
        if (!hasTab || inTabInAnimation) {
            return
        }
        val tabHeight: Float = if (viewBinding.fragmentBnb.measuredHeight > 0) viewBinding.fragmentBnb.measuredHeight.toFloat() else 55f.dp
        if (hide) {
            ObjectAnimator.ofFloat(viewBinding.fragmentBnb, "translationY", tabHeight).apply {
                duration = TAB_ANIMATION_DURATION
                addListener(hideAnimationListener)
                start()
            }
        } else {
            ObjectAnimator.ofFloat(viewBinding.fragmentBnb, "translationY", -tabHeight).apply {
                duration = TAB_ANIMATION_DURATION
                addListener(showAnimationListener)
                start()
                hasTabFirstShow = true
            }
        }
    }

    private var hasTabFirstShow: Boolean = false
    private val hideAnimationListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            inTabInAnimation = true
            if (hasTabFirstShow && viewBinding.groupTipsMsg.isVisible) {
                viewBinding.groupTipsMsg.show(false)
            }
        }

        override fun onAnimationEnd(animation: Animator?) {
            inTabInAnimation = false
        }

        override fun onAnimationCancel(animation: Animator?) {
            inTabInAnimation = false
        }

        override fun onAnimationRepeat(animation: Animator?) {
            inTabInAnimation = true
        }
    }


    private val showAnimationListener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            inTabInAnimation = true
            if (hasTabFirstShow && viewBinding.groupTipsMsg.isVisible) {
                viewBinding.groupTipsMsg.show(false)
            }
        }

        override fun onAnimationEnd(animation: Animator?) {
            inTabInAnimation = false
        }

        override fun onAnimationCancel(animation: Animator?) {
            inTabInAnimation = false
        }

        override fun onAnimationRepeat(animation: Animator?) {
            inTabInAnimation = true
        }
    }

    /**
     * 底部tab初始化
     */
    private fun initBottomBar() {
        if (!hasTab) return
        viewBinding.fragmentBnb.apply {
            addItem(
                BottomNavigationItem(this@MemberCodeActivity, TabPosition.TAB_STORE_CENTER).setItemData(
                    NavigationItemBean(
                        R.drawable.store_cneter_selected,
                        R.drawable.store_cneter_normal,
                        getTabName(TabPosition.TAB_STORE_CENTER),
                        TabPosition.TAB_STORE_CENTER
                    )
                )
            )
            addItem(
                BottomNavigationItem(this@MemberCodeActivity, TabPosition.TAB_WELFARE_CENTER).setItemData(
                    NavigationItemBean(
                        R.drawable.welfare_cneter_selected,
                        R.drawable.welfare_cneter_normal,
                        getTabName(TabPosition.TAB_WELFARE_CENTER),
                        TabPosition.TAB_WELFARE_CENTER
                    )
                )
            )
            setFirstSelectedTab(TabPosition.TAB_STORE_CENTER).initialise()
            mTabSelectedListener = this@MemberCodeActivity
        }
//        viewBinding.tabBarCover.show()
//        viewBinding.tabBarCover.singleClick { } // 忽略点击事件
    }


    /**
     * 获取tab的名字，会根据接口返回字段变化
     */
    private fun getTabName(tabName: Int): String {
        return when (tabName) {
            TabPosition.TAB_STORE_CENTER -> {
                "到店"
            }
            TabPosition.TAB_WELFARE_CENTER -> {
                "福利"
            }
            else -> "到店"
        }
    }

    private fun initTitle() {
        tvTitle?.setText("到店购物")
        ifBack?.setTextColor((ContextCompat.getColor(this, R.color.white)))
        tvTitle?.setTextColor((ContextCompat.getColor(this, R.color.white)))
        viewBinding.titleBar.tvHelp.increaseTouchRange()
    }

    override fun getMainContentResId(): Int {
        return R.layout.activity_member_code_new
    }

    override fun onTabSelected(position: Int) {
        viewBinding.fragmentBnb.post { switchContent(tabManager.getFragmentNameByTag(position)) }
        setTitleState(position)
    }

    /**
     * 根据tab位置展示不同的标题
     */
    private fun setTitleState(position: Int) {
        when (position) {
            TabPosition.TAB_STORE_CENTER -> {
                viewBinding.titleBar.tvHelp.show()
                viewBinding.titleBar.titleBar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_2A292F))
            }
            TabPosition.TAB_WELFARE_CENTER -> {
                viewBinding.titleBar.tvHelp.show(false)
                viewBinding.titleBar.titleBar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_FF5F37))
            }
        }
    }

    override fun onTabReselected(position: Int) {
    }
}