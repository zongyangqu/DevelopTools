package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apkfuns.logutils.LogUtils
import com.coding.qzy.baselibrary.utils.AbToastUtil
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.Fruit
import com.quzy.coding.databinding.ActivityAverageRecyclerviewBinding
import com.quzy.coding.ui.adapter.AverageAdapter
import com.quzy.coding.util.RecyclerViewTrackShowUtils

/**
 * CreateDate:2022/7/8 16:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: RecyclerView添加横向均分 支持设置边距 item曝光
 */
class RecyclerViewAverageKotActivity : BaseActivity() {

    val TAG: String = this::class.java.simpleName
    private var data = ArrayList<Fruit>()
    lateinit var adapter: AverageAdapter
    var activityAverageRecyclerviewBinding: ActivityAverageRecyclerviewBinding? = null

    override fun onViewCreated() {
        supportActionBar?.title = "RecyclerView横向均分、间距、item曝光"
        initData()
        // 设置RecyclerView横向
        setGridLayoutManager(activityAverageRecyclerviewBinding?.recyclerView)
        adapter = AverageAdapter(this)
        expoList(true, data, activityAverageRecyclerviewBinding?.recyclerView)
        activityAverageRecyclerviewBinding?.recyclerView?.adapter = adapter
        adapter?.setData(data)
    }

    override fun getLayoutView(): View? {
        activityAverageRecyclerviewBinding = ActivityAverageRecyclerviewBinding.inflate(LayoutInflater.from(this))
        activityAverageRecyclerviewBinding?.root?.let {
            return it.rootView
        }
        return null
    }

    private fun setGridLayoutManager(product_gridview: RecyclerView?) {
        val totalSize = data?.size ?: 0
        if (totalSize in 1..3) {
            val manager = GridLayoutManager(this, data?.size ?: 2)
            product_gridview?.layoutManager = manager
        } else {
            // 数量大于3个
            val manager: GridLayoutManager =
                object : GridLayoutManager(this, 1, HORIZONTAL, false) {
                    override fun canScrollHorizontally(): Boolean {
                        return true
                    }
                }
            product_gridview?.layoutManager = manager
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    fun initData() {
        repeat(1) {
            data.add(Fruit("apple", R.mipmap.apple_pic))
            data.add(Fruit("banana", R.mipmap.banana_pic))
            data.add(Fruit("orange", R.mipmap.orange_pic))
            data.add(Fruit("watermelon", R.mipmap.watermelon_pic))
            data.add(Fruit("pear", R.mipmap.pear_pic))
            data.add(Fruit("grape", R.mipmap.grape_pic))
            data.add(Fruit("pineapple", R.mipmap.pineapple_pic))
            data.add(Fruit("strawberry", R.mipmap.strawberry_pic))
            data.add(Fruit("cherry", R.mipmap.cherry_pic))
            data.add(Fruit("mango", R.mipmap.mango_pic))
        }
    }

    private var expoUtil: RecyclerViewTrackShowUtils? = null
    fun expoList(isNotifyOrResume: Boolean, fruit: ArrayList<Fruit>?, rv: RecyclerView?) {
        try {
            if (expoUtil == null) {
                expoUtil =
                    RecyclerViewTrackShowUtils()
            }
            expoUtil?.recordViewShowCount(
                rv,
                isNotifyOrResume,
                object : RecyclerViewTrackShowUtils.OnExposureListener {
                    override fun onExposure(position: Int, child: View) { // view是RecyclerView的item
                        LogUtils.tag(TAG).d(fruit?.get(position)?.name)
                        AbToastUtil.showToast(this@RecyclerViewAverageKotActivity, fruit?.get(position)?.name)
                    }
                }
            )
        } catch (e: java.lang.Exception) {
        }
    }
}
