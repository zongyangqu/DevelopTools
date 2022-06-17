package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.Fruit
import com.quzy.coding.databinding.ActivityRecyclerviewBinding
import com.quzy.coding.ui.adapter.BaseVerticalQuickAdapter

/**
 * CreateDate:2021/8/20 16:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class BaseQuickRecyclerViewKotActivity : BaseActivity() {

    private var data = ArrayList<Fruit>()
    lateinit var adapter: BaseVerticalQuickAdapter
    var activityRecyclerviewBinding: ActivityRecyclerviewBinding? = null

    override fun onViewCreated() {
        supportActionBar?.title = "BaseQuickAdapter"
        var manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        activityRecyclerviewBinding?.recyclerView?.layoutManager = manager
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        activityRecyclerviewBinding?.recyclerView?.addItemDecoration(dividerItemDecoration)
        initData()
        adapter = BaseVerticalQuickAdapter()
        activityRecyclerviewBinding?.recyclerView?.adapter = adapter
        adapter?.setNewData(data)
    }

    override fun getLayoutView(): View? {
        activityRecyclerviewBinding = ActivityRecyclerviewBinding.inflate(LayoutInflater.from(this))
        activityRecyclerviewBinding?.root?.let {
            return it.rootView
        }
        return null
    }

    override fun getLayoutId(): Int {
        return 0
    }

    fun initData() {
        repeat(4) {
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
}
