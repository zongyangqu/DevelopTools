package com.quzy.coding.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.quzy.coding.R
import com.quzy.coding.base.BaseActivity
import com.quzy.coding.bean.Fruit
import com.quzy.coding.bean.MyData
import com.quzy.coding.databinding.ActivityRecyclerviewBinding
import com.quzy.coding.ui.adapter.MoreItemKotlinAdapter
import com.quzy.coding.ui.adapter.RecycVerticalAdapter


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

    override fun onViewCreated() {
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

        val measure=MyData.MeasureBean()
        measure.date="2018.11.25"
        measure.havePay="否"
        measure.id=14
        measure.payNum="12.0"
        measure.title="融圣国际"
        val measure2=MyData.MeasureBean()
        measure2.date="2018.11.25"
        measure2.havePay="是"
        measure2.id=14
        measure2.payNum="11.0"
        measure2.title="北大青鸟"



        allInfo?.add(budget)
        allInfo?.add(master)
        allInfo?.add(master2)
        allInfo?.add(measure)
        allInfo?.add(measure2)
    }

}