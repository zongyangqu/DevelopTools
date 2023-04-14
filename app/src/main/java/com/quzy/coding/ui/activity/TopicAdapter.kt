package com.quzy.coding.ui.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.bean.TopicBean

/**
 * CreateDate:2023/4/13 19:09
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class TopicAdapter(context: Context, data: List<TopicBean>) :
    RecyclerView.Adapter<TopicAdapter.TopicViewHolder?>() {
    private val mData: List<TopicBean>
    private var onItemClickListener: OnItemClickListener? = null
    private val mContext: Context
    private val columnCount = 5
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        return TopicViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_topic_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val item: TopicBean = mData[position] ?: return
        holder.title.setText(item.getTitle())
        val top =
            mContext.resources.getDrawable(if (item.getIcon() === 0) R.mipmap.icon_home_99 else item.getIcon())
        holder.title.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null)
        holder.itemView.setOnClickListener {
            if (onItemClickListener != null) {
                onItemClickListener!!.onTopicItemClick(item)
            }
        }
        val params = holder.itemView.layoutParams as GridLayoutManager.LayoutParams
        val screenWidth = mContext.resources.displayMetrics.widthPixels //屏幕宽度
        params.width = screenWidth / columnCount
        holder.itemView.layoutParams = params
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    inner class TopicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView

        init {
            title = view.findViewById(R.id.title)
        }
    }

    interface OnItemClickListener {
        fun onTopicItemClick(position: TopicBean?)
    }

    init {
        mData = data
        mContext = context
    }
}
