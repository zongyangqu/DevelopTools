package com.quzy.coding.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.apkfuns.logutils.LogUtils
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.TopicBean
import com.quzy.coding.databinding.ActivityHorizontalVpRvBinding
import com.quzy.coding.ui.adapter.HomeTopicPagerAdapter
import com.quzy.coding.util.Constants

/**
 * CreateDate:2023/4/13 17:41
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class HorizontalViewPagerRvActivity : BaseActivity() {

    var viewBinding : ActivityHorizontalVpRvBinding?= null
    private val mTopicData: ArrayList<TopicBean> = ArrayList<TopicBean>()
    override fun onViewCreated() {
        initTopicData()
        initTypeViewPager(2, 4)
    }

    private fun initTopicData(){
        mTopicData.clear()
        mTopicData.add(TopicBean(R.mipmap.icon_home_99, "9块9包邮"))
        mTopicData.add(TopicBean(R.mipmap.icon_home_gaoyong, "高佣排名"))
        mTopicData.add(TopicBean(R.mipmap.icon_home_big_quan, "大额优惠券"))
        mTopicData.add(TopicBean(R.mipmap.icon_home_today_bao, "今日爆款"))
        mTopicData.add(TopicBean(R.mipmap.icon_home_low_price, "超低价"))
        mTopicData.add(TopicBean(R.mipmap.icon_home_today_jingdong, "京东"))
        mTopicData.add(TopicBean(R.mipmap.icon_home_pdd, "拼多多"))
        mTopicData.add(TopicBean(R.mipmap.icon_home_elm, "饿了么"))
//        mTopicData.add(TopicBean(R.mipmap.icon_home_hfcz, "话费充值"))
//        mTopicData.add(TopicBean(R.mipmap.icon_home_tmall_chaoshi, "天猫超市"))
//        mTopicData.add(TopicBean(R.mipmap.icon_home_sams_club, "山姆会员店"))
//        mTopicData.add(TopicBean(R.mipmap.icon_home_feizhu, "飞猪"))
//        mTopicData.add(TopicBean(R.mipmap.icon_home_oil, "优惠加油"))
//        mTopicData.add(TopicBean(R.mipmap.icon_home_kfc, "肯德基"))
//        mTopicData.add(TopicBean(R.mipmap.icon_home_meituan, "美团"))
    }


    private fun initTypeViewPager(rowNum: Int, columnNum: Int) {
        val topicViewPager = findViewById<ViewPager>(R.id.topicViewPager)
        //1.根据数据的多少来分页，每页的数据为rw
        val singlePageDatasNum = rowNum * columnNum //每个单页包含的数据量：2*4=8；
        var pageNum = mTopicData.size / singlePageDatasNum //算出有几页菜单：20%8 = 3;
        if (mTopicData?.size % singlePageDatasNum > 0) pageNum++ //如果取模大于0，就还要多一页出来，放剩下的不满项
        val mList = ArrayList<RecyclerView>()//java.util.ArrayList<RecyclerView>()
        for (i in 0 until pageNum) {
            val recyclerView = RecyclerView(applicationContext)
            val gridLayoutManager = GridLayoutManager(applicationContext, columnNum)
            recyclerView.layoutManager = gridLayoutManager
            val fromIndex = i * singlePageDatasNum
            var toIndex = (i + 1) * singlePageDatasNum
            if (toIndex > mTopicData.size) toIndex = mTopicData.size
            //a.截取每个页面包含数据
            val menuItems = ArrayList(mTopicData.subList(fromIndex, toIndex))
            //b.设置每个页面的适配器数据
            val menuAdapter = TopicAdapter(applicationContext, menuItems)
            //c.绑定适配器，并添加到list
            recyclerView.adapter = menuAdapter
            mList.add(recyclerView)
        }
        //2.ViewPager的适配器
        val menuViewPagerAdapter = HomeTopicPagerAdapter(mList)
        topicViewPager.adapter = menuViewPagerAdapter
        //3.动态设置ViewPager的高度，并加载所有页面
        val height: Int = 96f.dpOfInt//这里的80为MainMenuAdapter中布局文件高度
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            if (mTopicData.size <= columnNum) height else height * rowNum
        )
        topicViewPager.layoutParams = layoutParams
        topicViewPager.offscreenPageLimit = pageNum - 1
        //4.创建指示器
//        viewBinding?.indicator?.setWithViewPager(topicViewPager)
        viewBinding?.indicatorCircleLine?.setViewPager(topicViewPager)

        topicViewPager?.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                LogUtils.tag(Constants.LOG_TAG).d("onPageScrolled===========$position")
            }

            override fun onPageSelected(position: Int) {
                LogUtils.tag(Constants.LOG_TAG).d("onPageSelected===========$position")
                if(position == 1){
                    val height: Int = 46f.dpOfInt//这里的80为MainMenuAdapter中布局文件高度
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        if (mTopicData.size <= columnNum) height else height * rowNum
                    )
                    topicViewPager.layoutParams = layoutParams
                } else {
                    val height: Int = 96f.dpOfInt//这里的80为MainMenuAdapter中布局文件高度
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        if (mTopicData.size <= columnNum) height else height * rowNum
                    )
                    topicViewPager.layoutParams = layoutParams
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                LogUtils.tag(Constants.LOG_TAG).d("onPageScrollStateChanged===========$state")
            }

        })
    }

    override fun getLayoutId(): Int {
        return 0
    }

    override fun getLayoutView(): View?{
        viewBinding = ActivityHorizontalVpRvBinding.inflate(LayoutInflater.from(this))
        viewBinding?.let {
            return it.root
        }
        return null
    }
}