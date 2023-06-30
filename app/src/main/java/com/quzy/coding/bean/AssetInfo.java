package com.quzy.coding.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Map;

/**
 * CreateDate:2021/10/19 16:29
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
public class AssetInfo implements Parcelable {
    public int memcardstate;
    public int balance;
    public String walletdesc;
    public String walleturl;
    public int validity;
    public String grade;
    public int gradeid;
    public int hasorder;
    public int credit;
    public int coupon;
    public int freeshipping;
    public String freeshippingdesc;
    public String fillMemInfoTips;
    public int growth;
    public String mobile;
    public String nickname;
    public String duestip;
    public String renewlinkdesc;//续卡
    public String renewlink;//续卡链接
    public int toPay;
    public int toPickup;
    public int toDelivery;
    public int toReceive;
    public int renew;//1去续费
    public int toComment;//待评价订单数
    public int refunding;
    public String privilegeDesc;
    public String privilegeAction;
    public int bravoCardFlag; //bravo卡绑定情况，0没有Bravo卡 1有卡没有绑定 2 已绑定
    public CsxManagerRep csxManagerRep;
    public int memberType;//会员类型，0个人用户 1企业用户
    public static final int TYPE_RENEW = 1;//去续费
    public int needSetPassword;//是否有设置密码
    public ArrayList<CommonProductBean> favorskus;//我常买商品列表
    public ArrayList<MemberOrderItemModel> orderListRep;//订单列表
    public static final int NO_BRAVO_CARD = 0;
    public static final int BRAVO_UNBIND = 1;
    public static final int BRAVO_BINDED = 2;
    public int hasdigitpaypassword;//是否已设置数字支付密码，1(是),0(否)
    public VipHintBean rights; //会员完成N单领券
    public ArrayList<VipHintBean> rightsList;//4.3.2新增会员完成N单领券
    public String ispartner;//是否是分销合伙人
    public String invitationUrl;//邀请好友活动页地址
    public GiftCardModel page;//服务端配置页面
    public GiftCardBean giftCard;//礼品卡相关(是否有礼品卡和礼品卡余额)
    public int couponpendingcount;//券可领取数量
    public String toCommentMsg;//待评价抵现金文案
    public String gradeDescUrl;//会员等级跳转链接
    public String cmsPagePit;//个人中心 活动坑位id
    public boolean svipGrayUser = false;//true 是灰度用户新样式/else 老样式---超级会员
    public String avatar;//头像，目前只有微信登录才有
    public ArrayList<VipHintBean> vipRightsList = new ArrayList<>();// 新版超级会员提示数据
    public boolean svipUser;//新版超级会员 5.2.0
    public String creditDetailUrl;
    public String creditDetailUrlName;
    public OffineStoreInfo shopDiscountsVo;
    public GiftCardListBean certificate;  // 企业资质
    public GiftCardListBean onlineService;  // 客服中心
    public ArrayList<PayConfigInfo> payConfigInfos;// 小辉付配置字段
    public ArrayList<DoubleElevenPromotions> doubleElevenPromotions;// 双十一大促
    //不加入序列化的字段
    public Map<String, Boolean> memberNewCustomerCouponInfo;

    public ArrayList<CustomerProperties>  customerProperties;
    public ArrayList<PromotionAndFunction>  promotionsAndFunctions;
    public ActivityAreaBean activityArea; //天天逛 享优惠活动卡片信息

    // 头像
    public String avatarImageUrl;

    public MemberRankBean memberRank;

    public int afterSaleProcessingQuantity;

    //个人中心banner数据
    public CommonBannerResponse userCenterBanner;

    public ArrayList<ProductBlock> results; //商品数据

    @Override
    public String toString() {
        return "AssetInfo{" +
                "memcardstate=" + memcardstate +
                ", balance=" + balance +
                ", walletdesc='" + walletdesc + '\'' +
                ", walleturl='" + walleturl + '\'' +
                ", validity=" + validity +
                ", grade='" + grade + '\'' +
                ", gradeid=" + gradeid +
                ", hasorder=" + hasorder +
                ", credit=" + credit +
                ", coupon=" + coupon +
                ", freeshipping=" + freeshipping +
                ", freeshippingdesc='" + freeshippingdesc + '\'' +
                ", growth=" + growth +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", duestip='" + duestip + '\'' +
                ", renewlinkdesc='" + renewlinkdesc + '\'' +
                ", renewlink='" + renewlink + '\'' +
                ", toPay=" + toPay +
                ", toPickup=" + toPickup +
                ", toDelivery=" + toDelivery +
                ", toReceive=" + toReceive +
                ", renew=" + renew +
                ", toComment=" + toComment +
                ", refunding=" + refunding +
                ", privilegeDesc='" + privilegeDesc + '\'' +
                ", privilegeAction='" + privilegeAction + '\'' +
                ", bravoCardFlag=" + bravoCardFlag +
                ", csxManagerRep=" + csxManagerRep +
                ", memberType=" + memberType +
                ", needSetPassword=" + needSetPassword +
                ", orderListRep=" + orderListRep +
                ", hasdigitpaypassword=" + hasdigitpaypassword +
                ", rights=" + rights +
                ", rightsList=" + rightsList +
                ", ispartner='" + ispartner + '\'' +
                ", invitationUrl='" + invitationUrl + '\'' +
                ", page=" + page +
                ", giftCard=" + giftCard +
                ", couponpendingcount=" + couponpendingcount +
                ", toCommentMsg='" + toCommentMsg + '\'' +
                ", avatar='" + avatar + '\'' +
                ", creditDetailUrl='" + creditDetailUrl + '\'' +
                ", creditDetailUrlName='" + creditDetailUrlName + '\'' +
                '}';
    }


    public AssetInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.memcardstate);
        dest.writeInt(this.balance);
        dest.writeString(this.walletdesc);
        dest.writeString(this.walleturl);
        dest.writeInt(this.validity);
        dest.writeString(this.grade);
        dest.writeInt(this.gradeid);
        dest.writeInt(this.hasorder);
        dest.writeInt(this.credit);
        dest.writeInt(this.coupon);
        dest.writeInt(this.freeshipping);
        dest.writeString(this.freeshippingdesc);
        dest.writeInt(this.growth);
        dest.writeString(this.mobile);
        dest.writeString(this.nickname);
        dest.writeString(this.duestip);
        dest.writeString(this.renewlinkdesc);
        dest.writeString(this.renewlink);
        dest.writeInt(this.toPay);
        dest.writeInt(this.toPickup);
        dest.writeInt(this.toDelivery);
        dest.writeInt(this.toReceive);
        dest.writeInt(this.renew);
        dest.writeInt(this.toComment);
        dest.writeInt(this.refunding);
        dest.writeString(this.privilegeDesc);
        dest.writeString(this.privilegeAction);
        dest.writeInt(this.bravoCardFlag);
        dest.writeParcelable(this.csxManagerRep, flags);
        dest.writeInt(this.memberType);
        dest.writeInt(this.needSetPassword);
        dest.writeTypedList(this.orderListRep);
        dest.writeInt(this.hasdigitpaypassword);
        dest.writeParcelable(this.rights, flags);
        dest.writeTypedList(this.rightsList);
        dest.writeString(this.ispartner);
        dest.writeString(this.invitationUrl);
        dest.writeParcelable(this.page, flags);
        dest.writeParcelable(this.giftCard, flags);
        dest.writeInt(this.couponpendingcount);
        dest.writeString(this.toCommentMsg);
        dest.writeString(this.gradeDescUrl);
        dest.writeString(this.cmsPagePit);
        dest.writeByte(this.svipGrayUser ? (byte) 1 : (byte) 0);
        dest.writeString(this.avatar);
        dest.writeTypedList(this.vipRightsList);
        dest.writeString(this.creditDetailUrl);
        dest.writeString(this.creditDetailUrlName);
        dest.writeParcelable(this.shopDiscountsVo, flags);
        dest.writeParcelable(this.certificate, flags);
        dest.writeParcelable(this.onlineService, flags);
        dest.writeTypedList(this.payConfigInfos);
        dest.writeString(this.avatarImageUrl);
        dest.writeParcelable(this.memberRank, flags);
        dest.writeInt(this.afterSaleProcessingQuantity);
    }

    protected AssetInfo(Parcel in) {
        this.memcardstate = in.readInt();
        this.balance = in.readInt();
        this.walletdesc = in.readString();
        this.walleturl = in.readString();
        this.validity = in.readInt();
        this.grade = in.readString();
        this.gradeid = in.readInt();
        this.hasorder = in.readInt();
        this.credit = in.readInt();
        this.coupon = in.readInt();
        this.freeshipping = in.readInt();
        this.freeshippingdesc = in.readString();
        this.growth = in.readInt();
        this.mobile = in.readString();
        this.nickname = in.readString();
        this.duestip = in.readString();
        this.renewlinkdesc = in.readString();
        this.renewlink = in.readString();
        this.toPay = in.readInt();
        this.toPickup = in.readInt();
        this.toDelivery = in.readInt();
        this.toReceive = in.readInt();
        this.renew = in.readInt();
        this.toComment = in.readInt();
        this.refunding = in.readInt();
        this.privilegeDesc = in.readString();
        this.privilegeAction = in.readString();
        this.bravoCardFlag = in.readInt();
        this.csxManagerRep = in.readParcelable(CsxManagerRep.class.getClassLoader());
        this.memberType = in.readInt();
        this.needSetPassword = in.readInt();
        this.orderListRep = in.createTypedArrayList(MemberOrderItemModel.CREATOR);
        this.hasdigitpaypassword = in.readInt();
        this.rights = in.readParcelable(VipHintBean.class.getClassLoader());
        this.rightsList = in.createTypedArrayList(VipHintBean.CREATOR);
        this.ispartner = in.readString();
        this.invitationUrl = in.readString();
        this.page = in.readParcelable(GiftCardModel.class.getClassLoader());
        this.giftCard = in.readParcelable(GiftCardBean.class.getClassLoader());
        this.couponpendingcount = in.readInt();
        this.toCommentMsg = in.readString();
        this.gradeDescUrl = in.readString();
        this.cmsPagePit = in.readString();
        this.svipGrayUser = in.readByte() != 0;
        this.avatar = in.readString();
        this.vipRightsList = in.createTypedArrayList(VipHintBean.CREATOR);
        this.creditDetailUrl = in.readString();
        this.creditDetailUrlName = in.readString();
        this.shopDiscountsVo = in.readParcelable(OffineStoreInfo.class.getClassLoader());
        this.certificate = in.readParcelable(GiftCardListBean.class.getClassLoader());
        this.onlineService = in.readParcelable(GiftCardListBean.class.getClassLoader());
        this.payConfigInfos = in.createTypedArrayList(PayConfigInfo.CREATOR);
        this.avatarImageUrl = in.readString();
        this.memberRank = in.readParcelable(MemberRankBean.class.getClassLoader());
        this.afterSaleProcessingQuantity = in.readInt();
    }

    public static final Creator<AssetInfo> CREATOR = new Creator<AssetInfo>() {
        @Override
        public AssetInfo createFromParcel(Parcel source) {
            return new AssetInfo(source);
        }

        @Override
        public AssetInfo[] newArray(int size) {
            return new AssetInfo[size];
        }
    };
}

