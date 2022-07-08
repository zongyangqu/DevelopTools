package com.quzy.coding.util.widget

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.quzy.coding.bean.ActivityTextSign
import com.quzy.coding.bean.SimpleActivityTextInfo
import java.lang.Exception

/**
 * 扩展函数
 */
fun SimpleActivityTextInfo.spanOnText(): CharSequence {
    val signs = this.signs
    val text = this.activitytext

    text?.let {
        val title = SpannableString(it)
        try {
            signs?.let { arr ->
                arr.forEach { textSign ->
                    if (textSign.color?.isNotEmpty() == true) {
                        title.setSpan(
                            ForegroundColorSpan(Color.parseColor(textSign.color)),
                            textSign.start,
                            textSign.end + 1,
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                        )
                    }
                    if (textSign.size > 0) {
                        title.setSpan(
                            AbsoluteSizeSpan(textSign.size, true),
                            textSign.start,
                            textSign.end + 1,
                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                        )
                    }
                    if (textSign.weight?.isNotEmpty() == true) {
                        when (textSign.weight) {

                            ActivityTextSign.WEIGHT_BOLD -> title.setSpan(
                                StyleSpan(Typeface.BOLD), textSign.start,
                                textSign.end + 1,
                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                            )

                            else ->
                                title.setSpan(
                                    StyleSpan(Typeface.NORMAL), textSign.start,
                                    textSign.end + 1,
                                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                                )
                        }
                    }
                }
            }
        } catch (e: Exception) {
        } finally {
            return title
        }
    }

    return ""
}
