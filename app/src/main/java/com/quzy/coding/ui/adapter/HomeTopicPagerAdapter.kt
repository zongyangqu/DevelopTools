package com.quzy.coding.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter

/**
 * CreateDate:2023/4/13 19:12
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.adapter
 * @Description:
 */
class HomeTopicPagerAdapter(mList: ArrayList<RecyclerView>) :
    PagerAdapter() {
    private val mList: ArrayList<RecyclerView>
    override fun getCount(): Int {
        return if (mList.isEmpty()) 0 else mList.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
        container.removeView(mList[position])
    }


//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any?) {
//        container.removeView(mList[position])
//    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mList[position])
        return mList[position]
    }

    init {
        this.mList = mList
    }
}
