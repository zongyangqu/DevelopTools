package com.quzy.coding.ui.activity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityViewpagerIndicatorBinding
import java.util.*

/**
 * CreateDate:2023/4/14 14:23
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class ViewPagerIndicatorActivity  : BaseActivity() {
    var viewBinding : ActivityViewpagerIndicatorBinding?= null
    override fun onViewCreated() {

        viewBinding?.vpFirst?.adapter = CustomViewPagerAdapter()
        viewBinding?.vpiFirst?.setupWithViewPager(viewBinding?.vpFirst)

        viewBinding?.vpSecond?.adapter = CustomViewPagerAdapter()
        viewBinding?.vpiSecond?.setupWithViewPager(viewBinding?.vpSecond)

        viewBinding?.vpSecond2?.adapter = CustomViewPagerAdapter()
        viewBinding?.vpiSecond2?.setupWithViewPager(viewBinding?.vpSecond2)

        viewBinding?.vpThird?.adapter = CustomViewPagerAdapter()
        viewBinding?.vpiThird?.setupWithViewPager(viewBinding?.vpThird)

        viewBinding?.vpFourth?.adapter = CustomViewPagerAdapter()
        viewBinding?.vpiFourth?.setupWithViewPager(viewBinding?.vpFourth)

        viewBinding?.vpFifth?.adapter = CustomViewPagerAdapter()
        viewBinding?.vpiFifth?.setupWithViewPager(viewBinding?.vpFifth)

        viewBinding?.btnChangeAdapter?.setOnClickListener {
            viewBinding?.vpFirst?.adapter = CustomViewPagerAdapter()
//            (vp_first.adapter as CustomViewPagerAdapter).apply {
//                itemCount = 10
//                notifyDataSetChanged()
//            }
            viewBinding?.vpSecond?.adapter = CustomViewPagerAdapter()
//            (vp_second.adapter as CustomViewPagerAdapter).apply {
//                itemCount = 10
//                notifyDataSetChanged()
//            }
            viewBinding?.vpSecond2?.adapter = CustomViewPagerAdapter()
//            (vp_second2.adapter as CustomViewPagerAdapter).apply {
//                itemCount = 10
//                notifyDataSetChanged()
//            }
            viewBinding?.vpThird?.adapter = CustomViewPagerAdapter()
//            (vp_third.adapter as CustomViewPagerAdapter).apply {
//                itemCount = 10
//                notifyDataSetChanged()
//            }
            viewBinding?.vpFourth?.adapter = CustomViewPagerAdapter()
//            (vp_fourth.adapter as CustomViewPagerAdapter).apply {
//                itemCount = 10
//                notifyDataSetChanged()
//            }
            viewBinding?.vpFifth?.adapter = CustomViewPagerAdapter()
//            (vp_fifth.adapter as CustomViewPagerAdapter).apply {
//                itemCount = 10
//                notifyDataSetChanged()
//            }
        }
    }

    class CustomViewPagerAdapter : PagerAdapter() {

        var itemCount = 3

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun getCount(): Int = itemCount

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = View(container.context)
            view.setBackgroundColor(Color.rgb(Random().nextInt(255), Random().nextInt(255), Random().nextInt(255)))
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View?{
        viewBinding = ActivityViewpagerIndicatorBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }
}