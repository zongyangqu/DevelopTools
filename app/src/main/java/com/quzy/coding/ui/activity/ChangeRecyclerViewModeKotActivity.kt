package com.quzy.coding.ui.activity

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.coding.qzy.baselibrary.utils.MMKVUtils
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.Ware
import com.quzy.coding.ui.adapter.AssortShowAdapter
import com.quzy.coding.ui.adapter.HotAdapter
import com.quzy.coding.util.ISearchResult
import com.quzy.coding.util.JsonUtils
import com.quzy.coding.util.widget.CnToolbar
import com.quzy.coding.util.widget.MyDivider

/**
 * CreateDate:2021/11/10 10:14
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:  使用两个adapter来完成 瀑布流和列表的样式切换
 */
class ChangeRecyclerViewModeKotActivity : BaseActivity(), ISearchResult {
    var recyclerView: RecyclerView? = null
    var cnToolbar: CnToolbar? = null
    private var hotAdapter: HotAdapter? = null
    private var classifyWaresAdapter: AssortShowAdapter? = null

    companion object {
        const val SHOW_TYPE_LINER = 0
        const val SHOW_TYPE_GRID = 1
        const val VIEWSHOETYPE = "VIEWSHOETYPE"
    }

    var showType = SHOW_TYPE_GRID
    private var recyclerViewLayoutManager: StaggeredGridLayoutManager? = null
    override fun onViewCreated() {
        showType = MMKVUtils.decodeInt(VIEWSHOETYPE)!!
        recyclerView = findViewById<View>(R.id.recycleListView) as RecyclerView
        cnToolbar = findViewById<View>(R.id.toolBar) as CnToolbar
        try {
            InitShowOrder()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        addShowStyleChengeListener()
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                firstVisiblePosition = getFirstVisiblePosition()
                Log.d("position---->", firstVisiblePosition.toString() + "")
            }
        })
    }

    private var firstVisiblePosition = 0
    private var data: List<Ware>? = null

    private fun InitShowOrder() {
        val wareEntity = JsonUtils.analysisWareJsonFile(this, "ware")
        data = wareEntity.wareList
        when(showType){
            SHOW_TYPE_GRID->initGridMaterialRefrshLayoutListener()
            SHOW_TYPE_LINER->initMaterialRefrshLayoutListener()
        }
        //initMaterialRefrshLayoutListener() ;

    }

    private fun initMaterialRefrshLayoutListener() {
        if (data != null && data!!.size > 0) {
            if (hotAdapter == null) {
                hotAdapter = HotAdapter(this)
                hotAdapter?.refresh(data)
            }
            recyclerView?.adapter = hotAdapter
            recyclerView?.layoutManager = LinearLayoutManager(this@ChangeRecyclerViewModeKotActivity)
            recyclerView?.layoutManager?.scrollToPosition(firstVisiblePosition)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_change_mode_recyclerview2
    }

    override fun getLayoutView(): View? {
        return null
    }

    /**
     * 展示格式切换
     */
    private fun addShowStyleChengeListener() {
        cnToolbar?.setRightImgeButtonIcOnClickListener {
            showType = if (showType == SHOW_TYPE_LINER) SHOW_TYPE_GRID else SHOW_TYPE_LINER
            MMKVUtils.encode(VIEWSHOETYPE,showType)
            val style = if (showType == SHOW_TYPE_LINER) "列表模式" else "瀑布流模式"
            Toast.makeText(this@ChangeRecyclerViewModeKotActivity, "切换格式：$style", Toast.LENGTH_SHORT).show()
            try {
                changeShowType()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Grid布局展示商品
     *
     * @throws Exception
     */
    private fun initGridMaterialRefrshLayoutListener() {
        if (data != null && data!!.size > 0) {
            if (classifyWaresAdapter == null) {
                recyclerViewLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                // recyclerViewLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
                // 设置item的间距处理方式
                recyclerViewLayoutManager?.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
                classifyWaresAdapter = AssortShowAdapter(this)
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

    private fun changeShowType() {
        when (showType) {
            SHOW_TYPE_LINER -> {
                initMaterialRefrshLayoutListener()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cnToolbar?.setRightButtonIcon(R.drawable.icon_grid_32)
                }
            }
            SHOW_TYPE_GRID -> {
                initGridMaterialRefrshLayoutListener()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    cnToolbar?.setRightButtonIcon(R.drawable.icon_list_32)
                }
            }
        }
    }

    /**
     * 获取第一条展示的位置
     *
     * @return
     */
    private fun getFirstVisiblePosition(): Int {
        val position: Int
        position = if (recyclerView?.layoutManager is LinearLayoutManager) {
            (recyclerView?.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        } else if (recyclerView?.layoutManager is GridLayoutManager) {
            (recyclerView?.layoutManager as GridLayoutManager?)!!.findFirstVisibleItemPosition()
        } else if (recyclerView?.layoutManager is StaggeredGridLayoutManager) {
            val layoutManager = recyclerView?.layoutManager as StaggeredGridLayoutManager?
            val lastPositions = layoutManager!!.findFirstVisibleItemPositions(IntArray(layoutManager.spanCount))
            getMinPositions(lastPositions)
        } else {
            0
        }
        return position
    }

    /**
     * 获得当前展示最小的position
     *
     * @param positions
     * @return
     */
    private fun getMinPositions(positions: IntArray): Int {
        val size = positions.size
        var minPosition = Int.MAX_VALUE
        for (i in 0 until size) {
            minPosition = Math.min(minPosition, positions[i])
        }
        return minPosition
    }

    override fun getCurrentViewType(): Int {
        return showType
    }
}