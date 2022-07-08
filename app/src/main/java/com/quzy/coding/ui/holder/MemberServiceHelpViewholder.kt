package com.quzy.coding.ui.holder

import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.text.TextUtils
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.R
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.GiftCardListBean
import com.quzy.coding.bean.GiftCardModel
import com.quzy.coding.bean.PromotionAndFunction
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import com.quzy.coding.ui.adapter.MemberServiceViewAdapter
import com.quzy.coding.util.ResourceUtil
import kotlinx.android.synthetic.main.view_content_service_help.view.*
import java.lang.Exception

/**
 * CreateDate:2021/11/4 20:23
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberServiceHelpViewholder(itemView : View): RecyclerView.ViewHolder(itemView) {

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null
    private var adapter: MemberServiceViewAdapter? = null
    private var mServicePhone  =""

    fun bindData(info: AssetInfo?, fragment: RecyclerViewWaterfallComplexKotActivity?) {
        this.fragment = fragment
        setGridLayoutManager(info)
        info?.apply {
            setServiceData(info!!.page) //下发的grideview数据

            //if (info!!.page != null && info!!.page.functionnew != null && info!!.page.functionnew.list != null) {
            if (info.promotionsAndFunctions != null ) {
                if (adapter == null) {
                    adapter = MemberServiceViewAdapter()
                    setServiceAdapter(adapter)
                }
                //  adapter?.setData(itemView.context, info!!.page.functionnew.list, if (TextUtils.isEmpty(mServicePhone)) itemView.context.getString(R.string.CustomerServicePhone) else mServicePhone)
                adapter?.setData(itemView.context, packagePromotionsAndFunctionsData(info.promotionsAndFunctions), if (TextUtils.isEmpty(mServicePhone)) itemView.context.getString(R.string.CustomerServicePhone) else mServicePhone)

            }
        }
    }




    fun setServiceAdapter(adapter: MemberServiceViewAdapter?) {
        itemView.mamber_gridview.adapter = adapter
    }
    fun setServiceData(model: GiftCardModel?) {
        itemView.apply {
            if (model != null) {
                if (model.banner != null && model.banner.list != null && model.banner.list.size > 0) {
                    iv_invite_friend.visibility = View.VISIBLE
                    fragment?.let { Glide.with(it).load(model.banner.list[0].image).into(iv_invite_friend) }
                    iv_invite_friend.setOnClickListener {

                    }
                } else {
                    iv_invite_friend.visibility = View.GONE
                }
            }
        }
    }


    private fun setGridLayoutManager(info: AssetInfo?) {
        val totalSize = info?.promotionsAndFunctions?.size ?: 0
        if (totalSize in 1..10) {
            // 数量小于等于10个，第一行先排满5个，再排第二行
            // 从icon数量小于等于10个的地址，切换到数量大于10个的地址时，需要移除掉decoration
            removeItemDecoration()
            val manager = GridLayoutManager(itemView.context, 5)
            itemView.mamber_gridview.layoutManager = manager
        } else {
            // 数量大于10个
            val manager: GridLayoutManager =
                    object : GridLayoutManager(itemView.context, 2, HORIZONTAL, false) {
                        override fun canScrollHorizontally(): Boolean {
                            return true
                        }
                    }
            if(itemView.mamber_gridview.itemDecorationCount == 0) {
                itemView.mamber_gridview?.addItemDecoration(scrollBarItemDecoration)
            }
            itemView.mamber_gridview.layoutManager = manager
        }
    }

    /**
     * 重新组装数据
     */
    private fun packagePromotionsAndFunctionsData(list:MutableList<PromotionAndFunction>) : MutableList<GiftCardListBean> {
        if(list.size <= 10) {
            //小于等于10个
            return list.map {
                GiftCardListBean(it.action ?: 0, it.title ?: "", it.desc ?: "", it.image ?: "", it.link ?: "", it.cornerMarkText)
            }.toMutableList()
        }

        var finalList: MutableList<GiftCardListBean> = ArrayList()
        //大于10个时，前10个数据重新组装数据源
        //产品要求大于10个时， 前10个从左往右排（第一屏）第一行：list[0]、list[1]、list[2]、list[3]、list[4]，第二行：list[5]、list[6]、list[7]、list[8]、list[9], 从第二屏开始上下排
        finalList.add(generateGiftCardListBean(list[0]))
        finalList.add(generateGiftCardListBean(list[5]))
        finalList.add(generateGiftCardListBean(list[1]))
        finalList.add(generateGiftCardListBean(list[6]))
        finalList.add(generateGiftCardListBean(list[2]))
        finalList.add(generateGiftCardListBean(list[7]))
        finalList.add(generateGiftCardListBean(list[3]))
        finalList.add(generateGiftCardListBean(list[8]))
        finalList.add(generateGiftCardListBean(list[4]))
        finalList.add(generateGiftCardListBean(list[9]))

        for(i in 10 until list.size) {
            finalList.add(generateGiftCardListBean(list[i]))
        }
        return  finalList
    }

    private fun generateGiftCardListBean(bean: PromotionAndFunction) : GiftCardListBean {
        return GiftCardListBean(bean.action ?:0,bean.title?:"",bean.desc?:"",bean.image?:"",bean.link?:"",bean.cornerMarkText)
    }

    private val scrollBarItemDecoration by lazy {
        object : RecyclerView.ItemDecoration() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onDrawOver(
                    c: Canvas,
                    parent: RecyclerView,
                    state: RecyclerView.State
            ) {
                super.onDrawOver(c, parent, state)
                val barHeight = 3f.dpOfInt
                val scrollWidth = 30f.dpOfInt
                val indicatorWidth = 12f.dpOfInt
                val paddingBottom = 5f.dpOfInt
                val barX = (parent.width / 2 - scrollWidth / 2).toFloat()
                val barY = (parent.height - paddingBottom - barHeight).toFloat()

                val paint = Paint()
                paint.isAntiAlias = true
                paint.color = ResourceUtil.getColor(R.color.color_eeeeee)
                c.drawRoundRect(barX, barY, barX + scrollWidth.toFloat(), barY + barHeight, 2.5f.dp, 2.5f.dp, paint)

                val extent = parent.computeHorizontalScrollExtent()
                val range = parent.computeHorizontalScrollRange()
                val offset = parent.computeHorizontalScrollOffset()
                val maxEndX = (range - extent).toFloat()
                if (maxEndX > 0) {
                    val proportion = offset / maxEndX

                    val scrollableDistance = scrollWidth - indicatorWidth

                    val offsetX = scrollableDistance * proportion
                    paint.color = ResourceUtil.getColor(R.color.themeColor)
                    c.drawRoundRect(barX + offsetX, barY, barX + indicatorWidth.toFloat() + offsetX, barY + barHeight, 2.5f.dp, 2.5f.dp, paint)
                } else {
                    paint.color = ResourceUtil.getColor(R.color.themeColor)
                    c.drawRoundRect(barX, barY, barX + scrollWidth.toFloat(), barY + barHeight, 2.5f.dp, 2.5f.dp, paint)
                }
            }
        }
    }

    /**
     * 删除recyclerview的ItemDecoration
     */
    private fun removeItemDecoration() {
        if (itemView.mamber_gridview?.itemDecorationCount == 0) {
            return
        }
        for (i in 0 until itemView.mamber_gridview.itemDecorationCount) {
            try{
                if (i < itemView.mamber_gridview?.itemDecorationCount ?: 0) {
                    itemView.mamber_gridview?.removeItemDecorationAt(i)
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}