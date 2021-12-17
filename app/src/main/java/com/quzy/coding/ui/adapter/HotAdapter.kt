package com.quzy.coding.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coding.qzy.baselibrary.base.recyclerview.adapter.BaseRecyclerItemTypeAdapter
import com.coding.qzy.baselibrary.base.recyclerview.holder.RecyclerViewHolder
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.Ware
import com.quzy.coding.ui.SITEM_NONE
import com.quzy.coding.ui.SITEM_RESULT
import com.quzy.coding.ui.SITEM_TITLE
import com.quzy.coding.ui.holder.ProductShow1ViewHolder
import com.quzy.coding.ui.holder.ProductShow2ViewHolder
import com.quzy.coding.ui.holder.ProductShow3ViewHolder
import com.quzy.coding.util.ISearchResult

/**
 * CreateDate:2021/11/23 18:45
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class HotAdapter(val mISearchResult: ISearchResult) : BaseRecyclerItemTypeAdapter(){
    private var data:List<Ware>?=null
    val inflater: LayoutInflater = LayoutInflater.from(BaseApplication.getContext())

    override fun onBindViewHolder(holder: RecyclerViewHolder, position:Int){
        val ware=data!![position]
        when(holder){
            is ProductShow1ViewHolder ->{
                holder.bindData(ware)
            }
            is ProductShow2ViewHolder ->{
                holder.bindData(ware)
            }
            is ProductShow3ViewHolder ->{
                holder.bindData(ware)
            }
        }
    }

    override fun getViewHolder(itemView: View, viewType:Int): RecyclerViewHolder?{
        when(viewType){
            SITEM_RESULT ->{
                return ProductShow1ViewHolder(itemView,mISearchResult)
            }
            SITEM_NONE ->return ProductShow2ViewHolder(itemView,mISearchResult)
            SITEM_TITLE ->return ProductShow3ViewHolder(itemView,mISearchResult)

        }
        return null
    }

    override fun getItemView(viewType:Int,parent: ViewGroup?): View?{
        when(viewType){
            SITEM_RESULT ->return inflater.inflate(R.layout.product_show_adapter_horizontal,parent,false)
            SITEM_NONE ->return inflater.inflate(R.layout.product_show_adapter2_horizontal,parent,false)
            SITEM_TITLE ->return inflater.inflate(R.layout.product_show_adapter3_horizontal,parent,false)
        }
        return null
    }


    override fun getItemCount():Int{
        return data!!.size
    }

    override fun getItemViewType(position:Int):Int{
        return data?.get(position)?.type?:1
    }

    fun refresh(datas:List<Ware>?){
        data=datas
        notifyDataSetChanged()
    }

}