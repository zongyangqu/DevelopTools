package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityRecyclerviewBinding

/**
 * CreateDate:2021/10/19 14:50
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: Kotlin-多条目瀑布流的RecyclerView
 */
class RecyclerViewWaterfallComplexKotActivity : BaseActivity() {

    var activityRecyclerviewBinding: ActivityRecyclerviewBinding? = null

    override fun onViewCreated() {

    }

    override fun getLayoutId(): Int {
        return 0;
    }

    override fun getLayoutView(): View? {
        activityRecyclerviewBinding = ActivityRecyclerviewBinding.inflate(LayoutInflater.from(this))
        activityRecyclerviewBinding?.root?.let {
            return it.rootView
        }
        return null
    }
}