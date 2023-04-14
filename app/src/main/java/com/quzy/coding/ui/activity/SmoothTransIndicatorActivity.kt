package com.quzy.coding.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.coding.qzy.baselibrary.widget.smoothtransindicator.SmoothTransIndicator
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.databinding.ActivityTransIndicatorBinding
import com.quzy.coding.ui.fragment.ViewPagerFragment


/**
 * CreateDate:2023/4/14 16:49
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: Viewpager指示器，此消彼长的动效，及切换时两种颜色的顺滑过渡
 */
class SmoothTransIndicatorActivity : BaseActivity() {
    var viewBinding : ActivityTransIndicatorBinding?= null
    private var mViewpager: ViewPager? = null
    private val adapter: ViewPagerAdapter? = null
    private var mIndicatorCircleLine: SmoothTransIndicator? = null
    override fun onViewCreated() {
        mViewpager = findViewById(R.id.viewpager) as ViewPager
        val strings = java.util.ArrayList<String>()
        strings.add("blue")
        strings.add("red")
        strings.add("yellow")
        val fragments = java.util.ArrayList<Fragment>()
        for (i in strings.indices) {
            val fragment: Fragment = ViewPagerFragment()
            val bundle = Bundle()
            bundle.putString("key", strings[i])
            fragment.arguments = bundle
            fragments.add(fragment)
        }

        val adapter: ViewPagerAdapter =
            ViewPagerAdapter(
                supportFragmentManager, fragments
            )
        mViewpager?.setAdapter(adapter)

        mIndicatorCircleLine = findViewById(R.id.indicator_circle_line) as SmoothTransIndicator

        mIndicatorCircleLine?.setViewPager(mViewpager)
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View?{
        viewBinding = ActivityTransIndicatorBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }

    internal class ViewPagerAdapter(
        supportFragmentManager: FragmentManager,
        fragments: ArrayList<Fragment>
    ) :
        FragmentPagerAdapter(supportFragmentManager) {
        private val mSupportFragmentManager: FragmentManager
        private val mFragments: ArrayList<Fragment>
        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        init {
            mSupportFragmentManager = supportFragmentManager
            mFragments = fragments
        }
    }
}