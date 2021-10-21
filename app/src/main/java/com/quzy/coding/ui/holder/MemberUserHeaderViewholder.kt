package com.quzy.coding.ui.holder

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ImageSpan
import android.util.ArrayMap
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.quzy.coding.R
import com.quzy.coding.bean.AssetInfo
import com.quzy.coding.bean.VipHintBean
import com.quzy.coding.ui.activity.RecyclerViewWaterfallComplexKotActivity
import com.quzy.coding.util.CenterImageSpan
import com.quzy.coding.util.widget.CouponShakeAnimation
import kotlinx.android.synthetic.main.view_membercenter_head.view.*
import java.util.ArrayList

/**
 * CreateDate:2021/10/20 11:36
 * @author: zongyang qu
 * @Package： com.quzy.coding.ui.holder
 * @Description:
 */
class MemberUserHeaderViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        private val PULL_DISTANCE = 36f
        private val MAX_COUNT = 99

        // 普通会员是0，超级会员是1
        private val MEMBER_TYPE_NORMAL = 0
        private val MEMBER_TYPE_SUPER = 1
        private val MEMBER_TYPE_EXCELLENCE = 1

        // 有礼品卡1，无礼品卡0
        private val MEMBER_GIFT_CARD = 1
        private val MEMBER_GIFT_NO_CARD = 0

        // 个人中心广告类型
        private val BANNER_TYPE_MEMBER_CENTER = 1
    }
    private var mIsLogin: Boolean = false
    private var mWxNickname: String? = ""
    val EXTRA_ROUTE = "route"
    private var couponShakeAnimation: CouponShakeAnimation? = null
    private var isShowCouponAnimation = true
    private var creditAction: String? = null
    private var isFirst = false
    var fragment: RecyclerViewWaterfallComplexKotActivity? = null
    init {
        //设置当前瀑布流 横向满屏幕显示
        (itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = true
    }

    fun bindData(info: AssetInfo?, fragment: RecyclerViewWaterfallComplexKotActivity?, first: Boolean) {
        this.fragment = fragment
        this.isFirst = first
        // 头部
        if (info != null) {
            info.apply {
                setLogin(true)
                if (!TextUtils.isEmpty(nickname)) {
                    setNickName(nickname)
                } else {
                    setNickName(if (TextUtils.isEmpty(mWxNickname)) "" else mWxNickname)
                    setMemberPhone(mobile)
                }
                setMemberLevelInfo(info)
                setSvipMsg(info.vipRightsList, info.gradeid)
            }
        } else {
            cleanTextContent()
        }
        // 点击事件
        itemView.apply {
            ll_member_header.setOnClickListener {
            }
            // 登录注册
            member_notlogin.setOnClickListener {
            }
            ic_avatar.setOnClickListener {
            }
            txt_phone.setOnClickListener {
            }
            tv_fillMemInfoTips.setOnClickListener {
            }
            ic_login_service.setOnClickListener {
            }
            ic_login_qrcode.setOnClickListener {
            }
            // 扫一扫
            ic_member_scan.setOnClickListener {
            }
            // 设置
            ic_setting.setOnClickListener {
            }
        }
    }

    private fun cleanTextContent() {
        itemView.apply {
            sivp_hint_layout.setBackgroundResource(R.mipmap.member_action_black)
            iv_bg_icon.setImageDrawable(resources.getDrawable(R.mipmap.member_action_black_icon))
            iv_bg.setBackgroundResource(R.mipmap.member_header_new_red)
            svip_hint_action.setBackgroundResource(R.drawable.member_sviphint_action_bg)
            svip_hint_subtitle.setTextColor(ContextCompat.getColor(context, R.color.white))
            svip_hint_action.setTextColor(
                    ContextCompat.getColor(context, R.color.svip_action_textcolor))
            svip_hint_title.setTextColor(ContextCompat.getColor(context, R.color.subGoldColor))
            ic_crown.visibility = View.GONE
        }
        setLogin(false)
        setMemberLevel("")
    }

    fun setSvipMsg(vipHintBeans: ArrayList<VipHintBean>?, gradeid: Int) {
        itemView.apply {
            val memberMsgParams = member_msg_rootlayout.layoutParams
            if (vipHintBeans?.size ?: 0 > 0) {
                member_msg_rootlayout.setPadding(0, 0, 0, 1)
                sivp_hint_layout.visibility = View.VISIBLE
                iv_bg_icon.visibility = View.VISIBLE
                val vipHintBean = vipHintBeans?.get(0)
                setSivpHintTitle(vipHintBean?.desc1, gradeid)
                if (!TextUtils.isEmpty(vipHintBean?.desc3)) {
                    svip_hint_subtitle.visibility = View.VISIBLE
                    svip_hint_subtitle.text = vipHintBean?.desc3
                } else {
                    svip_hint_subtitle.visibility = View.GONE
                }
                svip_hint_action.text = vipHintBean?.btn

                sivp_hint_layout.setOnClickListener {
                }
            } else {
                member_msg_rootlayout.setPadding(0, 0, 0, 12)
                sivp_hint_layout.visibility = View.GONE
                iv_bg_icon.visibility = View.GONE
            }
            member_msg_rootlayout.layoutParams = memberMsgParams
        }
    }

    private fun setSivpHintTitle(title: String?, gradeid: Int) {
        val drawableId: Int
        if (gradeid == MEMBER_TYPE_NORMAL) {
            drawableId = R.mipmap.member_svip_logo_gold
        } else {
            drawableId = R.mipmap.member_svip_logo
        }
        val imageSpan = CenterImageSpan(itemView.context, drawableId, ImageSpan.ALIGN_BASELINE)
        val spanText = SpannableString("  $title")
        spanText.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        // itemView.svip_hint_title.text = spanText
        itemView.svip_hint_title.text = title
    }

    fun setMemberLevelInfo(info: AssetInfo) {
        itemView.ic_avatar_bg.setBackgroundResource(R.drawable.bg_avatar_normal)
        if (info.memberRank != null) {
            val link = info.memberRank.link != null && (!TextUtils.isEmpty(info.memberRank.link))
            // 卓越会员
            if (info.memberRank.levelValue == MEMBER_TYPE_EXCELLENCE) {
                itemView.txt_member_level.text = ""
                itemView.txt_member_level.visibility = View.GONE
                itemView.iv_nor_member_icon.visibility = View.GONE
                itemView.iv_sss_member_icon.visibility = View.VISIBLE
                itemView.ic_crown.visibility = View.VISIBLE
                itemView.ic_avatar_bg.setBackgroundResource(R.drawable.bg_avatar_vip)
                if (link) {
                    itemView.iv_sss_member_icon.setImageResource(R.mipmap.sss_member_icon_arrow)
                    itemView.iv_sss_member_icon.setOnClickListener {
                    }
                } else {
                    itemView.iv_sss_member_icon.setImageResource(R.mipmap.sss_member_icon)
                }
            } else {
                // 普通会员
                itemView.txt_member_level.text = info.memberRank.levelNickValue
                itemView.iv_sss_member_icon.visibility = View.GONE
                itemView.iv_super_member_icon.visibility = View.GONE
                itemView.ic_crown.visibility = View.GONE
                if (TextUtils.isEmpty(info.memberRank.levelNickValue)) {
                    itemView.txt_member_level.visibility = View.GONE
                } else {
                    if (link) {
                        itemView.iv_nor_member_icon.setImageResource(R.mipmap.nor_member_icon_arrow)
                        itemView.iv_nor_member_icon.setOnClickListener {
                            //startSchema(itemView.context, info.memberRank.link ?: "")
                        }
                    } else {
                        itemView.txt_member_level.visibility = View.VISIBLE
                    }
                }
            }
            val hasLink = info.gradeDescUrl != null && (!TextUtils.isEmpty(info.gradeDescUrl))

            // 超级会员
            if (info.gradeid == MEMBER_TYPE_SUPER) {
                itemView.iv_super_member_icon.visibility = View.VISIBLE
                itemView.txt_member_level.visibility = View.GONE
                itemView.iv_nor_member_icon.visibility = View.GONE
                if (hasLink) {
                    itemView.iv_super_member_icon.setImageResource(R.mipmap.super_member_icon_arrow)
                    itemView.iv_super_member_icon.setOnClickListener {
                    }
                } else {
                    itemView.iv_super_member_icon.setImageResource(R.mipmap.super_member_icon)
                }
                itemView.iv_bg.setBackgroundResource(R.mipmap.member_header_new_black)
            } else {
                itemView.iv_bg.setBackgroundResource(R.mipmap.member_header_new_red)
                itemView.iv_super_member_icon.visibility = View.GONE
                itemView.txt_member_level.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_FFEEC2))
            }
        } else {
            setMemberLevel(info.grade)
            setMemberTypeIcon(info)
        }
    }


/*    fun setMemberLevelInfo(info: AssetInfo) {
        itemView.ic_avatar_bg.setBackgroundResource(R.drawable.bg_avatar_normal)
        if (info.memberRank != null) {
            val link = info.memberRank.link != null && (!TextUtils.isEmpty(info.memberRank.link))
            // 卓越会员
            if (info.memberRank.levelValue == MEMBER_TYPE_EXCELLENCE) {
                itemView.txt_member_level.text = ""
                itemView.txt_member_level.visibility = View.GONE
                itemView.iv_nor_member_icon.visibility = View.GONE
                itemView.iv_sss_member_icon.visibility = View.VISIBLE
                itemView.ic_crown.visibility = View.VISIBLE
                itemView.ic_avatar_bg.setBackgroundResource(R.drawable.bg_avatar_vip)
                if (link) {
                    itemView.iv_sss_member_icon.setImageResource(R.mipmap.sss_member_icon_arrow)
                    itemView.iv_sss_member_icon.setOnClickListener {
                        //startSchema(itemView.context, info.memberRank.link ?: "")
                    }
                } else {
                    itemView.iv_sss_member_icon.setImageResource(R.mipmap.sss_member_icon)
                }
            } else {
                // 普通会员
                itemView.txt_member_level.text = info.memberRank.levelNickValue
                itemView.iv_sss_member_icon.visibility = View.GONE
                itemView.iv_super_member_icon.visibility = View.GONE
                itemView.ic_crown.visibility = View.GONE
                if (TextUtils.isEmpty(info.memberRank.levelNickValue)) {
                    itemView.txt_member_level.visibility = View.GONE
                } else {
                    if (link) {
                        itemView.iv_nor_member_icon.setImageResource(R.mipmap.nor_member_icon_arrow)
                        itemView.iv_nor_member_icon.setOnClickListener {
                            //startSchema(itemView.context, info.memberRank.link ?: "")
                        }
                    } else {
                        itemView.txt_member_level.visibility = View.VISIBLE
                    }
                }
            }
            val hasLink = info.gradeDescUrl != null && (!TextUtils.isEmpty(info.gradeDescUrl))

            // 超级会员
            if (info.gradeid == MEMBER_TYPE_SUPER) {
                itemView.iv_super_member_icon.visibility = View.VISIBLE
                itemView.txt_member_level.visibility = View.GONE
                itemView.iv_nor_member_icon.visibility = View.GONE
                if (hasLink) {
                    itemView.iv_super_member_icon.setImageResource(R.mipmap.super_member_icon_arrow)
                    itemView.iv_super_member_icon.setOnClickListener {
                        //startSchema(itemView.context, info.gradeDescUrl ?: "")
                    }
                } else {
                    itemView.iv_super_member_icon.setImageResource(R.mipmap.super_member_icon)
                }
                itemView.iv_bg.setBackgroundResource(R.mipmap.member_header_new_black)
            } else {
                itemView.iv_bg.setBackgroundResource(R.mipmap.member_header_new_red)
                itemView.iv_super_member_icon.visibility = View.GONE
                itemView.txt_member_level.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_FFEEC2))
            }
        } else {
            setMemberLevel(info.grade)
            setMemberTypeIcon(info)
        }
    }*/

    fun setMemberTypeIcon(info: AssetInfo) {
        itemView.ic_crown.visibility = View.GONE
        itemView.apply {
            if (info.gradeid == MEMBER_TYPE_NORMAL) {
                txt_member_level.setTextColor(ContextCompat.getColor(context, R.color.color_FFEEC2))
            } else {
                context?.apply {
                    txt_member_level.setTextColor(ContextCompat.getColor(this, R.color.member_svip_actiontext_cloro))
                }
            }
            val hasLink = info.gradeDescUrl != null && (!TextUtils.isEmpty(info.gradeDescUrl))
            if (info.gradeid == MEMBER_TYPE_NORMAL) {
                iv_super_member_icon.visibility = View.GONE
                iv_sss_member_icon.visibility = View.GONE
                iv_super_member_icon.visibility = View.GONE
                if (hasLink) {
                    txt_member_level.visibility = View.GONE
                    iv_nor_member_icon.visibility = View.VISIBLE
                    itemView.iv_nor_member_icon.setImageResource(R.mipmap.nor_member_icon_arrow)
                    itemView.iv_nor_member_icon.setOnClickListener {
                        //startSchema(itemView.context, info.gradeDescUrl ?: "")
                    }
                } else {
                    iv_nor_member_icon.visibility = View.GONE
                    txt_member_level.visibility = View.VISIBLE
                }
            } else if (info.gradeid == MEMBER_TYPE_SUPER) {
                iv_nor_member_icon.visibility = View.GONE
                iv_sss_member_icon.visibility = View.GONE
                txt_member_level.visibility = View.GONE
                iv_super_member_icon.visibility = View.VISIBLE
                if (hasLink) {
                    itemView.iv_super_member_icon.setImageResource(R.mipmap.super_member_icon_arrow)
                    itemView.iv_super_member_icon.setOnClickListener {
                        //startSchema(itemView.context, info.gradeDescUrl ?: "")
                    }
                } else {
                    itemView.iv_super_member_icon.setImageResource(R.mipmap.super_member_icon)
                }
            } else {
                iv_nor_member_icon.visibility = View.GONE
                txt_member_level.visibility = View.GONE
                iv_super_member_icon.visibility = View.GONE
            }
            setSvipUI(info.gradeid)
        }
    }

    private fun setSvipUI(gradeId: Int) {
        itemView.apply {
            if (gradeId == MEMBER_TYPE_NORMAL) { // 普通会员
                sivp_hint_layout.setBackgroundResource(R.mipmap.member_action_black)
                iv_bg_icon.setImageDrawable(resources.getDrawable(R.mipmap.member_action_black_icon))
                iv_bg.setBackgroundResource(R.mipmap.member_header_new_red)
                svip_hint_action.setBackgroundResource(R.drawable.member_sviphint_action_bg)
                svip_hint_subtitle.setTextColor(ContextCompat.getColor(context, R.color.white))
                svip_hint_action.setTextColor(ContextCompat.getColor(context, R.color.svip_action_textcolor))
                svip_hint_title.setTextColor(ContextCompat.getColor(context, R.color.subGoldColor))
            } else { // 超级会员
                sivp_hint_layout.setBackgroundResource(R.mipmap.member_action_red)
                iv_bg_icon.setImageDrawable(resources.getDrawable(R.mipmap.member_action_gload_icon))
                iv_bg.setBackgroundResource(R.mipmap.member_header_new_black)
                svip_hint_action.setBackgroundResource(R.drawable.member_sviphint_action_black_bg)
                svip_hint_subtitle.setTextColor(ContextCompat.getColor(context, R.color.member_svip_actiontext_cloro))
                svip_hint_action.setTextColor(ContextCompat.getColor(context, R.color.subGoldColor))
                svip_hint_title.setTextColor(ContextCompat.getColor(context, R.color.member_svip_actiontext_cloro))
            }
        }
    }

    fun setMemberLevel(level: String?) {
        if (level == null || level.isEmpty()) {
            return
        }
        itemView.txt_member_level.text = level
    }


    fun setMemberPhone(phone: String?) {
        if (phone == null || phone.isEmpty()) {
            return
        }
        if (itemView.txt_phone.text.toString().isEmpty()) {
            itemView.txt_phone.text = phone
        }
    }

    fun setNickName(nickName: String?) {
        if (nickName == null) {
            return
        }
        itemView.txt_phone.text = nickName
    }

    private fun setNull() {
        val ll = itemView.my_new_wallet.findViewById(R.id.ll) as LinearLayout
        val fl = itemView.my_new_wallet as FrameLayout
        val count = fl.childCount
        for (ind in 0..count) {
            val vie = fl.getChildAt(ind)
            if (vie != null && vie is TextView) {
                fl.removeView(vie)
            }
        }
    }

    private fun setLogin(login: Boolean) {
        itemView.apply {
            mIsLogin = login
            if (login) {
                member_login.visibility = View.VISIBLE
                member_notlogin.visibility = View.GONE
            } else {
                ic_avatar_bg.setBackgroundResource(R.drawable.bg_avatar_normal)
                sivp_hint_layout.visibility = View.GONE
                iv_bg_icon.visibility = View.GONE
                member_login.visibility = View.GONE
                if (isFirst) {
                    member_notlogin.visibility = View.GONE
                } else {
                    member_notlogin.visibility = View.VISIBLE
                }
                member_msg_rootlayout.setPadding(0, 0, 0, 12)
                couponShakeAnimation?.stopAnimation()
            }
        }
    }
}