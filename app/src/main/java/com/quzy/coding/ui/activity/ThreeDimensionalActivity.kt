package com.quzy.coding.ui.activity

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.quzy.coding.Question
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.QuestionInfo
import com.quzy.coding.util.CardTransformer
import com.quzy.coding.util.JsonUtils
import com.quzy.coding.util.widget.NoSwipeViewPager
import java.util.*
import kotlin.collections.ArrayList

/**
 * CreateDate:2021/12/3 14:52
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description: 3D 卡片叠放
 */
class ThreeDimensionalActivity :BaseActivity() {

//    var mViewPager : ViewPager ?= null
    var mViewPager : NoSwipeViewPager ?= null
    var mHistoryList: List<QuestionInfo> ?= null
    var mViewPagerAdapter: ViewPagerAdapter? = null


    override fun onViewCreated() {
        mViewPager = findViewById(R.id.viewpager)
        var question : Question = JsonUtils.analysisQuestionJsonFile(activity, "question")
        mHistoryList = question.history_list
        setAdapter()

    }


    private fun setAdapter() {
        Collections.reverse(mHistoryList)
        mViewPager?.setCanSwipe(false)
        mViewPagerAdapter = mHistoryList?.let { ViewPagerAdapter(this, it) }
        mViewPager?.adapter = mViewPagerAdapter
        mViewPager?.setCurrentItem((mHistoryList?.size ?: 1) - 1, false)
        val mCardPageTransformer = CardTransformer()
        //设置limit
        mViewPager?.offscreenPageLimit = mHistoryList?.size?:1
        //设置transformer
        mViewPager?.setPageTransformer(true, mCardPageTransformer)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_layout_three_dimensional
    }

    override fun getLayoutView(): View? {
        return null
    }


    class ViewPagerAdapter(private val mContext: Context, private var list: List<QuestionInfo>) : PagerAdapter() {
        //获去当前VIew的方法
        var primaryItem: View? = null
            private set
        var contentTv: TextView? = null
        var TipContentTv: TextView? = null
        var first_btn_right_icon: ImageView? = null
        var second_btn_right_icon: ImageView? = null
        var tip_layout: LinearLayout? = null
        var optionTv1: TextView? = null
        var optionTv2: TextView? = null
        var img: ImageView? = null
        var item_data_layout: RelativeLayout? = null
        var tip_button: Button? = null
        var check_more_layout: RelativeLayout? = null

        override fun setPrimaryItem(container: ViewGroup, position: Int, any: Any) {
            primaryItem = any as View?
        }


        override fun getCount(): Int {
            return list.size
        }

        fun setList(list: List<QuestionInfo>) {
            this.list = list
            notifyDataSetChanged()
        }

        fun getItem(position: Int): QuestionInfo {
            return list[position]
        }

        override fun isViewFromObject(view: View, any: Any): Boolean {
            return view === any
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            Log.d("zongyang-p:",position.toString())
            val view: View = LayoutInflater.from(container.context).inflate(R.layout.test_item_layout, container, false)
            if(position != 4){
                view.background.alpha = 50
            }
            initView(view)
            bindData(list[position])
            container.addView(view)
            return view
        }

        private fun saveQuestionInfo(option: Int) {
            optionTv1!!.isSelected = true
            optionTv2!!.isSelected = false
            optionTv1!!.isEnabled = false
            optionTv2!!.isEnabled = false
        }

        private fun initView(view: View) {
            contentTv = view.findViewById<View>(R.id.card_text) as TextView
            TipContentTv = view.findViewById<View>(R.id.tip_text) as TextView
            optionTv1 = view.findViewById<View>(R.id.first_btn) as TextView
            optionTv2 = view.findViewById<View>(R.id.second_btn) as TextView
            tip_layout = view.findViewById<View>(R.id.tip_layout) as LinearLayout
            img = view.findViewById<View>(R.id.img) as ImageView

            item_data_layout = view.findViewById<View>(R.id.item_data_layout) as RelativeLayout
            first_btn_right_icon = view.findViewById<View>(R.id.first_btn_right_icon) as ImageView
            second_btn_right_icon = view.findViewById<View>(R.id.second_btn_right_icon) as ImageView
            optionTv1!!.setOnClickListener {
                saveQuestionInfo(1)
                //mEmoticonRainView.start(getBitmaps())
            }
            optionTv2?.setOnClickListener { saveQuestionInfo(2) }
            tip_button?.setOnClickListener { }
        }

        fun bindData(itemData: QuestionInfo?) {
            try {
                if (itemData == null) {
                    return
                }
                contentTv?.text = itemData.title
                val type = itemData.card_type
                var answer = 0
                var user_option = 0
                if (!TextUtils.isEmpty(itemData.answer)) {
                    answer = Integer.valueOf(itemData.answer)
                }
                if (!TextUtils.isEmpty(itemData.option)) {
                    user_option = Integer.valueOf(itemData.option)
                }
                optionTv1?.text = itemData.options[0]
                optionTv2?.text = itemData.options[1]
                img?.let { Glide.with(BaseApplication.getContext()).load(itemData.img_url).into(it) }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            container.removeView(any as View?)
        }

    }
}