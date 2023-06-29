package com.quzy.coding.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.quzy.coding.R
import com.quzy.coding.base.fragment.BaseYHFragment

/**
 * CreateDate:2023/6/16 18:54
 * @author: zongyang qu
 * @Package： com.quzy.coding.base
 * @Description:
 */
open class BaseNavigationBarFragment : BaseYHFragment() {

    private var isViewCreated: Boolean = false // 视图是否被初始化过了
    private var isUIVisible: Boolean = false // 是否对用户可见
    var isContentCreated: Boolean = false // 子类的view全部初始化完成,如果在onhiddenChanged里面想用oncreateview里面初始化的值，需要先判断这个参数为true

    override fun initBaseView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mActionBar = (activity as AppCompatActivity).supportActionBar
        rootView = inflater.inflate(R.layout.fragment_base, container, false) as ViewGroup
        contentView = doCreateView(inflater, container, savedInstanceState)
            ?: inflater.inflate(getContentResource(), rootView, false)
        rootView?.addView(contentView, 0)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        lazyLoadData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isUIVisible = isVisibleToUser
        if (isUIVisible) lazyLoadData()
    }

    @Deprecated("Use onHiddenChangedByViewCreated instand", ReplaceWith("onHiddenChangedByViewCreated(hidden)", "com.quzy.coding.base.onHiddenChangedByViewCreated"), DeprecationLevel.ERROR)
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isUIVisible = !hidden
        if (isUIVisible) lazyLoadData()
        onHiddenChangedByViewCreated(hidden)
    }

    /**
     * 懒加载数据
     */
    private fun lazyLoadData() {
        if ((isViewCreated)) {
            // 数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false
            isUIVisible = false
            contentView?.let { initContentView(it) }
            onFinishCreateView()
            isContentCreated = true
        }
    }

    @Deprecated("Deprecated", ReplaceWith("onResumeByViewCreated()"), DeprecationLevel.ERROR)
    override fun onResume() {
        super.onResume()
        // 不经过hidde直接显示的fragment isContentCreated的值永远是false
        if (isContentCreated) onResumeByViewCreated()
    }

    override fun putPageParam(key: String?, value: String?) {
    }

    override fun showLoadingView(show: Boolean) {
    }

    protected open fun onResumeByViewCreated() {}

    protected open fun onHiddenChangedByViewCreated(hidden: Boolean) {}
}