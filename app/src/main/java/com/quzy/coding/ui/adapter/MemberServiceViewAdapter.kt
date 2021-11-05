package com.quzy.coding.ui.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.ScreenUtils
import com.quzy.coding.R
import com.quzy.coding.bean.GiftCardListBean

/**
 * CreateDate:2021/11/4 20:31
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class MemberServiceViewAdapter : RecyclerView.Adapter<MemberServiceViewAdapter.MyViewHolder>() {
    lateinit var ctx: Context
    lateinit var mServicePhone: String
    var mData: MutableList<GiftCardListBean> = arrayListOf()

    // 埋点参数 返回相对应的bean
    var mPosition: Int = 0
    var totalItemWidth :Int = 0
    companion object {
        val SERVICE_PHONE: Int = 1
    }

    fun setData(ctx: Context, mData: MutableList<GiftCardListBean>, mServicePhone: String) {
        this.ctx = ctx
        this.mServicePhone = mServicePhone
        this.mData = mData
        notifyDataSetChanged()
        totalItemWidth = ScreenUtils.getScreenWidth(ctx) - 17f.dpOfInt * 2 // margin 12+5
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //每列展示5个
        holder.itemView.layoutParams.width = totalItemWidth / 5
        holder.setData(mData[position])
        holder.item_servicehelp_name.text = mData[position].title
        Glide.with(ctx).load(mData[position].image).into(holder.item_servicehelp_img)
        holder.tv_desc.text = mData[position].desc
        //右上角角标
        if (!TextUtils.isEmpty(mData[position].bubbledesc)) {
            holder.tvTag.text = mData[position].bubbledesc
            holder.tvTag.visibility = View.VISIBLE
        }else {
            holder.tvTag.visibility = View.GONE
        }
        if (mData[position].action == SERVICE_PHONE) {
            holder.itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + if (TextUtils.isEmpty(mServicePhone)) ctx.getString(R.string.CustomerServicePhone) else mServicePhone)
            }
        } else {
            holder.itemView.setOnClickListener {
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(
                ctx).inflate(R.layout.membercenter_servicehelp_item, parent,
                false))
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: GiftCardListBean?){}
        var item_servicehelp_img: ImageView = itemView.findViewById(R.id.item_servicehelp_img)
        var item_servicehelp_name: TextView = itemView.findViewById(R.id.item_servicehelp_name)
        var tv_desc :TextView = itemView.findViewById(R.id.tv_servicehelp_desc)
        val tvTag :TextView = itemView.findViewById(R.id.tv_tag)
    }


}