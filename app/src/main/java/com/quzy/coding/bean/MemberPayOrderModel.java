package com.quzy.coding.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * CreateDate:2021/10/19 17:04
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
public class MemberPayOrderModel implements Parcelable {
    public long generate;
    public long payend;
    public long groupBuyEnd;//拼团结束时间


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.generate);
        dest.writeLong(this.payend);
        dest.writeLong(this.groupBuyEnd);
    }

    public MemberPayOrderModel() {
    }

    protected MemberPayOrderModel(Parcel in) {
        this.generate = in.readLong();
        this.payend = in.readLong();
        this.groupBuyEnd = in.readLong();
    }

    public static final Creator<MemberPayOrderModel> CREATOR = new Creator<MemberPayOrderModel>() {
        @Override
        public MemberPayOrderModel createFromParcel(Parcel source) {
            return new MemberPayOrderModel(source);
        }

        @Override
        public MemberPayOrderModel[] newArray(int size) {
            return new MemberPayOrderModel[size];
        }
    };
}
