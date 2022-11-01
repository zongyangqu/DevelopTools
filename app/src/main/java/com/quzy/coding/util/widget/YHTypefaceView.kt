package com.quzy.coding.util.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.apkfuns.logutils.LogUtils
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.util.Constants
import java.io.File

/**
 * CreateDate:2022/9/23 15:54
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.util.widget
 * @Description: TextView加载字体库
 */
class YHTypefaceView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        initFontStyle()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        context, attrs
    ) {
        initFontStyle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        initFontStyle()
    }

    private fun initFontStyle() {
        // typeface = Typeface.createFromAsset(BaseApplication.getContext().assets, "font/fzzzhjt.TTF")
        try {
            if (typefaceTransformer == null) {
                var apk = File(BaseApplication.getContext().cacheDir.absolutePath + Constants.DOWNLOAD_TYPEFACE_NAME)
                LogUtils.tag(Constants.LOG_TAG).d("typeface is null")
                LogUtils.tag(Constants.LOG_TAG).d("apk path" + apk.absolutePath)
                typefaceTransformer = Typeface.createFromFile(apk)
            }
            typeface = typefaceTransformer
        } catch (exception: Exception) {
            typeface = Typeface.DEFAULT_BOLD
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun setText(text: CharSequence, type: BufferType) {
        super.setText(text, type)
    }

    fun setNormalText(text: CharSequence?) {
        typeface = Typeface.DEFAULT_BOLD
        setText(text)
    }

    companion object {
        var typefaceTransformer: Typeface? = null
    }
}
