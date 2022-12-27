package com.quzy.coding.ui.activity

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.Fruit
import com.quzy.coding.bean.MyData
import com.quzy.coding.databinding.ActivityRecyclerviewBinding
import com.quzy.coding.ui.adapter.MoreItemKotlinAdapter
import com.quzy.coding.ui.adapter.RecycVerticalAdapter
import com.quzy.coding.util.UiUtil
import java.util.*
import kotlin.collections.ArrayList


/**
 * CreateDate:2021/8/20 16:58
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.activity
 * @Description:
 */
class MoreItemRecyclerViewKotActivity : BaseActivity(){

    lateinit var adapter : MoreItemKotlinAdapter
    var activityRecyclerviewBinding:ActivityRecyclerviewBinding? = null
    private var allInfo:MutableList<Any>?= mutableListOf()

    var textList:MutableList<String>?= mutableListOf()

    fun textViewFormat1(){
        activityRecyclerviewBinding?.textview1?.text= "哈哈这是来哈"
        activityRecyclerviewBinding?.textview2?.text= "| 1哈哈这试 | 2哈哈这来"
        activityRecyclerviewBinding?.textview2?.measure(0,0)
        activityRecyclerviewBinding?.textview1?.measure(0,0)
        val textview2Width = activityRecyclerviewBinding?.textview2?.measuredWidth ?:0
        val textview1Width = activityRecyclerviewBinding?.textview1?.measuredWidth ?:0
        if ((textview2Width + textview1Width) > 280f.dpOfInt) {
            activityRecyclerviewBinding?.textview2?.text= "| 1哈哈这试 | 2哈哈这来"
        } else {
            activityRecyclerviewBinding?.textview2?.text= " | 1哈哈这试 | 2哈哈这来"
        }
    }

    /**
     * 推荐文案
     */
    fun getRecommendTitle(titles :List<String>?):SpannableStringBuilder?{

        var titleBd = SpannableStringBuilder()
        titles?.forEachIndexed { index, s ->
            if (index == 0) {
                titleBd.append(s)
            }  else {
                titleBd.append(" | ")
                Log.d("zongyang","titleBd.length---->"+titleBd.length)
                titleBd.setSpan(
                    AbsoluteSizeSpan(UiUtil.sp2px(this, 10f)), titleBd.length -2,
                    titleBd.length -1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
                titleBd.append(s)
            }
        }


//
//        var contentStr = titleBd.toString()
//        val spannableString = SpannableStringBuilder()
//        spannableString.append(contentStr)
//        spannableString.setSpan(
//            AbsoluteSizeSpan(UiUtil.sp2px(this, 16f)), 0,
//            contentStr?.length
//                ?: 0,
//            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
//        )
//        spannableString.setSpan(
//            ForegroundColorSpan(ContextCompat.getColor(context, R.color.themeColor)),
//            6,
//            6 + (Collections.min(newPersonProducts)?.price?.price?.length ?: 0),
//            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
//        )
//
//        viewBinding?.tvCustomerProductTitle?.text = spannableString



        return titleBd
    }

    override fun onViewCreated() {
        textList?.add("多汁")
        textList?.add("非常新鲜")
//        textList?.add("吃完还买")

        activityRecyclerviewBinding?.textview?.text = getRecommendTitle(textList)
        textViewFormat1()
        var id = resources.getResourceEntryName(activityRecyclerviewBinding!!.textview?.id)
        Log.d("zongyang", "id============$id")
        supportActionBar?.title = "RecyclerView多条目"
        var manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        activityRecyclerviewBinding?.recyclerView?.layoutManager = manager
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        activityRecyclerviewBinding?.recyclerView?.addItemDecoration(dividerItemDecoration)
        initData()
        adapter = MoreItemKotlinAdapter(this, allInfo!!)
        activityRecyclerviewBinding?.recyclerView?.adapter = adapter

        adapter!!.setOnItemClickListener(object :MoreItemKotlinAdapter.OnItemClickListener{

            override fun onItemClick(view: View, pos: Int) {
                val bundle= Bundle()
                when(allInfo!![pos]){
                    is MyData.MasterBean->{
                        val list=allInfo as MutableList<MyData.MasterBean>
                        bundle.putInt("id",list!![pos].id)
                        Toast.makeText(this@MoreItemRecyclerViewKotActivity,"师傅"+list!![pos].id,Toast.LENGTH_SHORT).show()
                    }
                    is MyData.MeasureBean->{
                        val list=allInfo as MutableList<MyData.MeasureBean>
                        Toast.makeText(this@MoreItemRecyclerViewKotActivity,"量房"+list!![pos].id,Toast.LENGTH_SHORT).show()
                    }
                    is MyData.BudgetBean->{
                        val list=allInfo as MutableList<MyData.BudgetBean>
                        Toast.makeText(this@MoreItemRecyclerViewKotActivity,"预算"+list!![pos].id,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        var data :MutableList<String> = ArrayList<String>()
        data.add("111")
        data.add("222")
        data.add("333")
        data.add("444")
        data.add("555")
        data.add("666")

        var dataUsed :MutableList<String> = ArrayList<String>()
        var dataSwitch :MutableList<String> = ArrayList<String>()
        data.mapIndexed { index, s ->
            if (index < 3){
                dataUsed.add(s)
            } else {
                dataSwitch.add(s)
            }
        }

        dataUsed.forEachIndexed { index, s ->
            Log.d("zongyang","dataUsed---->$s")
        }

        dataSwitch.forEachIndexed { index, s ->
            Log.d("zongyang","dataSwitch---->$s")
        }

        data?.subList(3,data.size).forEachIndexed { index, s ->
            Log.d("zongyang","data---->$s")
        }


    }

    override fun getLayoutView(): View? {
        activityRecyclerviewBinding = ActivityRecyclerviewBinding.inflate(LayoutInflater.from(this))
        activityRecyclerviewBinding?.root?.let {
            return it.rootView
        }
        return null
    }

    override fun getLayoutId(): Int {
        return 0;
    }

    fun initData(){
        val budget= MyData.BudgetBean()
        budget.title="新天地"
        budget.id=5
        budget.date="2018.12.15"
        val master=MyData.MasterBean()
        master.id=6
        master.date="2018.12.17"
        master.dictName="木工"
        master.isVip="否"
        master.telphone="13022578945"
        val master2=MyData.MasterBean()
        master2.id=2
        master2.date="2018.12.17"
        master2.dictName="水电工"
        master2.isVip="是"
        master2.telphone="13022578945"


        val master3=MyData.MasterBean()
        master.id=6
        master.date="2018.12.17"
        master.dictName="木工1"
        master.isVip="否"
        master.telphone="13022578945"
        val master4=MyData.MasterBean()
        master2.id=2
        master2.date="2018.12.17"
        master2.dictName="水电工1"
        master2.isVip="是"
        master2.telphone="13022578945"

        val measure=MyData.MeasureBean()
        measure.date="2018.11.25"
        measure.havePay="否"
        measure.id=14
        measure.payNum="12.0"
        measure.title="融圣国际"


        val master5=MyData.MasterBean()
        master.id=6
        master.date="2018.12.17"
        master.dictName="木工2"
        master.isVip="否"
        master.telphone="13022578945"
        val master6=MyData.MasterBean()
        master2.id=2
        master2.date="2018.12.17"
        master2.dictName="水电工2"
        master2.isVip="是"
        master2.telphone="13022578945"

        val budget2= MyData.BudgetBean()
        budget.title="新天地2"
        budget.id=6
        budget.date="2018.12.15"
        val budget3= MyData.BudgetBean()
        budget.title="新天地3"
        budget.id=7
        budget.date="2018.12.15"
        val budget4= MyData.BudgetBean()
        budget.title="新天地4"
        budget.id=8
        budget.date="2018.12.15"
        val budget5= MyData.BudgetBean()
        budget.title="新天地5"
        budget.id=9
        budget.date="2018.12.15"
        val budget6= MyData.BudgetBean()
        budget.title="新天地3"
        budget.id=7
        budget.date="2018.12.15"
        val budget7= MyData.BudgetBean()
        budget.title="新天地4"
        budget.id=8
        budget.date="2018.12.15"
        val budget8= MyData.BudgetBean()
        budget.title="新天地5"
        budget.id=9
        budget.date="2018.12.15"
        val budget9= MyData.BudgetBean()
        budget.title="新天地4"
        budget.id=8
        budget.date="2018.12.15"
        val budget10= MyData.BudgetBean()
        budget.title="新天地5"
        budget.id=9
        budget.date="2018.12.15"



        allInfo?.add(budget)
        allInfo?.add(master)
        allInfo?.add(master2)
        allInfo?.add(measure)
        allInfo?.add(master3)
        allInfo?.add(master4)
        allInfo?.add(master5)
        allInfo?.add(master6)
        allInfo?.add(budget2)
        allInfo?.add(budget3)
        allInfo?.add(budget4)
        allInfo?.add(budget5)
        allInfo?.add(budget6)
        allInfo?.add(budget7)
        allInfo?.add(budget8)
        allInfo?.add(budget9)
        allInfo?.add(budget10)
    }

}