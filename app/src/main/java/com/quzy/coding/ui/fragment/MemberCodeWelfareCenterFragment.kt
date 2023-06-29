package com.quzy.coding.ui.fragment

import com.quzy.coding.R
import com.quzy.coding.base.BaseNavigationBarFragment
import com.quzy.coding.databinding.FragmentMemberCodeWelfareCenterBinding
import kotlinx.android.synthetic.main.fragment_member_code_welfare_center.welfare_center

/**
 * CreateDate:2023/6/16 18:57
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.fragment
 * @Description:
 */
class MemberCodeWelfareCenterFragment : BaseNavigationBarFragment() {

    val viewBinding by lazy { FragmentMemberCodeWelfareCenterBinding.bind(welfare_center) }
    override fun getContentResource(): Int {
        return R.layout.fragment_member_code_welfare_center
    }

}