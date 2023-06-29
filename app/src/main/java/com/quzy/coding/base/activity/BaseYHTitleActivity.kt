package com.quzy.coding.base.activity

import android.widget.TextView
import com.quzy.coding.R
import com.quzy.coding.util.ResourceUtil
import com.quzy.coding.util.extend.singleClick
import com.quzy.coding.util.widget.IconFont

/**
 * CreateDate:2023/6/16 16:25
 * @author: zongyang qu
 * @Package： com.quzy.coding.base.activity
 * @Description:
 */
abstract class BaseYHTitleActivity : BaseYHActivity() {
    protected var ifBack: IconFont? = null
    protected var tvTitle: TextView? = null

    override fun setRootView() {
        setContentView(R.layout.activity_base)
    }

    override fun initToolbar() {
        mToolbar = findViewById(R.id.toolbar)
    }

    /**
     * @description 新版toolbar标题栏
     */
    private fun initCustomToolbar() {
        ifBack = findViewById(R.id.if_back)
        ifBack?.singleClick {
            onBackPressed()
        }
        tvTitle = findViewById(R.id.tv_title)
        if (toolbarTitle != 0) {
            tvTitle?.setText(toolbarTitle)
        }
    }

    override fun initView() {
        super.initView()
        if (hideNavigationIcon()) {
            initCustomToolbar()
        }
    }

    override fun updateSkinUI() {
        super.updateSkinUI()
        if (hideNavigationIcon()) {
            ifBack?.setTextColor(ResourceUtil.getColor(R.color.leftArrow))
        }
    }

    override fun setToolbarTitle(title: String?) {
        tvTitle?.text = title
    }
}