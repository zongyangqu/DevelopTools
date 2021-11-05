package com.quzy.coding.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.yonghui.hyd.member.wigets.loopview.BaseLoopBannerAdapter
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.widget.roundview.RoundConstraintLayout
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.LiveProductBean

/**
 * CreateDate:2021/10/28 21:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class MemberActivityCardLiveAdapter : BaseLoopBannerAdapter<LiveProductBean, MemberActivityCardLiveAdapter.ViewHolder>() {

    companion object{
        const val LIVEING = 101
        const val LIVE_NOTICE = 102
        const val LIVE_BACKUP = 103
    }

    var moduleName :String ?= null


    fun setModule(name :String?) {
        this.moduleName = name
    }

    override fun getLayoutId(viewType: Int) = R.layout.member_item_card_live

    override fun onBind(holder: ViewHolder, data: LiveProductBean, position: Int, pageSize: Int) {

        holder.tv_card_live_title.text = data?.firstTitle
        holder.tv_card_live_desc.text = data?.secondTitle
        holder.tv_card_live_enter.text = data?.desc+">"
        holder.tv_card_live_status.text = data?.liveStatusDesc

        when(data?.liveStatus){
            LIVEING ->{
                Glide.with(BaseApplication.appContext).load(data?.liveIcon).into(holder.iv_live_tips)
                holder.live_view_status.visibility = View.VISIBLE
            }
            LIVE_NOTICE ->{
                holder.live_view_status.visibility = View.GONE
            }
            LIVE_BACKUP ->{
                holder.live_view_status.visibility = View.GONE
            }
        }

        holder.live_entry_view.setOnClickListener {
        }

    }



    override fun createViewHolder(parent: ViewGroup, itemView: View, viewType: Int): ViewHolder {
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_card_live_title: TextView = itemView.findViewById(R.id.tv_card_live_title)
        var tv_card_live_status: TextView = itemView.findViewById(R.id.tv_card_live_status)
        var tv_card_live_enter: TextView = itemView.findViewById(R.id.tv_card_live_enter)
        var tv_card_live_desc: TextView = itemView.findViewById(R.id.tv_card_live_desc)
        var live_view_status: RoundConstraintLayout = itemView.findViewById(R.id.live_view_status)
        var iv_live_tips: ImageView = itemView.findViewById(R.id.iv_live_tips)
        var live_entry_view: LinearLayout = itemView.findViewById(R.id.live_entry_view)

    }


}