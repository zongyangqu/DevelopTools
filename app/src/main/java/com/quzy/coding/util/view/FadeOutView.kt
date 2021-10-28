package com.quzy.coding.util.view

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.quzy.coding.R
import com.quzy.coding.bean.TagBean
import com.quzy.coding.bean.TagCell
import com.quzy.coding.util.widget.TagView
import org.jetbrains.anko.dip
import java.lang.StringBuilder

/**
 * CreateDate:2021/10/25 11:49
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.view
 * @Description:
 */
class FadeOutView(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    init {
        gravity = Gravity.CENTER_VERTICAL
    }

    /**
     * @method addChildWithView
     * @description 根据View的集合添加标签
     * @date: 2020/7/27 10:37 AM
     * @author: ZhaoXuan.Zeng
     * @param [viewList, showFade]
     * @return
     */
    fun addChildWithView(viewList: List<View>, showFade: Boolean = true) {
        viewList.forEach continuing@{ view ->
            if (view is TextView) {
                view.setSingleLine()
            }
            view.id = childCount + 1
            val params = RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            if (childCount > 0) {
                val lastid = getChildAt(childCount - 1).id
                params.addRule(RelativeLayout.RIGHT_OF, lastid)
                params.setMargins(dip(5), 0, 0, 0)
            }
            params.addRule(RelativeLayout.CENTER_VERTICAL)
            addView(view, params)
        }
        if (showFade) {
            addFadeView()
        }
    }

    /**
     * @method addChildWithTagBean
     * @description 根据TagBean列表设置标签
     * @date: 2020/7/27 10:46 AM
     * @author: ZhaoXuan.Zeng
     * @param [listView, showFade, trackBuilder]
     * @return
     */
    fun addChildWithTagBean(beanList: List<TagBean>, showFade: Boolean = true, trackBuilder: StringBuilder? = null) {
        beanList.forEach continuing@{ bean ->
            if (TextUtils.isEmpty(bean.type) || TextUtils.isEmpty(bean.text))
                return@continuing
            val tag = TagView(context, null)
            val support = tag.setTagData(bean)
            if (!support) {
                return@continuing
            }
            tag.setSingleLine()
            tag.id = childCount + 1
            val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            if (childCount > 0) {
                val lastId = getChildAt(childCount - 1).id
                params.addRule(RIGHT_OF, lastId)
                params.setMargins(dip(5), 0, 0, 0)
            }
            params.addRule(CENTER_VERTICAL)
            addView(tag, params)
            if (bean.text != " ") {
                trackBuilder?.append(bean.text)
                trackBuilder?.append(',')
            }
        }
        if (showFade) {
            addFadeView()
        }
        if (!trackBuilder.isNullOrEmpty() && trackBuilder.last() == ',') {
            trackBuilder.deleteCharAt(trackBuilder.length - 1)
        }
    }


    /**
     * @method addFadeView
     * @description 添加蒙层
     * @date: 2020/7/27 2:16 PM
     * @author: ZhaoXuan.Zeng
     * @param []
     * @return
     */
    private fun addFadeView() {
        val fadeView = View(context)
        fadeView.id = R.id.fade_view_id
        fadeView.setBackgroundResource(R.drawable.shape_fadeout)
        val params = RelativeLayout.LayoutParams(dip(15), LayoutParams.MATCH_PARENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        addView(fadeView, params)
    }
}
