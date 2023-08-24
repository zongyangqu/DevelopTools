package com.quzy.coding.ui.activity

import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityBottomDialogBinding
import com.quzy.coding.databinding.ActivityShapeBackgroundBinding
import com.quzy.coding.util.DrawableUtils
import com.quzy.coding.util.ResourceUtil
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.tv_day_unit


/**
 * CreateDate:2023/5/24 15:04
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class BottomDialogActivity : BaseActivity() {

    var viewBinding: ActivityBottomDialogBinding? = null

    override fun onViewCreated() {

    }



    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityBottomDialogBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

}