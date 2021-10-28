package com.quzy.coding.ui.holder

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.quzy.coding.databinding.ViewCustomerPropertiesPagerBinding
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity

/**
 * CreateDate:2021/10/22 17:15
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberDefaultCustomerPropertiesViewholder(mLifecycleOwner: LifecycleOwner?, itemView: View): RecyclerView.ViewHolder(itemView) {

    var fragment: RecyclerViewWaterfallComplexKotActivity? = null

    private val balanceDefaultAction = "myyh://yhlife.com/show/native?name=topup&needlogin=1"
    private val markDefaultAction = "myyh://yhlife.com/show/web?url=https%3A%2F%2Fm.yonghuivip.com%2Ft1%2Fyh-m-site%2Fyh-e-card%2Findex.html%23%2F&needlogin=1"
    private val packageDefaultAction = "myyh://yhlife.com/show/native?name=coupon&needlogin=1"
    private val yhcardDefaultAction = "myyh://yhlife.com/show/web?url=https%3A%2F%2Fm.yonghuivip.com%2Ft1%2Fyh-m-site%2Fyh-point-exchange%2Findex.html%3Fneedlocation%3D1%26canShare%3D0&needlogin=1"

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }

    val viewBinding by lazy {
        ViewCustomerPropertiesPagerBinding.bind(itemView)
    }

    fun bindData(fragment: RecyclerViewWaterfallComplexKotActivity?) {
        this.fragment = fragment

        viewBinding.tvMemberBalanceDefault.setOnClickListener {
            toDefaultPage(balanceDefaultAction)
        }

        viewBinding.tvMemberMarkDefault.setOnClickListener{
            toDefaultPage(markDefaultAction)
        }

        viewBinding.tvMemberPackageDefault.setOnClickListener{
            toDefaultPage(packageDefaultAction)
        }

        viewBinding.tvMemberYhcardDefault.setOnClickListener{
            toDefaultPage(yhcardDefaultAction)
        }
    }

    private fun toDefaultPage(pageUrl: String?) {
        fragment?.let {
//            Navigation.startSchema(it.context, pageUrl)
        }
    }

}