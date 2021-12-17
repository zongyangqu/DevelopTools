package com.quzy.coding.ui.activity

import android.os.Build
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.Ware
import com.quzy.coding.ui.adapter.AssortShowAdapter
import com.quzy.coding.ui.adapter.ProductShowAdapter
import com.quzy.coding.util.ISearchResult
import com.quzy.coding.util.JsonUtils
import com.quzy.coding.util.widget.CnToolbar
import com.quzy.coding.util.widget.MyDivider

/**
 * CreateDate:2021/12/17 10:29
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class ChangeRecyclerViewModeKotActivity :BaseActivity(),ISearchResult{

    var recyclerView: RecyclerView? = null
    var cnToolbar: CnToolbar? = null
    var showWaresNum: TextView? = null
    private var classifyWaresAdapter: ProductShowAdapter? = null
    private var firstVisiblePosition = 0
    private var data: List<Ware>? = null
    var showType = SHOW_TYPE_LINER
    private var recyclerViewLayoutManager: StaggeredGridLayoutManager? = null

    companion object {
        const val SHOW_TYPE_LINER :Int= 0
        const val SHOW_TYPE_GRID :Int= 1
    }
    var currentType:Int = SHOW_TYPE_GRID

    override fun onViewCreated() {
        supportActionBar?.hide()
        recyclerView = findViewById<View>(R.id.recycleListView) as RecyclerView?
        cnToolbar = findViewById<View>(R.id.toolBar) as CnToolbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addShowStyleChengeListener()
        }
        InitShowOrder()
    }

    private fun InitShowOrder() {
        val wareEntity = JsonUtils.analysisWareJsonFile(this, "ware")
        data = wareEntity.wareList
        initGridMaterialRefrshLayoutListener()
    }

    fun initGridMaterialRefrshLayoutListener() {
        if (data != null && data!!.size > 0) {
            if (classifyWaresAdapter == null) {
                recyclerViewLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                // recyclerViewLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                // 设置item的间距处理方式
                recyclerViewLayoutManager?.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                classifyWaresAdapter = ProductShowAdapter(this)
                recyclerView?.adapter = classifyWaresAdapter
                recyclerView?.layoutManager = recyclerViewLayoutManager
                recyclerView?.addItemDecoration(MyDivider())
                classifyWaresAdapter?.refresh(data)
                recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        recyclerViewLayoutManager?.invalidateSpanAssignments() //防止第一行到顶部有空白区域
                    }
                })
            } else {
                recyclerView?.adapter = classifyWaresAdapter
                recyclerView?.layoutManager = recyclerViewLayoutManager
            }
            recyclerView?.layoutManager?.scrollToPosition(firstVisiblePosition)
        } else {
            Toast.makeText(this@ChangeRecyclerViewModeKotActivity, "该类别暂无商品", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addShowStyleChengeListener() {
        cnToolbar?.setRightImgeButtonIcOnClickListener {
            val style = if (currentType == SHOW_TYPE_LINER) "瀑布流模式" else "列表模式"
            Toast.makeText(this@ChangeRecyclerViewModeKotActivity, "切换格式：$style", Toast.LENGTH_SHORT).show()
            changeShowType()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun changeShowType() {
        when (currentType) {
            SHOW_TYPE_LINER -> {
                cnToolbar?.setRightButtonIcon(R.drawable.icon_grid_32)
                currentType = SHOW_TYPE_GRID
                classifyWaresAdapter?.notifyDataSetChanged()
            }
            SHOW_TYPE_GRID -> {
                cnToolbar?.setRightButtonIcon(R.drawable.icon_list_32)
                currentType = SHOW_TYPE_LINER
                classifyWaresAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_change_mode_recyclerview3
    }
    override fun getLayoutView(): View? {
        return null
    }

    override fun getCurrentViewType(): Int {
        return currentType
    }
}