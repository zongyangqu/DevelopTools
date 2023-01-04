package com.quzy.coding.ui.widget

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.qzy.baselibrary.utils.extend.dp
import com.coding.qzy.baselibrary.utils.extend.dpOfInt
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.base.BaseDialogFragment
import com.quzy.coding.bean.CouponNewCustomerResultBean
import com.quzy.coding.ui.adapter.NewCustomerCouponUnitaryListAdapter
import com.quzy.coding.util.*
import com.quzy.coding.util.extend.click
import com.quzy.coding.util.extend.gone
import com.quzy.coding.util.extend.show
import com.quzy.coding.util.extend.singleClick
import kotlinx.android.synthetic.main.dialog_new_customer_unitary_coupon.view.*

/**
 * CreateDate:2022/7/1 9:52
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.widget
 * @Description: 全局弹窗
 */
class CouponNewCustomerUnitaryDialog : BaseDialogFragment() {
    var mNewCustomerCouponBean: CouponNewCustomerResultBean? = null
    private lateinit var mView: View
    override fun getDialogResourceId(): Int {
        return R.layout.dialog_new_customer_unitary_coupon
    }

    override fun initView(view: View) {
        mView = view
        mView.package_unclosed?.singleClick {
            it.gone()
            mView?.new_customer_coupon_dialog_container?.show()
        }
        mView.itemView.click {
            dismiss()
        }
        context?.let { mContext ->
            mView.new_customer_coupon_dialog_get_layout.background = DrawableUtils.createCornerTopBottomDrawable(
                20f.dp, 20f.dp, 20f.dp, 20f.dp,
                intArrayOf(
                    ContextCompat.getColor(mContext, R.color.color_FFFCF6), ContextCompat.getColor(mContext, R.color.color_FFDA8E)
                )
            )

            mView?.backgroup?.background = DrawableUtils.createCornerTopBottomDrawable(
                9f.dp, 9f.dp, 9f.dp, 9f.dp,
                intArrayOf(
                    ContextCompat.getColor(mContext, R.color.color_FFFCF6), ContextCompat.getColor(mContext, R.color.color_FFDA8E)
                )
            )
        }
        mView.new_customer_coupon_dialog_get_layout.click {
            //jumpToMethod()
        }
        mView.backgroup.click {
            //jumpToMethod()
        }
        showCouponList()
    }

    private fun showCouponList() {

        val couponCount = mNewCustomerCouponBean?.coupons?.size ?: 0
        val layoutParams = mView?.new_customer_coupon_dialog_container.layoutParams
        val countProduct = mNewCustomerCouponBean?.newPersonSkus?.size ?: 0

        val layoutPar = mView?.new_customer_coupon_dialog_success?.layoutParams
        if (layoutPar is RelativeLayout.LayoutParams) {
            if (countProduct == 0 && countProduct < 3) {
                layoutPar.topMargin = 50f.dpOfInt
            } else {
                layoutPar.topMargin = 10f.dpOfInt
            }
        }
        if (couponCount > 0) {
            mView?.new_customer_coupon_dialog_container?.layoutParams = layoutParams
            mView?.new_customer_coupon_dialog_rv.layoutManager = LinearLayoutManager(
                BaseApplication.getContext()
            )
            val mAdapter = NewCustomerCouponUnitaryListAdapter(context, mNewCustomerCouponBean?.coupons, mNewCustomerCouponBean?.newPersonSkus)
            mView?.new_customer_coupon_dialog_rv.adapter = mAdapter
        }
        mView?.new_customer_coupon_dialog_success.text = mNewCustomerCouponBean?.title
        mView?.new_customer_coupon_dialog_get.text = mNewCustomerCouponBean?.buttonCopyWriting
        mView?.new_customer_coupon_dialog_amount.text = String.format(context?.getString(R.string.home_new_customer_coupon_dialog_amount) ?: "", centToYuanDeleteZeroString(mNewCustomerCouponBean?.totalamount ?: 0))
        mView?.new_customer_coupon_dialog_title.text = mNewCustomerCouponBean?.subTitle
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.decorView?.setPadding(0, 0, 0, 0)
        dialog?.window?.setGravity(Gravity.CENTER)
        val lp = dialog?.window?.attributes
        lp?.width = WindowManager.LayoutParams.MATCH_PARENT
        lp?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = lp
        dialog?.setCanceledOnTouchOutside(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun setData(newCustomerResultBean: CouponNewCustomerResultBean?) {
        this.mNewCustomerCouponBean = newCustomerResultBean
    }

    override fun onDismiss(dialog: DialogInterface) {
        //postEvent(RefreshHomeEvent())
        super.onDismiss(dialog)
    }

    fun centToYuanDeleteZeroString(cents: Int): String? {
        var result = (cents / 100.0).toString()
        if (result.indexOf(".") > 0) {
            result = result.replace("0+?$".toRegex(), "") //去掉后面无用的零
            result = result.replace("[.]$".toRegex(), "") //如小数点后面全是零则去掉小数点
        }
        return result
    }
}