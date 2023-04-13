package com.quzy.coding.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.quzy.coding.R
import com.quzy.coding.base.BasePageInterFace
import com.quzy.coding.base.ILoginCheck

/**
 * CreateDate:2023/2/14 11:30
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.fragment
 * @Description:
 */
abstract class BaseYHFragment() :
    BaseAnalyticsFragment(),
    ILoginCheck,
    BasePageInterFace {

    protected var rootView: ViewGroup? = null
    protected lateinit var contentView: View
    protected var mToolbar: Toolbar? = null
    protected var mHideTitle: Boolean = false // true 不需要显示title 默认显示
    protected var mActionBar: ActionBar? = null
    //    TOOLBAR相关的方法
    val REQUESTCODE_LOGIN = 15698
    private var isDataEmpty = true
    private var toolbarTitle: Int = 0

    protected open fun getToolbarTitle(): Int {
        return toolbarTitle
    }

    /**
     * 子类创建自己的视图

     * @param inflater 视图构造器。
     * *
     * @param container 容器。
     * *
     * @param savedInstanceState 保存的状态。
     * *
     * @return 创建出来的视图。如果为 null 则界面出栈。
     */
    @Deprecated("This method is Deprecated, please user #getContentResource# and #initContentView# instand")
    protected open fun doCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = null

    @Deprecated("Don't implement this system function. if you need completely customer rootView try #initContentView#", ReplaceWith(""), DeprecationLevel.ERROR)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(true)
        return initBaseView(inflater, container, savedInstanceState)
    }

    @Deprecated("Do not override this function unless necessary. the existence of this function is for some scene that needn't init base view such as BaseTabFragment")
    protected open fun initBaseView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mActionBar = (activity as AppCompatActivity).supportActionBar
        rootView = inflater.inflate(R.layout.fragment_base, container, false) as ViewGroup
        contentView = doCreateView(inflater, container, savedInstanceState)
            ?: inflater.inflate(getContentResource(), rootView, false)
        rootView?.addView(contentView, 0)
        contentView?.let { initContentView(it) }
        onFinishCreateView()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateSkinUI(activity ?: return)
    }


    // 判断页面是否有数据 ，默认没有 由子类复写
    fun setDataEmpty(misDataEmpty: Boolean) {
        isDataEmpty = misDataEmpty
    }

    protected open fun isDataEmpty(): Boolean {
        return isDataEmpty
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    open fun initContentView(layoutView: View) {}

    open fun getContentResource(): Int = 0

    /**
     * 创建视图完成后的后续操作。
     */
    protected open fun onFinishCreateView() {}

    /**
     * Activity是否可用
     */
    protected fun activityAlive(): Boolean {
        val mActivity = activity
        return !(mActivity == null || mActivity.isDestroyed || mActivity.isFinishing)
    }

    /**
     * 这里给子Fragment初始化Toolbar
     */
    protected open fun initAppBarLayoutAsTitle(v: View?) = initAppBarLayoutAsTitle(v, 0)

    protected open fun initAppBarLayoutAsTitle(v: View?, color: Int = 0) {
        if (v == null) return
        val color = if (color == 0) R.color.white else color
        if (v is AppBarLayout) {
            val appBarLayout = v as AppBarLayout?
            appBarLayout?.setBackgroundResource(color)
            initToolbar(appBarLayout, color)
        } else if (v is Toolbar) {
            initToolbar(v, color)
        }
    }

    protected open fun initToolbar(v: View?, color: Int) {
        if (v == null) return
        mToolbar = v.findViewById(R.id.toolbar) as Toolbar
        if (getToolbarTitle() != 0) mToolbar?.setTitle(getToolbarTitle()) else mToolbar?.title = ""
        mToolbar?.setBackgroundResource(color)
        resetSupportActionBar()
        mActionBar?.setDisplayHomeAsUpEnabled(!hideNavigationIcon())

        resetToolbarNavgationClick()
    }

    /**
     * 是否要隐藏返回键
     */
    open fun hideNavigationIcon(): Boolean {
        return false
    }

    /**
     * 是否要显示title
     */
    protected fun hideToolbarTitle(hideTitle: Boolean): Boolean {
        this.mHideTitle = hideTitle
        resetSupportActionBar()
        return mHideTitle
    }

    open fun resetToolbarNavgationClick() {
        if (hideNavigationIcon()) return
        mToolbar?.setNavigationOnClickListener {
            if (activityAlive()) activity?.onBackPressed()
        }
    }

    /**
     * 显示返回按键
     */
    fun showNavgationIcon() {
        mActionBar?.setHomeButtonEnabled(true) // 设置返回键可用
        mActionBar?.setDisplayHomeAsUpEnabled(true)
        resetSupportActionBar()
    }

    protected open fun setToolbarTitle(title: String) {
        if (TextUtils.isEmpty(title) || mToolbar == null) return
        mToolbar?.title = title
        resetSupportActionBar()
    }

    fun resetSupportActionBar() {
        if (activity != null) {
            (activity as AppCompatActivity).setSupportActionBar(mToolbar)
            mActionBar = (activity as AppCompatActivity).supportActionBar
            resetToolbarNavgationClick()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun setOnNavgationClickListener(onNavgationClickListener: View.OnClickListener?) = mToolbar?.setNavigationOnClickListener(onNavgationClickListener)


    override fun enableSkeleton(layoutid: Int) {
    }

    /**
     * 当用户登录成功返回的时候
     */
    override fun onLoginActivityResult(result: Int) {}

    override fun isAtyAlive(): Boolean {
        return isAdded && activityAlive()
    }

    override fun getAtyContext(): Activity? {
        return activity
    }


    /**
     * @method updateSkinUi
     * @description 更新需要皮肤包控制的UI
     * @date: 2020/4/27 6:33 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    protected open fun updateSkinUI(context: Context) {
    }

    /**
     * @method setErrorViewBackground
     * @description 设置错误页面背景色
     * @date: 2020/4/29 6:15 PM
     * @author: ZhaoXuan.Zeng
     * @param [color]
     * @return
     */
    protected open fun setErrorViewBackground(color: Int) {
    }
}
