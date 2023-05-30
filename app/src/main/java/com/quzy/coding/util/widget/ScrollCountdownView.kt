package com.quzy.coding.util.widget

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ViewSwitcher
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.coding.qzy.baselibrary.utils.guidelayer.util.UIUtils
import com.quzy.coding.R
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.ts_day
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.ts_hour
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.ts_minute
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.ts_second
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.tv_colon_first
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.tv_colon_second
import kotlinx.android.synthetic.main.widget_scroll_countdown_layout.view.tv_day_unit
import java.lang.ref.WeakReference

/**
 * CreateDate:2023/5/24 15:08
 * @author: zongyang qu
 * @Package： com.coding.qzy.baselibrary.widget
 * @Description: 日期倒计时
 */
class ScrollCountdownView : FrameLayout, ViewSwitcher.ViewFactory {

    // 倒计时结束的时间，毫秒
    private var endTime: Long = 0

    // 预约开始倒计时的时间
    private var startTime: Long = 0

    // 还剩多少天
    private var lastDays = 0.toLong()

    // 还剩多少小时, Long
    private var lastHours = 0.toLong()

    // 还剩多少分钟, Long
    private var lastMinutes = 0.toLong()

    // 还剩多少秒, Long
    private var lastSeconds = 0.toLong()

    // 还剩多少天, 字符串
    private var lastDaysText =
        TIME_INITIAL_VALUE

    // 还剩多少小时, 字符串
    private var lastHoursText =
        TIME_INITIAL_VALUE

    // 还剩多少分钟, 字符串
    private var lastMinutesText =
        TIME_INITIAL_VALUE

    // 还剩多少秒, 字符串
    private var lastSecondsText =
        TIME_INITIAL_VALUE
    private val handler =
        MyHandler(
            this
        )

    // 当前是否正在倒计时
    private var isCountDowning = false

    // 倒计时结束监听回调
    private var countDownListener: OnCountDownListener? = null

    // 时间变更监听回调
    private var timeChangedListener: OnTimeChangedListener? = null

    private var digitSize = 12f

    private var textSize: Float = 12f

    private var textMargin: Float = 3f

    private var typeBold: Boolean = false

    private var textColor: Int = 0

    private var backgroundResId: Int = 0

    private var textHeight: Int = 0

    private var textWidth: Int = 0

    private var showDay = false // 是否展示天
    private var dayTextFormat = false
    // 文字动画，默认开启
    private var textAnimation: Boolean = true

    private class MyHandler(view: ScrollCountdownView) : Handler() {
        private val reference = WeakReference<ScrollCountdownView>(view)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val view = reference.get() ?: return
            when (msg.what) {
                0 -> {
                    view.showCountDown()
                    reference.get()
                        ?.let { view.countDownListener?.onCountDown(it.endTime - it.getCurrentTime()) }
                }
                1 -> {
                    if (view.isCurrentTimeReachStartTime()) {
                        // 到达预约的时间了
                        view.countDownListener?.onStartNext()
                    } else {
                        // 没有到预约的时间，继续检测
                        view.checkAppointmentTime()
                    }
                }
                2 -> {
                    view.countDownListener?.onStop()
                }
            }
        }
    }

    companion object {
        private const val TIME_INITIAL_VALUE = "00"

        // 时间的进制，1分钟 = 60秒
        private const val TIME_UNIT = 60

        // 1秒 = 1000毫秒
        private const val TIME_MILLIS = 1000
    }

    constructor(mContext: Context) : super(mContext) {
        init(mContext)
    }

    constructor(mContext: Context, mAttr: AttributeSet) : super(mContext, mAttr) {
        init(mContext, mAttr)
    }

    constructor(mContext: Context, mAttr: AttributeSet, defStyleAttr: Int) : super(
        mContext,
        mAttr,
        defStyleAttr
    ) {
        init(mContext, mAttr)
    }

    private fun init(mContext: Context, mAttr: AttributeSet? = null) {
        LayoutInflater.from(context).inflate(R.layout.widget_scroll_countdown_layout, this)
        val defaultSize = UIUtils.dip2px(mContext, 12f).toFloat()
        val defaultMargin = UIUtils.dip2px(mContext, 3f).toFloat()
        textSize = defaultSize
        mAttr?.let {
            val typedArray = mContext.obtainStyledAttributes(it, R.styleable.YHScrollCountdownView)
            digitSize =
                typedArray.getDimension(R.styleable.YHScrollCountdownView_digit_size, defaultSize)
            textSize =
                typedArray.getDimension(
                    R.styleable.YHScrollCountdownView_digit_text_size,
                    defaultSize
                )

            textMargin =
                typedArray.getDimension(
                    R.styleable.YHScrollCountdownView_digit_text_margin,
                    defaultMargin
                )

            textHeight = typedArray.getDimensionPixelSize(
                R.styleable.YHScrollCountdownView_digit_text_height,
                0
            )

            textWidth = typedArray.getDimensionPixelSize(
                R.styleable.YHScrollCountdownView_digit_text_width,
                0
            )

            val digitColor = typedArray.getColor(
                R.styleable.YHScrollCountdownView_digit_color,
                ContextCompat.getColor(context, R.color.themeColor)
            )

            textColor = typedArray.getColor(
                R.styleable.YHScrollCountdownView_digit_text_color,
                ContextCompat.getColor(context, R.color.themeColor)
            )

            textAnimation = typedArray.getBoolean(
                R.styleable.YHScrollCountdownView_digit_text_animation,
                true
            )

            backgroundResId =
                typedArray.getResourceId(R.styleable.YHScrollCountdownView_digit_text_background, 0)

            showDay = typedArray.getBoolean(
                R.styleable.YHScrollCountdownView_digit_show_day,
                false
            )

            dayTextFormat = typedArray.getBoolean(
                R.styleable.YHScrollCountdownView_digit_day_text_format,
                false
            )

            if (showDay) {
                ts_day?.visibility = View.VISIBLE
                tv_day_unit?.visibility = View.VISIBLE
            } else {
                ts_day?.visibility = View.GONE
                tv_day_unit?.visibility = View.GONE
            }
            tv_day_unit?.setTextColor(digitColor)
            tv_colon_first?.setTextColor(digitColor)
            tv_colon_second?.setTextColor(digitColor)
            tv_day_unit?.setTextSize(TypedValue.COMPLEX_UNIT_PX, digitSize)
            tv_colon_first?.setTextSize(TypedValue.COMPLEX_UNIT_PX, digitSize)
            tv_colon_second?.setTextSize(TypedValue.COMPLEX_UNIT_PX, digitSize)
            val tv_colon_first_lp: LinearLayout.LayoutParams =
                tv_colon_first.getLayoutParams() as LinearLayout.LayoutParams
            tv_colon_first_lp.marginStart = UIUtils.px2dip(mContext, textMargin)
            tv_colon_first_lp.marginEnd = UIUtils.px2dip(mContext, textMargin)
            tv_day_unit.layoutParams = tv_colon_first_lp
            tv_colon_first.layoutParams = tv_colon_first_lp
            tv_colon_second.layoutParams = tv_colon_first_lp

            typedArray.recycle()
        }

        ts_day.setFactory(this)
        ts_hour.setFactory(this)
        ts_minute.setFactory(this)
        ts_second.setFactory(this)

        // 关闭动画
        if (!textAnimation) {
            ts_day.inAnimation = null
            ts_day.outAnimation = null
            ts_hour.inAnimation = null
            ts_hour.outAnimation = null
            ts_minute.inAnimation = null
            ts_minute.outAnimation = null
            ts_second.inAnimation = null
            ts_second.outAnimation = null
        }
        if (backgroundResId != 0) {
            ViewCompat.setBackground(
                ts_day, AppCompatResources.getDrawable(context, backgroundResId)
            )
            ViewCompat.setBackground(
                ts_hour, AppCompatResources.getDrawable(context, backgroundResId)
            )
            ViewCompat.setBackground(
                ts_minute, AppCompatResources.getDrawable(context, backgroundResId)
            )
            ViewCompat.setBackground(
                ts_second, AppCompatResources.getDrawable(context, backgroundResId)
            )
        }
    }

    /**
     * @methodName setCountDownTextColor
     * @description 设置倒计时文案颜色
     * @param [color]
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/2/17 2:54 下午
     */
    fun setCountDownTextColor(color: Int) {
        textColor = color
    }

    /**
     * @methodName setCountDownTextBg
     * @description 动态控制倒计时文案背景色
     * @param [bgDrawable]
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/2/17 2:34 下午
     */
    fun setCountDownTextBg(bgDrawable: Drawable) {
        ts_day?.background = bgDrawable
        ts_hour?.background = bgDrawable
        ts_minute?.background = bgDrawable
        ts_second?.background = bgDrawable
    }

    /**
     * @methodName setColonColor
     * @description 设置冒号文案渐变颜色
     * @param [linearGradient]
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/2/17 2:48 下午
     */
    fun setColonColor(linearGradient: LinearGradient) {
        tv_day_unit?.paint?.shader = linearGradient
        tv_colon_first?.paint?.shader = linearGradient
        tv_colon_second?.paint?.shader = linearGradient
        tv_day_unit?.invalidate()
        tv_colon_first?.invalidate()
        tv_colon_second?.invalidate()
    }

    /**
     * @methodName setColonColor
     * @description 设置冒号文案颜色
     * @param [color]
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/2/17 2:48 下午
     */
    fun setColonColor(color: Int) {
        tv_day_unit?.setTextColor(color)
        tv_colon_first?.setTextColor(color)
        tv_colon_second?.setTextColor(color)
    }
    /**
     * 设置倒计时结束时间，毫秒
     */
    fun setEndTime(mEndTime: Long) {
        this.endTime = mEndTime
    }

    /**
     * @methodName setShowDay
     * @description 设置是否展示日期
     * @param [showDay]
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/4/29 9:14 上午
     */
    fun setShowDay(showDay: Boolean) {
        if (this.showDay == showDay) {
            return
        }
        this.showDay = showDay
        if (showDay) {
            ts_day?.visibility = View.VISIBLE
            tv_day_unit?.visibility = View.VISIBLE
        } else {
            ts_day?.visibility = View.GONE
            tv_day_unit?.visibility = View.GONE
        }
    }

    /**
     * 开始倒计时
     */
    fun startCountDown() {
        stopCountDown()
        isCountDowning = true
        showCountDown()
    }

    private fun stopCountDown() {
        handler.removeCallbacksAndMessages(null)
    }

    /**
     * 设置预约开始倒计时的时间
     */
    fun setAppointmentTime(mStartTime: Long, mEndTime: Long) {
        this.startTime = mStartTime
        this.endTime = mEndTime
    }

    /**
     * 开始预约倒计时
     */
    fun startAppointmentCountDown() {
        stopCountDown()
        checkAppointmentTime()
    }

    /**
     * 发送Handler检测预约时间
     */
    private fun checkAppointmentTime() {
        val message = Message()
        message.what = 1
        handler.sendMessageDelayed(message, 1000)
    }

    /**
     * 获取当前系统时间
     */
    private fun getCurrentTime(): Long = System.currentTimeMillis()

    /**
     * 判断当前时间是否已经到达预约的倒计时开始时间
     */
    private fun isCurrentTimeReachStartTime(): Boolean = getCurrentTime() >= startTime

    /**
     * 显示倒计时并开始自动递减倒计时
     */
    private fun showCountDown() {
        countDown()
        if (isCountDowning) {
            if (showDay) {
                if (lastDays == 0L && lastMinutes == 0.toLong() && lastSeconds == 0.toLong()) {
                    isCountDowning = false
                }
            } else {
                if (lastHours == 0.toLong() && lastMinutes == 0.toLong() && lastSeconds == 0.toLong()) {
                    isCountDowning = false
                }
            }
        }
        autoCountDown()
    }

    /**
     * 自动按秒递减倒计时
     */
    private fun autoCountDown() {
        if (isCountDowning) {
            handler.sendEmptyMessageDelayed(0, 1000)
        } else {
            handler.sendEmptyMessageDelayed(2, 1000)
        }
    }

    /**
     * 设置倒计时结束的监听
     */
    fun setOnCountDownListener(countDownListener: OnCountDownListener) {
        this.countDownListener = countDownListener
    }

    /**
     * @methodName setOnTimeChangedListener
     * @description 设置时间变更的监听
     * @param [timeChangedListener]
     * @return
     * @author Zeng ZhaoXuan
     * @time 2022/4/29 9:28 上午
     */
    fun setOnTimeChangedListener(timeChangedListener: OnTimeChangedListener) {
        this.timeChangedListener = timeChangedListener
    }

    /**
     * 计算并显示倒计时的时分秒到TextView
     */
    private fun countDown() {
        val currentTimeMillis = System.currentTimeMillis()
        val lastDay = lastDaysText
        val lastHour = lastHoursText
        val lastMinute = lastMinutesText
        val lastSecond = lastSecondsText
        if (currentTimeMillis <= endTime) {
            val mLastMilliSeconds = endTime - currentTimeMillis
            if (mLastMilliSeconds > 0) {
                lastDays = mLastMilliSeconds / (24 * TIME_UNIT * TIME_UNIT * TIME_MILLIS)
                if (lastDays == 0L) {
                    setShowDay(false)
                }
                if (showDay) {
                    lastHours = (mLastMilliSeconds / TIME_MILLIS - lastDays * 24 * TIME_UNIT * TIME_UNIT) / (TIME_UNIT * TIME_UNIT)
                    lastMinutes = (mLastMilliSeconds / TIME_MILLIS - lastDays * 24 * TIME_UNIT * TIME_UNIT - lastHours * TIME_UNIT * TIME_UNIT) / TIME_UNIT
                    lastSeconds = mLastMilliSeconds / TIME_MILLIS - lastDays * 24 * TIME_UNIT * TIME_UNIT - lastHours * TIME_UNIT * TIME_UNIT - lastMinutes * TIME_UNIT
                    lastDaysText = lastDays.toString() // 天不加0
                    lastHoursText = if (lastHours < 10) {
                        "0$lastHours"
                    } else {
                        lastHours.toString()
                    }
                    lastMinutesText = if (lastMinutes < 10) {
                        "0$lastMinutes"
                    } else {
                        lastMinutes.toString()
                    }
                    lastSecondsText = if (lastSeconds < 10) {
                        "0$lastSeconds"
                    } else {
                        lastSeconds.toString()
                    }
                } else {
                    lastDays = 0L
                    lastHours = mLastMilliSeconds / (TIME_UNIT * TIME_UNIT * TIME_MILLIS)
                    lastMinutes =
                        (mLastMilliSeconds / TIME_MILLIS - lastHours * TIME_UNIT * TIME_UNIT) / TIME_UNIT
                    lastSeconds =
                        mLastMilliSeconds / TIME_MILLIS - lastHours * TIME_UNIT * TIME_UNIT - lastMinutes * TIME_UNIT
                    lastDaysText = "0"
                    lastHoursText = if (lastHours < 10) {
                        "0$lastHours"
                    } else {
                        lastHours.toString()
                    }
                    lastMinutesText = if (lastMinutes < 10) {
                        "0$lastMinutes"
                    } else {
                        lastMinutes.toString()
                    }
                    lastSecondsText = if (lastSeconds < 10) {
                        "0$lastSeconds"
                    } else {
                        lastSeconds.toString()
                    }
                }
            } else {
                cleanCountDown()
            }
        } else {
            // 过期了
            cleanCountDown()
        }
        setCountDownText(
            lastDay != lastDaysText,
            lastHour != lastHoursText,
            lastMinute != lastMinutesText,
            lastSecond != lastSecondsText
        )
        timeChangedListener?.onTimeChanged(
            lastDay != lastDaysText,
            lastHour != lastHoursText,
            lastMinute != lastMinutesText,
            lastSecond != lastSecondsText
        )
    }

    /**
     * 倒计时显示归零
     */
    private fun cleanCountDown() {
        lastDaysText = "0"
        lastHoursText = TIME_INITIAL_VALUE
        lastMinutesText = TIME_INITIAL_VALUE
        lastSecondsText = TIME_INITIAL_VALUE
    }

    private fun getDayFormat(lastDaysText :String) :String{
        return if (dayTextFormat && UIUtils.isNumber(lastDaysText) && (lastDaysText.toInt() ?: 0) < 10) {
            "0$lastDaysText"
        } else {
            lastDaysText
        }
    }

    /**
     * 将计算好的时分秒显示到TextView
     */
    private fun setCountDownText(
        updateDay: Boolean,
        updateHour: Boolean,
        updateMinute: Boolean,
        updateSecond: Boolean
    ) {
        if (showDay) {
            if (updateDay) {
                ts_day.setText(getDayFormat(lastDaysText))
            } else {
                ts_day.setCurrentText(getDayFormat(lastDaysText))
            }
        }
        if (updateHour) {
            ts_hour.setText(lastHoursText)
        } else {
            ts_hour.setCurrentText(lastHoursText)
        }
        if (updateMinute) {
            ts_minute.setText(lastMinutesText)
        } else {
            ts_minute.setCurrentText(lastMinutesText)
        }
        if (updateSecond) {
            ts_second.setText(lastSecondsText)
        } else {
            ts_second.setCurrentText(lastSecondsText)
        }
    }

    /**
     * 倒计时结束的监听接口
     */
    interface OnCountDownListener {
        // 当前场次倒计时结束了
        fun onStop()

        // 到达预约开始倒计时的时间了
        fun onStartNext()

        /**
         * 每次倒数回调一次
         * @param leftTime 剩余时间
         */
        fun onCountDown(leftTime: Long)
    }

    interface OnTimeChangedListener {
        /**
         * 每次倒数回调一次
         * @param updateDay 日期改变
         * @param updateHour 小时改变
         * @param updateMinute 分钟改变
         * @param updateSecond 秒改变
         */
        fun onTimeChanged(updateDay: Boolean, updateHour: Boolean, updateMinute: Boolean, updateSecond: Boolean)
    }

    override fun makeView(): View {
        val text = PriceFontView(context)
        val params: LayoutParams
        val padding = 1f.dpOfInt
        if (textHeight == 0) {
            params = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            text.setPadding(padding, padding, padding, 0)
            text.includeFontPadding = false
        } else {
            params = LayoutParams(if (textWidth == 0) textHeight else textWidth, textHeight)
            text.setPadding(0, 2f.dpOfInt, 0, 0)
        }

        text.layoutParams = params
        text.gravity = Gravity.CENTER
        text.ellipsize = TextUtils.TruncateAt.END
        text?.setSingleLine(true)

        text.setTextColor(textColor)
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)

        return text
    }
}
