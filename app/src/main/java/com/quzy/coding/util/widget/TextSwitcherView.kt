package com.quzy.coding.util.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.*
import com.quzy.coding.R
import com.quzy.coding.bean.HintBean
import org.jetbrains.anko.runOnUiThread
import java.util.*

/**
 * CreateDate:2021/12/6 16:27
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description:
 */
class TextSwitcherView(context: Context, attrs: AttributeSet?) :
        LinearLayout(context, attrs), ViewSwitcher.ViewFactory {

    private val textSwitcher = MemTextSwitcher(context)

    private var timer: Timer? = null

    var listener: SwitchContentChangeListener?=null

    private var list: List<HintBean>? = null

    var currentIndex = 0
    var currentClickIndex = 0

    var currentColor: Int = 0

    companion object {
        const val DURATION: Long = 500L
        const val SCHEDULE_PERIOD: Long = 4000L
    }

    init {
        textSwitcher.setFactory(this)
        createAnimation()
        val params = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        addView(textSwitcher, params)
        currentColor = R.color.subMediumBlackColor
    }

    private fun createAnimation() {
        var alphaAnimation: AlphaAnimation?
        var translateAnimation: TranslateAnimation?
        var h = textSwitcher.height
        if (h <= 0) {
            textSwitcher.measure(0, 0)
            h = textSwitcher.measuredHeight
        }
        val inAnimationSet = AnimationSet(true)
        val outAnimationSet = AnimationSet(true)
        alphaAnimation = AlphaAnimation(0f, 1f)
        translateAnimation = TranslateAnimation(
                Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, h.toFloat(), Animation.ABSOLUTE, 0f
        )

        inAnimationSet.addAnimation(alphaAnimation)
        inAnimationSet.addAnimation(translateAnimation)
        inAnimationSet.duration = DURATION

        alphaAnimation = AlphaAnimation(1f, 0f)
        translateAnimation = TranslateAnimation(
                Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, 0f,
                Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, -h.toFloat()
        )

        outAnimationSet.addAnimation(alphaAnimation)
        outAnimationSet.addAnimation(translateAnimation)
        outAnimationSet.duration = DURATION
        outAnimationSet.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                currentClickIndex = currentIndex
            }

            override fun onAnimationStart(animation: Animation?) {
                currentClickIndex = if (currentIndex == 0) {
                    list!!.size - 1
                } else {
                    currentIndex - 1
                }
            }

        })
        textSwitcher.inAnimation = inAnimationSet
        textSwitcher.outAnimation = outAnimationSet
    }

    fun getCurrentData(): HintBean? {
        return list?.getOrNull(currentClickIndex)
    }

    fun addListener(listener: SwitchContentChangeListener?){
        this.listener=listener
    }

    override fun makeView(): View {
        val tv = TextView(context)
        tv.textSize = 14f
        val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        tv.layoutParams = params
        tv.gravity = Gravity.CENTER
        tv.setTextColor(resources.getColor(R.color.color_666666))
        return tv
    }

    fun setData(list: List<HintBean>) {
        currentIndex = 0
        currentClickIndex= 0
        this.list = list

        if (list.size == 1) {
            if(timer!=null){
                timer!!.cancel()
                timer=null
            }
            currentClickIndex = 0
            listener?.apply {
                currentContent(list[0].keyWord?:"")
            }
            textSwitcher.setTextAndColor(list[0].content, currentColor)
        } else {
            currentIndex = 0
            currentClickIndex= 0
            if (timer == null) {
                timer = Timer()
                timer
                //timer?.schedule(task, 0, SCHEDULE_PERIOD)
                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        context.runOnUiThread {
                            if (currentIndex < ((list?.size ?: 0) - 1)) {
                                currentIndex++
                            } else {
                                currentIndex = 0
                            }
                            if(currentIndex<list.size) {
                                val data = list.get(currentIndex)
                                listener?.apply {
                                    currentContent(data.keyWord?:"")
                                }
                                textSwitcher.setTextAndColor(data.content, currentColor)
                            }
                        }
                    }
                }, 0, SCHEDULE_PERIOD)
            }
        }
    }

    fun setTextColor(color: Int) {
        currentColor = color
        textSwitcher.setCurrentTextColor(color)
        textSwitcher.setNextTextColor(color)
    }

    var task = object : TimerTask() {
        override fun run() {
            context.runOnUiThread {
                if (currentIndex < ((list?.size ?: 0) - 1)) {
                    currentIndex++
                } else {
                    currentIndex = 0
                }
                val data = list?.getOrNull(currentIndex)
                listener?.apply {
                    currentContent(data?.keyWord ?: "")
                }
                textSwitcher.setTextAndColor(data?.keyWord ?: "", currentColor)
            }
        }
    }

    interface SwitchContentChangeListener{
        fun currentContent(content:String)
    }
}

class MemTextSwitcher : TextSwitcher {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
            context,
            attrs
    ) {
    }

    fun setTextAndColor(text: CharSequence?, color: Int) {
        val t = nextView as TextView
        t.text = text ?:""
        t.textSize = 12f
        t.setTextColor(color)
        showNext()
    }

    fun setNextTextColor(color: Int) {
        val t = nextView as TextView
        t.setTextColor(color)
    }

    fun setCurrentTextColor(color: Int) {
        (currentView as TextView).setTextColor(color)
    }
}