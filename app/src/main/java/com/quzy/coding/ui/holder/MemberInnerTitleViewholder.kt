package com.quzy.coding.ui.holder

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.quzy.coding.R
import com.quzy.coding.bean.HintBean
import com.quzy.coding.bean.WelfareRemind
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import kotlinx.android.synthetic.main.view_content_inner_title.view.*

/**
 * CreateDate:2021/12/6 16:38
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberInnerTitleViewholder(mLifecycleOwner: LifecycleOwner?, itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(bean: WelfareRemind?, activity: RecyclerViewWaterfallComplexKotActivity?) {

        activity?.let { Glide.with(it).load(bean?.icon).into(itemView.inner_icon) }

        try {
            val text = SpannableStringBuilder(bean?.title?.activitytext)
            bean?.title?.signs?.apply {
                this.forEachIndexed { index, it ->
                    if (index == 0 && it != null && it.start != null && it.end != null) {
                        text.setSpan(StyleSpan(Typeface.BOLD), it.start!!.toInt(), it.end!!.toInt() + 1,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                        text.insert(it.start!!.toInt(), " ")
                        text.insert(it.end!!.toInt() - it.start!!.toInt() + 2 + it.start!!.toInt(), " ")
                    }

                }
            }
            itemView.inner_title.text = text
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (bean != null && !(bean.welfare.isNullOrEmpty())) {
            if (bean.welfare!!.size <= 1) {
                itemView.text_switcher.visibility = View.GONE
                itemView.text_only_one.visibility = View.VISIBLE
                val it = bean.welfare!![0]
                try {
                    val text = SpannableStringBuilder(it.activitytext)
                    it.signs?.apply {
                        this.forEachIndexed { index, it ->
                            if (index == 0 && it != null && it.start != null && it.end != null) {
                                text.setSpan(StyleSpan(Typeface.BOLD), it.start!!.toInt(), it.end!!.toInt() + 1,
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                                text.insert(it.start!!.toInt(), " ")
                                text.insert(it.end!!.toInt() - it.start!!.toInt() + 2 + it.start!!.toInt(), " ")
                            }

                        }
                    }
                    itemView.text_only_one.text = text
                } catch (e: Exception) {
                    e.printStackTrace()
                    itemView.text_only_one.text = ""
                }
            } else {
                itemView.text_switcher.setTextColor(ContextCompat.getColor(itemView.context, R.color.subMediumBlackColor))
                itemView.text_switcher.visibility = View.VISIBLE
                itemView.text_only_one.visibility = View.GONE

                val list = bean.welfare?.map {
                    try {
                        val text = SpannableStringBuilder(it.activitytext)
                        it.signs?.apply {
                            /*this.forEach {item->
                                text.setSpan(StyleSpan(Typeface.BOLD),item.start?.toInt() ?:0,item.end?.toInt()?:0,
                                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                            }*/
                            this.forEachIndexed { index, it ->
                                if (index == 0 && it != null && it.start != null && it.end != null) {
                                    text.setSpan(StyleSpan(Typeface.BOLD), it.start!!.toInt(), it.end!!.toInt() + 1,
                                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                                    text.insert(it.start!!.toInt(), " ")
                                    text.insert(it.end!!.toInt() - it.start!!.toInt() + 2 + it.start!!.toInt(), " ")
                                }

                            }
                        }
                        HintBean(0, "", "", text.toString(), "", "", null, 0, "", true, text)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        HintBean(0, "", "", "", "", "", null, 0, "", true, null)
                    }
                } ?: listOf(
                        HintBean(0, "", "", "", "", "", null, 0, "", true, null)
                )
                itemView.text_switcher.setData(listOf(HintBean(0, "", "", "", "", "", null, 0, "", true, null)))
                itemView.text_switcher.setData(list)
            }


        } else {
            itemView.text_switcher.visibility = View.GONE
            itemView.text_only_one.visibility = View.VISIBLE
            itemView.text_only_one.text = ""
        }
    }

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }
}