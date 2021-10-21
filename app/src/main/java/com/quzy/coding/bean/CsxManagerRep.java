package com.quzy.coding.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * CreateDate:2021/10/19 16:45
 *
 * @author: zongyang qu
 * @Package： com.quzy.coding.bean
 * @Description:
 */
public class CsxManagerRep implements Parcelable{
    public String managerMobile;
    public String managerName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.managerMobile);
        dest.writeString(this.managerName);
    }

    public CsxManagerRep() {
    }

    protected CsxManagerRep(Parcel in) {
        this.managerMobile = in.readString();
        this.managerName = in.readString();
    }

    public static final Creator<CsxManagerRep> CREATOR = new Creator<CsxManagerRep>() {
        @Override
        public CsxManagerRep createFromParcel(Parcel source) {
            return new CsxManagerRep(source);
        }

        @Override
        public CsxManagerRep[] newArray(int size) {
            return new CsxManagerRep[size];
        }
    };

    @Override
    public String toString() {
        return "CsxManagerRep{" +
                "managerMobile='" + managerMobile + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
