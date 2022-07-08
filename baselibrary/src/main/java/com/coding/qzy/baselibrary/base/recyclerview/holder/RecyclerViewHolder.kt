package com.coding.qzy.baselibrary.base.recyclerview.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.coding.qzy.baselibrary.base.recyclerview.util.toView

/**
 * CreateDate:2021/12/17 10:57
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.base.recyclerview.holder
 * @Description:
 */
open class RecyclerViewHolder(protected var mParentView: View) : RecyclerView.ViewHolder(mParentView),
        IViewHolder {

    constructor(context: Context, layoutId: Int) : this(layoutId.toView(context))

    var mSuperContext: Context = mParentView.context
}