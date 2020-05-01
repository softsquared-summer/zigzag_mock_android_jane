package com.example.zigzag.src.product;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int mitemId;
    private String mMallName;
    private String mimageUrl;
    private String mItemName;
    private int mCommentNum;
    private String isFreeShip; //무료배송 여부
    private String isHeart; //찜하기 여부
    private String mDiscount;
    private String mPrice;

    public Product(int mitemId, String mMallName, String mimageUrl, String mItemName, int mCommentNum, String isFreeShip, String isHeart, String mDiscount, String mPrice) {
        this.mitemId = mitemId;
        this.mMallName = mMallName;
        this.mimageUrl = mimageUrl;
        this.mItemName = mItemName;
        this.mCommentNum = mCommentNum;
        this.isFreeShip = isFreeShip;
        this.isHeart = isHeart;
        this.mDiscount = mDiscount;
        this.mPrice = mPrice;
    }

    private Product(Parcel in) {
        this.mitemId = in.readInt();
        this.mMallName = in.readString();
        this.mimageUrl = in.readString();
        this.mItemName = in.readString();
        this.mCommentNum = in.readInt();
        this.isFreeShip = in.readString();
        this.isHeart = in.readString();
        this.mDiscount = in.readString();
        this.mPrice = in.readString();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {

        public Product createFromParcel(Parcel in) {
            return new Product(in);

        }

        public Product[] newArray (int size) {
            return new Product[size];

        }

    };
    public String getmItemName() {
        return mItemName;
    }

    public int getMitemId() {
        return mitemId;
    }

    public String getMimageUrl() {
        return mimageUrl;
    }

    public String getIsFreeShip() {
        return isFreeShip;
    }

    public String getIsHeart() {
        return isHeart;
    }

    public String getmMallName() {
        return mMallName;
    }

    public String getmPrice() {
        return mPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mitemId);
        dest.writeString(this.mMallName);
        dest.writeString(this.mimageUrl);
        dest.writeString(this.mItemName);
        dest.writeInt(this.mCommentNum);
        dest.writeString(this.isFreeShip);
        dest.writeString(this.isHeart);
        dest.writeString(this.mDiscount);
        dest.writeString(this.mPrice);



    }
}
