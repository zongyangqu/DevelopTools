package com.quzy.coding.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.model.MemberType
import com.quzy.coding.bean.model.MemberTypeWithData
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import com.quzy.coding.ui.holder.*
import com.quzy.coding.util.Constants

/**
 * CreateDate:2021/10/20 10:47
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class WaterfallComplexKotAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var datas: ArrayList<MemberTypeWithData>? = null
    var headerViewholder: MemberUserHeaderViewholder? = null
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null
    private var isFirst = true
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
            MemberType.DEFAULT_CUSTOMER_PROPERTIES.ordinal -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_customer_properties_pager, parent, false)
                return MemberDefaultCustomerPropertiesViewholder(fragment, view)
            }
            MemberType.ORDERFORM.ordinal -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_content_pager, parent, false)
                return MemberOrderFormViewholder(view)
            }
            MemberType.YHJR.ordinal -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_content_yhjr, parent, false)
                return MemberYHJRViewholder(view).also { it.bindView(assetInfo, fragment) }
            }
            MemberType.STOREMSG.ordinal -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_content_store_msg, parent, false)
                return MemberStoreMsgViewholder(view)
            }
            MemberType.DOUBLE_ELEVEN_PROMOTION.ordinal ->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.view_double_eleven_promotions, parent, false)
                return MemberDoubleElevenTypeHolder(view)
            }
        }
        return MemberDefaultViewholder(view)
        //return MemberDefaultViewholder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //super.onBindViewHolder(holder,position)
        when (holder) {
            is MemberUserHeaderViewholder -> {
                holder.bindData(assetInfo, fragment, isFirst)
                if (isFirst) isFirst = false
            }
            is MemberOrderFormViewholder -> {
                // 订单
                holder.bindData(assetInfo, fragment)
            }
            is MemberDefaultCustomerPropertiesViewholder -> {
                holder.bindData(fragment)
            }
            is MemberYHJRViewholder -> {
                // 永辉金融
                holder.bindData(assetInfo, fragment)
            }
            is MemberStoreMsgViewholder -> {
                // 门店信息
                holder.bindData(assetInfo, fragment)
            }
            is MemberDoubleElevenTypeHolder -> {
                holder.bindData(assetInfo,fragment)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return datas?.get(position)?.type?.ordinal ?: MemberType.USERHEADER.ordinal
    }

    override fun getItemCount(): Int {
        return datas?.size ?: 0
    }
}