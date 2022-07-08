package com.quzy.coding.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * CreateDate:2021/10/19 17:03
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
public class MemberOrderItemModel implements Parcelable {

    public String id;//订单id
    public int type;//订单类型：1 销售订单 、2 拼团订单 3 点餐订单-堂食 4 点餐订单-外卖
    public int status;//订单状态 1, "待支付" 2, "商品排队打包中" 3, "配送中" 4, "待自提" 5, "交易完成"
    // 6, "已取消" 7, "退款审核中" 8, "退货审核中" 9, "已退款" 10, "已拆分" 11, "仓库配送中" 12, "包裹待配送",13 "拼团中"
    // 14,"待加工" 15,"加工中"
    public String statusmsg;// 订单状态描述
    public String title;//标题
    public String productImageUrl;//图片url
    public int pickself;// 配送类型：1 自提，0 配送
    public OrderTimeModel expecttime;
    public MemberPayOrderModel timeInfo;
    public String detailaction;//跳转目标页
    public String groupInfo;//拼团描述，例如：2人团、3人团。
    public boolean isVip=false;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.type);
        dest.writeInt(this.status);
        dest.writeString(this.title);
        dest.writeString(this.productImageUrl);
        dest.writeInt(this.pickself);
        dest.writeParcelable(this.expecttime, flags);
        dest.writeParcelable(this.timeInfo, flags);
        dest.writeString(this.detailaction);
        dest.writeString(this.groupInfo);
        dest.writeByte(this.isVip ? (byte) 1 : (byte) 0);
        dest.writeString(statusmsg);
    }

    public MemberOrderItemModel() {
    }

    protected MemberOrderItemModel(Parcel in) {
        this.id = in.readString();
        this.type = in.readInt();
        this.status = in.readInt();
        this.title = in.readString();
        this.productImageUrl = in.readString();
        this.pickself = in.readInt();
        this.expecttime = in.readParcelable(OrderTimeModel.class.getClassLoader());
        this.timeInfo = in.readParcelable(MemberPayOrderModel.class.getClassLoader());
        this.detailaction = in.readString();
        this.groupInfo = in.readString();
        this.isVip = in.readByte() != 0;
        this.statusmsg = in.readString();
    }

    public static final Creator<MemberOrderItemModel> CREATOR = new Creator<MemberOrderItemModel>() {
        @Override
        public MemberOrderItemModel createFromParcel(Parcel source) {
            return new MemberOrderItemModel(source);
        }

        @Override
        public MemberOrderItemModel[] newArray(int size) {
            return new MemberOrderItemModel[size];
        }
    };
}

