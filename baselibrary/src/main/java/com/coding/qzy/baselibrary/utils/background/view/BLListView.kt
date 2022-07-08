package com.coding.qzy.baselibrary.utils.background.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView
import com.coding.qzy.baselibrary.utils.background.BackgroundFactory

class BLListView : ListView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        BackgroundFactory.setViewBackground(context, attrs, this)
    }
}