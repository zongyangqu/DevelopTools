package com.quzy.coding.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.bean.MyData

/**
 * CreateDate:2021/10/9 17:50
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class MoreItemKotlinAdapter(private val context: Context, private val list: MutableList<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val MASTER=1
    private val MEASURE=2
    private val BUDGET=3
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View?
        var holder:RecyclerView.ViewHolder?=null
        when(viewType){
            MASTER->{
                view = LayoutInflater.from(context).inflate(R.layout.item_master_list, parent, false)
                holder= MasterViewHolder(view)
            }
            MEASURE->{
                view = LayoutInflater.from(context).inflate(R.layout.item_measure_list, parent, false)
                holder=  MeasureViewHolder(view)
            }
            BUDGET->{
                view = LayoutInflater.from(context).inflate(R.layout.item_budget_list, parent, false)
                holder=  BudgetViewHolder(view)
            }
        }
        return holder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MasterViewHolder->{
                val bean=list as MutableList<MyData.MasterBean>
                holder.textName.text=bean[position].title
                holder.textWork.text=bean[position].dictName
                holder.textPhone.text=bean[position].telphone
                // holder.textTime.text=bean[pos].date.substring(0,11)
                holder.imageType.visibility=View.VISIBLE
                holder.imageType.setImageResource(R.mipmap.mytecher)
                when(bean[position].isVip){
                    "是"->holder.imageVip.visibility=View.VISIBLE
                    "否"->holder.imageVip.visibility=View.INVISIBLE
                }
            }
            is MeasureViewHolder->{
                val bean=list as MutableList<MyData.MeasureBean>
                holder.textName.text=bean[position].title
                holder.textTime.text=bean[position].date
                holder.imageType.visibility=View.VISIBLE
                holder.imageType.setImageResource(R.mipmap.mymeasure)
                when(bean[position].havePay){
                    "是"->{
                        holder.textPay.visibility=View.GONE
                        holder.textStatus.visibility=View.GONE
                        holder.textStatus.text="已支付"
                    }
                    "否"->{
                        holder.textPay.visibility=View.VISIBLE
                        holder.textStatus.visibility=View.GONE
                    }
                }
            }
            is BudgetViewHolder->{
                val bean=list as MutableList<MyData.BudgetBean>
                holder.textName.text=bean[position].title
                holder.textTime.text=bean[position].date
                holder.imageType.visibility=View.VISIBLE
                holder.imageType.setImageResource(R.mipmap.mybudget)
            }
        }

        if (mListener != null) {
            holder.itemView.setOnClickListener { view ->
                mListener!!.onItemClick(view, holder.layoutPosition)
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        when(list[position]){
            is MyData.BudgetBean->return BUDGET
            is MyData.MeasureBean->return MEASURE
            is MyData.MasterBean->return MASTER
        }

        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MasterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName: TextView =itemView.findViewById(R.id.text_name)
        val textWork: TextView =itemView.findViewById(R.id.text_work)
        val textPhone: TextView =itemView.findViewById(R.id.text_phone)
        val textTime: TextView =itemView.findViewById(R.id.text_time)
        val textDel: TextView =itemView.findViewById(R.id.text_delete)
        val imageVip: ImageView =itemView.findViewById(R.id.image_vip)
        val imageType: ImageView =itemView.findViewById(R.id.image_type)

    }
    class MeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName: TextView =itemView.findViewById(R.id.text_name)
        val textTime: TextView =itemView.findViewById(R.id.text_time)
        val textStatus: TextView =itemView.findViewById(R.id.text_status)
        val textPay: TextView =itemView.findViewById(R.id.text_pay)
        val textDel: TextView =itemView.findViewById(R.id.text_delete)
        val imageType: ImageView =itemView.findViewById(R.id.image_type)


    }
    class BudgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName: TextView =itemView.findViewById(R.id.text_name)
        val textTime: TextView =itemView.findViewById(R.id.text_time)
        val textDel: TextView =itemView.findViewById(R.id.text_delete)
        val textExport: TextView =itemView.findViewById(R.id.text_export)
        val textShare: TextView =itemView.findViewById(R.id.text_share)
        val imageType: ImageView =itemView.findViewById(R.id.image_type)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, pos: Int)
    }

    fun setOnItemClickListener(mListener: OnItemClickListener) {
        this.mListener = mListener
    }
}