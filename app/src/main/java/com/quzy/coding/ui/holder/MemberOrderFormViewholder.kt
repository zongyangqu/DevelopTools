package com.quzy.coding.ui.holder

import android.util.ArrayMap
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.coding.qzy.baselibrary.utils.DensityUtil
import com.coding.qzy.baselibrary.widget.BadgeView
import com.quzy.coding.R
import com.quzy.coding.base.BaseApplication
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.MemberOrderItemModel
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import kotlinx.android.synthetic.main.view_content_pager.view.*

/**
 * CreateDate:2021/10/22 17:30
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberOrderFormViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mIsLogin: Boolean = false
    private var mNeedDeliverBadge: BadgeView? = null
    private var mNeedPickBadge: BadgeView? = null
    private var mNeedCommentBadge: BadgeView? = null
    private var mRefundBadge: BadgeView? = null
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null

    companion object {
        private val PULL_DISTANCE = 36f
        private val MAX_COUNT = 99

        // 普通会员是0，超级会员是1
        private val MEMBER_TYPE_NORMAL = 0
        private val MEMBER_TYPE_SUPER = 1

        // 有礼品卡1，无礼品卡0
        private val MEMBER_GIFT_CARD = 1
        private val MEMBER_GIFT_NO_CARD = 0

        // 个人中心广告类型
        private val BANNER_TYPE_MEMBER_CENTER = 1
    }

    private var animation: Animation? = null

    init {
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }

    fun bindData(
            info: AssetInfo?,
            fragment: RecyclerViewWaterfallComplexKotActivity?
    ) {
        this.fragment = fragment
        animation = AnimationUtils.loadAnimation(itemView.context, R.anim.member_orderlist)
        if (info != null) {
            info.apply {
                setLogin(true)
                if (info.rightsList != null) {
                    for (index in info.rightsList.indices) {
                        if (index > 2) break
                        val bean = info.rightsList[index]
                        bean.isVip = true
                        info.orderListRep.add(bean)
                    }
                }
                setOrderList(svipGrayUser, orderListRep)
                setNeedPayCount(info.toPay) // 待支付
                setNeedReceiveCount(info.toReceive) // 待收货
                setNeedCommentCount(info.toComment) // 待评价
                setInRefundCount(info.afterSaleProcessingQuantity) // 退款售后
            }
        } else {
            cleanTextContent()
        }

        // 点击事件
        itemView.apply {
            ll_to_deliver.setOnClickListener {
                if (fragment != null) {
                    //mLoginStatus?.action({ goToDeliverOrderList() }, fragment)
                    goToDeliverOrderList()
                }
            }
            ll_to_pick.setOnClickListener {
                if (fragment != null) {
                    goToPickOrderList()
                    //mLoginStatus?.action({ goToPickOrderList() }, fragment)
                }
            }
            ll_to_comment.setOnClickListener {
                if (fragment != null) {
                    goToCommentOrderList()
                    //mLoginStatus?.action({ goToCommentOrderList() }, fragment)
                }
            }
            ll_refund.setOnClickListener {
                if (fragment != null) {
                    goRefundOrderList()
                    //mLoginStatus?.action({ goRefundOrderList() }, fragment)
                }
            }
            ll_order_all.setOnClickListener {
                if (fragment != null) {
                    goOrderList()
                    //mLoginStatus?.action({ goOrderList() }, fragment)
                }
            }
        }
    }

    fun goOrderList(): Boolean {
        return true
    }

    fun goRefundOrderList(): Boolean {
        return true
    }

    fun goToCommentOrderList(): Boolean {
        return true
    }

    fun goToPickOrderList(): Boolean {
        return true
    }

    fun goToDeliverOrderList(): Boolean {
        return true
    }

    fun setInRefundCount(count: Int) {
        itemView.apply {
            if (mRefundBadge == null) {
                mRefundBadge = BadgeView(BaseApplication.appContext, ic_refund)
                initRefundBadge(mRefundBadge)
            }
            mRefundBadge?.hide()

            if (count > 0) {
                if (count > MAX_COUNT) {
                    mRefundBadge?.text = MAX_COUNT.toString()
                } else {
                    mRefundBadge?.text = count.toString()
                }
                mRefundBadge?.show()
            }
        }
    }

    private fun cleanTextContent() {

        setLogin(false)
        setOrderList(false, null)
        setNeedPayCount(0)
        setNeedReceiveCount(0)
        setNeedCommentCount(0)
        setInRefundCount(0)
    }

    fun setNeedCommentCount(count: Int) {
        itemView.apply {
            if (mNeedCommentBadge == null) {
                mNeedCommentBadge = BadgeView(BaseApplication.appContext, ic_to_comment)
                initRefundBadge(mNeedCommentBadge)
            }
            mNeedCommentBadge?.hide()

            if (count > 0) {
                if (count > MAX_COUNT) {
                    mNeedCommentBadge?.text = MAX_COUNT.toString()
                } else {
                    mNeedCommentBadge?.text = count.toString()
                }
                mNeedCommentBadge?.show()
            }
        }
    }

    fun setNeedReceiveCount(count: Int) {
        itemView.apply {
            if (mNeedPickBadge == null) {
                mNeedPickBadge = BadgeView(BaseApplication.appContext, ic_to_pick)
                initRefundBadge(mNeedPickBadge)
            }
            mNeedPickBadge?.hide()
            if (count > 0) {
                if (count > MAX_COUNT) {
                    mNeedPickBadge?.text = MAX_COUNT.toString()
                } else {
                    mNeedPickBadge?.text = count.toString()
                }
                mNeedPickBadge?.show()
            }
        }
    }

    fun setNeedPayCount(count: Int) {
        itemView.apply {
            if (mNeedDeliverBadge == null) {
                mNeedDeliverBadge = BadgeView(BaseApplication.appContext, ic_to_deliver)
                initRefundBadge(mNeedDeliverBadge)
            }
            mNeedDeliverBadge?.hide()
            if (count > 0) {
                if (count > MAX_COUNT) {
                    mNeedDeliverBadge?.text = MAX_COUNT.toString()
                } else {
                    mNeedDeliverBadge?.text = count.toString()
                }
                mNeedDeliverBadge?.show()
            }
        }
    }

    private fun initRefundBadge(badgeView: BadgeView?) {
        badgeView?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
        badgeView?.setTextColor(
                ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                )
        )
        badgeView?.setBackgroundResource(R.drawable.bg_shape_member_orderlist_red)
        val params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        badgeView?.layoutParams = params
        badgeView?.gravity = Gravity.CENTER
        badgeView?.minWidth = DensityUtil.dip2px(BaseApplication.appContext, 17f)
    }

    fun setOrderList(svipGrayUser: Boolean, orderlist: ArrayList<MemberOrderItemModel>?) {
        itemView.apply {
            //  if (!isAtyAlive()) return
            if (orderlist != null) {
                if (orderlist.size == 0) {
                    showOrderList(false)
                    my_layout_pager.setPadding(0, 0, 0, 5)
                } else {
                    showOrderList(true)
                    my_layout_pager.visibility = View.VISIBLE
                    my_list_viewpager.startAnimation(this@MemberOrderFormViewholder.animation)
                    my_list_viewpager?.clearOnPageChangeListeners()
                    my_layout_pager.setPadding(0, 0, 0, 0)
                    if (orderlist.size == 1) my_layout_pager.setPadding(0, 0, 0, 18)
                }
            } else {
                showOrderList(false)
                my_layout_pager.setPadding(0, 0, 0, 5)
            }
        }
    }

    private fun showOrderList(show: Boolean) {
        itemView.apply {
            orderbanner_delive?.visibility = if(show)View.VISIBLE else View.GONE
            my_list_viewpager?.visibility = if(show)View.VISIBLE else View.GONE
            pager_tab_layout?.visibility = if(show)View.VISIBLE else View.GONE
        }
    }

    fun setLogin(login: Boolean) {
        itemView.apply {
            mIsLogin = login
            if (!login) {
                showOrderList(false)
                my_layout_pager.setPadding(0, 0, 0, 5) // 订单
            }
        }
    }
}
