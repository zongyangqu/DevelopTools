package com.quzy.coding.ui.holder

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.ScreenUtils
import com.quzy.coding.bean.SimpleActivityTextInfo
import com.quzy.coding.util.widget.spanOnText

/**
 * CreateDate:2021/10/28 16:28
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
open class MemberActivityCardBaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    init {
        val params = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        params.width = (ScreenUtils.getScreenWidth(itemView.context) / 2 - ScreenUtils.dp2px(itemView.context,10))
        params.height = ((ScreenUtils.getScreenWidth(itemView.context) -33f.dpOfInt) / 34f * 25).toInt()
        itemView.layoutParams = params
    }

    /**
     * 设置标题
     */
    fun setTitleInfo(textInfo: SimpleActivityTextInfo?, textView: TextView?) {
        if(textInfo != null && textInfo.activitytext?.isNotEmpty() == true) {
            textView?.visibility = View.VISIBLE
            textView?.text = textInfo.spanOnText()
        }else {
            textView?.visibility = View.GONE
        }

    }

    /**
     * 点击事件(跳转及埋点)
     */
    fun handleClick(jumpUrl:String?, toast: String?) {
    }

    /**
     * 设置左下角、右下角的角标 2张优惠券
     */
    fun setBubbleTitle(view: TextView, bubbleTitle:String?) {
        if(TextUtils.isEmpty(bubbleTitle)) {
            view?.visibility = View.GONE
        }else {
            view?.visibility = View.VISIBLE
            view?.text = bubbleTitle
        }
    }

    /**
     * 设置图片
     */
    fun setImage(imgUrl: String?, view: ImageView) {
        if (!TextUtils.isEmpty(imgUrl)) {
            view.visibility = View.VISIBLE
            Glide.with(itemView.context).load(imgUrl).into(view)
        } else {
            view.visibility = View.GONE
        }
    }


}
