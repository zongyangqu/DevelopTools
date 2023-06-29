package com.quzy.coding.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import com.quzy.coding.R
import com.quzy.coding.base.BaseNavigationBarFragment
import com.quzy.coding.databinding.FragmentMemberCodeStoreCenterBinding
import com.quzy.coding.ui.activity.MemberCodeActivity
import com.quzy.coding.ui.adapter.MemberCodeMainAdapter
import com.quzy.coding.ui.util.MemberCodeLayoutManager
import com.quzy.coding.ui.widget.MemberCodeListItemDecoration
import com.quzy.coding.util.JsonUtils
import com.quzy.coding.util.extend.show

/**
 * CreateDate:2023/6/16 18:54
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.fragment
 * @Description:
 */
class MemberCodeStoreCenterFragment : BaseNavigationBarFragment() {

    val viewBinding: FragmentMemberCodeStoreCenterBinding by lazy { FragmentMemberCodeStoreCenterBinding.bind(contentView) }
    val marketingListAdapter by lazy { MemberCodeMainAdapter(activity, this, this) }
    val marketingLayoutManager by lazy { MemberCodeLayoutManager(activity, marketingListAdapter) }
    override fun getContentResource(): Int {
        return R.layout.fragment_member_code_store_center
    }

    override fun onFinishCreateView() {
        super.onFinishCreateView()
        initView()
        setData()
    }

    private fun initView(){
        viewBinding.memberCodeMarketingRecycleView.layoutManager = marketingLayoutManager
        viewBinding.memberCodeMarketingRecycleView.addItemDecoration(MemberCodeListItemDecoration())
        viewBinding.memberCodeMarketingRecycleView.adapter = marketingListAdapter
    }

    private fun setData(){
        var quickJumpTypeBean = JsonUtils.analysisPayQuickJsonFile(activity, "quicktype")
        val payTypeFirst = quickJumpTypeBean.quickJumpTypeList[0]
        viewBinding.payTypeFirst.show()
        viewBinding.payTypeFirst.bindData(payTypeFirst, true)

        val payTypeSecond = quickJumpTypeBean.quickJumpTypeList[1]
        viewBinding.payTypeSecond.show()
        viewBinding.payTypeSecond.bindData(payTypeSecond, true)

        val payTypeThird = quickJumpTypeBean.quickJumpTypeList[2]
        viewBinding.payTypeThird.show()
        viewBinding.payTypeThird.bindData(payTypeThird, true)
    }



}