package com.quzy.coding.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivtyRecyclerHorizontalMoreBinding
import com.quzy.coding.ui.adapter.HorizontalRefreshItemViewAdapter
import kotlinx.android.synthetic.main.view_content_service_help.view.*

/**
 * CreateDate:2022/6/17 11:42
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class RecyclerHorizontalMoreActivity : BaseActivity() {

    var viewBinding: ActivtyRecyclerHorizontalMoreBinding? = null
    private var adapter: HorizontalRefreshItemViewAdapter? = null
    override fun onViewCreated() {
        // 列表1
        viewBinding?.mRefresh?.reset()
        viewBinding?.mRefresh?.setOnRefreshListener {
            // TODO 左拉刷新回调
            Toast.makeText(this, "刷新或跳转页面", Toast.LENGTH_SHORT).show()
        }
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewBinding?.mList?.layoutManager = layoutManager
        val dataList = ArrayList<String>()
        for (c in 'A'..'D') {
            dataList.add(c.toString())
        }
        val testAdapter = RvAdapter(this, dataList)
        viewBinding?.mList?.adapter = testAdapter

        // 列表2
        viewBinding?.mRefresh2?.reset()
        viewBinding?.mRefresh2?.setOnRefreshListener {
            // TODO 左拉刷新回调
            Toast.makeText(this, "刷新或跳转页面", Toast.LENGTH_SHORT).show()
        }
        val dataList2 = ArrayList<String>()
        for (c in 'A'..'B') {
            dataList2.add(c.toString())
        }
        setGridLayoutManager(dataList2)
        if (dataList2 != null) {
            if (adapter == null) {
                adapter = HorizontalRefreshItemViewAdapter()
                viewBinding?.mList2?.adapter = adapter
            }
            adapter?.setData(this, dataList2)
        }
    }

    private fun setGridLayoutManager(info: ArrayList<String>?) {
        val totalSize = info?.size ?: 0
        if (totalSize in 1..4) {
            // 数量小于等于4个，均分显示
            val manager = GridLayoutManager(this, info?.size ?: 2)
            viewBinding?.mList2?.layoutManager = manager
        } else {
            // 数量大于4个
            val manager: GridLayoutManager =
                object : GridLayoutManager(this, 1, HORIZONTAL, false) {
                    override fun canScrollHorizontally(): Boolean {
                        return true
                    }
                }
            viewBinding?.mList2?.layoutManager = manager
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivtyRecyclerHorizontalMoreBinding.inflate(LayoutInflater.from(this))
        viewBinding?.root?.let {
            return it.rootView
        }
        return null
    }

    class RvAdapter(var context: Context, var dataList: List<String>) :
        RecyclerView.Adapter<RvAdapter
            .RvViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RvViewHolder {
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.item_pulltorefresh, p0, false)
            return RvViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        override fun onBindViewHolder(p0: RvViewHolder, p1: Int) {
            p0.name.text = dataList[p1]
        }

        class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var name: TextView = itemView.findViewById(R.id.tv_title)
            var image: ImageView = itemView.findViewById(R.id.iv_img)
        }
    }
}
