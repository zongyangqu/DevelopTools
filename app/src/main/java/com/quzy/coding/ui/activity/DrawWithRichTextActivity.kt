package com.quzy.coding.ui.activity

import android.os.Build
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.webkit.WebSettings
import com.alibaba.android.arouter.utils.MapUtils.isNotEmpty
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.NewPersonGiftProduct
import com.quzy.coding.bean.ProPrice
import com.quzy.coding.databinding.ActivityDrawWithRichtextBinding
import com.quzy.coding.ui.manager.CouponNewCustomerDialogManager
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * CreateDate:2022/7/1 9:37
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 全局弹窗优惠券的富文本展示
 */
class DrawWithRichTextActivity : BaseActivity() {

    var viewBinding: ActivityDrawWithRichtextBinding ? = null
    var n1: Long? = 0
    override fun onViewCreated() {
        // EventBus.getDefault().post(DialogEvent())
        CouponNewCustomerDialogManager.setCurActivity(this)
        CouponNewCustomerDialogManager.doRequestCoupon(this)

        val settings = viewBinding?.webview?.settings
        viewBinding?.webview?.isHorizontalScrollBarEnabled = false
        viewBinding?.webview?.isVerticalScrollBarEnabled = false
        settings?.domStorageEnabled = true;
        settings?.allowFileAccess = true;
        settings?.setAppCacheEnabled(true);
        settings?.domStorageEnabled = true
        settings?.javaScriptEnabled = true
        settings?.loadWithOverviewMode = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }
        viewBinding?.webview?.loadUrl("https://www.jianshu.com/")


        viewBinding?.contentTv?.text = "但是得是的是的是的是的所多多打算等我打完任务人防的夫人夫人个人更广泛的辅导辅导而贷款放款单方块巅峰丰富的减肥的减肥粉粉粉的辅导辅导是得是的是的是的是的所多多打算等我打完任务人防的夫人夫人个人更广泛的辅导辅导而贷款放款单方块巅峰丰富的减肥的减肥粉粉粉的辅导辅导是得是的是的是的是的所多多打算等我打完任务人防的夫人夫人个人更广泛的辅导辅导而贷款放款单方块巅峰丰富的减肥的减肥粉粉粉的辅导辅导是得是的是的是的是的所多多打算等我打完任务人防的夫人夫人个人更广泛的辅导辅导而贷款放款单方块巅峰丰富的减肥的减肥粉粉粉的辅导辅导是得是的是的是的是的所多多打算等我打完任务人防的夫人夫人个人更广泛的辅导辅导而贷款放款单方块巅峰丰富的减肥的减肥粉粉粉的辅导辅导是得是的是的是的是的所多多打算等我打完任务人防的夫人夫人个人更广泛的辅导辅导而贷款放款单方块巅峰丰富的减肥的减肥粉粉粉的辅导辅导是得是的是的是的是的所多多打算等我打完任务人防的夫人夫人个人更广泛的辅导辅导而贷款放款单方块巅峰丰富的减肥的减肥粉粉粉的辅导辅导"
        viewBinding?.contentTv?.movementMethod = ScrollingMovementMethod.getInstance()
        viewBinding?.contentTv?.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
                if(motionEvent?.getAction()==MotionEvent.ACTION_DOWN){
                    //通知父控件不要干扰
                    view?.getParent()?.requestDisallowInterceptTouchEvent(true);
                }
                if(motionEvent?.getAction()==MotionEvent.ACTION_MOVE){
                    //通知父控件不要干扰
                    view?.getParent()?.requestDisallowInterceptTouchEvent(true);
                }
                if(motionEvent?.getAction()==MotionEvent.ACTION_UP){
                    view?.getParent()?.requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }

        })






        val dataList = ArrayList<NewPersonGiftProduct>()
        var p = ProPrice()
        var n = NewPersonGiftProduct()
        p.price = "2.3"
        n.price = p
        dataList.add(n)
        var p1 = ProPrice()
        var n1 = NewPersonGiftProduct()
        p1.price = "1.5"
        n1.price = p1
        dataList.add(n1)
        var p2 = ProPrice()
        var n2 = NewPersonGiftProduct()
        p2.price = "1.5"
        n2.price = p2
        dataList.add(n2)
        var p3 = ProPrice()
        var n3 = NewPersonGiftProduct()
        p3.price = "1.9"
        n3.price = p3
        dataList.add(n3)
        var new = Collections.min(dataList)
        Log.d("zongyang--->min----:", new.price.price)

        var df = SimpleDateFormat("yyyy-MM-dd")
        var date = df.parse("2022-07-19")
        var cal = Calendar.getInstance()
        cal.setTime(date)
        var timestamp = cal.getTimeInMillis()
        Log.d("zongyang-->", "11111111")
        Log.d("zongyang-->", timestamp.toString())

        var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
// 先取得今天的日历日时间
        var calendar: Calendar = GregorianCalendar()
        calendar.setTime(Date())
// 转换得到今天的日期
        var today = sdf.format(calendar.getTime())
// 转换得倒明天的日期
        BaseApplication.appContext
        calendar.add(Calendar.DATE, +1)
        var tomorrow = sdf?.format(calendar.getTime())
        Log.d("zongyang-->", tomorrow.toString())
//        System.out.println(timestamp);
//        System.out.println(timestamp/1000);
        var list1 = mutableListOf<Int>(1, 2)
        var list2 = splitList(list1, 2)
        var s = ""
    }

    override fun getLayoutId(): Int {
        return 0
    }

    fun splitList(messageList: List<Int>, groupSize: Int): List<List<Int>>? {
        val length = messageList.size
        // 计算可以分多少份
        val num = (length + groupSize - 1) / groupSize
        val newList: MutableList<List<Int>> = ArrayList(num)
        for (i in 0 until num) {
            // 开始位置
            val fromIndex = i * groupSize
            // 结束位置
            val toIndex = if ((i + 1) * groupSize < length) (i + 1) * groupSize else length
            newList.add(messageList.subList(fromIndex, toIndex))
        }
        return newList
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityDrawWithRichtextBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

    private fun averageAssignFixLength(source: List<Int>?, splitItemNum: Int): List<Int> {
        val result = mutableListOf<Int>()

        if (source != null && source.run { isNotEmpty() } && splitItemNum > 0) {
            if (source.size <= splitItemNum) {
                // 源List元素数量小于等于目标分组数量
                result.addAll(source)
            } else {
                // 计算拆分后list数量
                val splitNum = if (source.size % splitItemNum == 0) source.size / splitItemNum else source.size / splitItemNum + 1

                var value: List<Int>? = null
                for (i in 0 until splitNum) {
                    if (i < splitNum - 1) {
                        value = source.subList(i * splitItemNum, (i + 1) * splitItemNum)
                    } else {
                        // 最后一组
                        value = source.subList(i * splitItemNum, source.size)
                    }

                    result.addAll(value)
                }
            }
        }

        return result
    }
}
