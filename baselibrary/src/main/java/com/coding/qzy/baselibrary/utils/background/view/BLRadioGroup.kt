package com.coding.qzy.baselibrary.utils.background.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioGroup
import com.coding.qzy.baselibrary.utils.background.BackgroundFactory

class BLRadioGroup : RadioGroup {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        BackgroundFactory.setViewBackground(context, attrs, this)
    }
}