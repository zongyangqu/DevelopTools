package com.quzy.coding.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * CreateDate:2022/7/1 10:08
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
public class CouponMineDataBean  implements Parcelable {


    public int catalog;//券类型，1:现金券,2:免邮券,3:满减券,4:赠品券,5:会员特权券,6:折扣券,7:商品兑换券,8:线下现金券,9:线下规则券,10:线下印花券,11:微信代金券,12:线上线下-满减券,13:支付宝代金券,0:其它
    public int amount; //现金券和满减券的券价值
    public String conditiondesc ;//使用条件，比如（满xx元可用）
    public String subtitle; //  副标题,永辉生活APP 4.3.6开始支持(4.3.6折扣券使用)
    public String expirationDesc; //  副标题,永辉生活APP 4.3.6开始支持(4.3.6折扣券使用)

    protected CouponMineDataBean(Parcel in) {
        catalog = in.readInt();
        amount = in.readInt();
        conditiondesc = in.readString();
        subtitle = in.readString();
    }

    public static final Creator<CouponMineDataBean> CREATOR = new Creator<CouponMineDataBean>() {
        @Override
        public CouponMineDataBean createFromParcel(Parcel in) {
            return new CouponMineDataBean(in);
        }

        @Override
        public CouponMineDataBean[] newArray(int size) {
            return new CouponMineDataBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(catalog);
        dest.writeInt(amount);
        dest.writeString(conditiondesc);
        dest.writeString(subtitle);
    }
}

