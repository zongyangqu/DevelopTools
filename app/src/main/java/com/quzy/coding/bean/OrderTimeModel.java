package com.quzy.coding.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * CreateDate:2021/10/19 17:03
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
public class OrderTimeModel implements Parcelable {
    public long date;
    public ArrayList<OrderSlot> timeslots;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date);
        dest.writeList(this.timeslots);
    }

    public OrderTimeModel() {
    }

    protected OrderTimeModel(Parcel in) {
        this.date = in.readLong();
        this.timeslots = new ArrayList<OrderSlot>();
        in.readList(this.timeslots, OrderSlot.class.getClassLoader());
    }

    public static final Creator<OrderTimeModel> CREATOR = new Creator<OrderTimeModel>() {
        @Override
        public OrderTimeModel createFromParcel(Parcel source) {
            return new OrderTimeModel(source);
        }

        @Override
        public OrderTimeModel[] newArray(int size) {
            return new OrderTimeModel[size];
        }
    };
}

