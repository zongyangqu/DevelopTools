package com.quzy.coding.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.model.MemberType
import com.quzy.coding.bean.model.MemberTypeWithData
import com.quzy.coding.ui.holder.MemberUserHeaderViewholder

/**
 * CreateDate:2021/10/20 10:47
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class WaterfallComplexKotAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var datas: ArrayList<MemberTypeWithData>? = null
    var headerViewholder: MemberUserHeaderViewholder? = null
    var assetInfo: AssetInfo? = null
    var topSize: Int = 0
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = View(parent.context)
        when(viewType){
            MemberType.USERHEADER.ordinal -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_membercenter_head, parent, false)
                val headerViewholder = MemberUserHeaderViewholder(view)
                this.headerViewholder = headerViewholder
                return headerViewholder
            }
        }
        return MemberUserHeaderViewholder(view)
        //return MemberDefaultViewholder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return datas?.get(position)?.type?.ordinal ?: MemberType.USERHEADER.ordinal
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}