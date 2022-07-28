package com.quzy.coding.ui.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View? {
        viewBinding = ActivityDrawWithRichtextBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }
}
