package com.quzy.coding.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivtyRecyclerHorizontalMoreBinding

/**
 * CreateDate:2022/6/17 11:42
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class RecyclerHorizontalMoreActivity : BaseActivity() {

    var viewBinding: ActivtyRecyclerHorizontalMoreBinding? = null
    override fun onViewCreated() {
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

    class RvAdapter(var context: Context, var dataList: List<String>) : RecyclerView.Adapter<RvAdapter
        .RvViewHolder>() {
        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RvViewHolder {
            val view: View = LayoutInflater.from(context).inflate(R.layout.item_pulltorefresh, p0, false)
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
