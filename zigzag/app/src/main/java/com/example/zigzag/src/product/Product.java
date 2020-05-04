package com.example.zigzag.src.product;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int mitemId;
    private String mItemCategory;
    private String getmItemCategoryDetail;
    private String mimageUrl1;
    private String mimageUrl2;
    private String isFreeShip; //무료배송 여부
    private String isHeart; //찜하기 여부
    private String mMallName;
    private String mItemName;
    private String mDiscount;
    private String mPrice;

    public Product(int mitemId, String mItemCategory, String getmItemCategoryDetail, String mMallName, String mimageUrl1, String mimageUrl2, String mItemName, int mCommentNum, String isFreeShip, String isHeart, String mDiscount, String mPrice) {
        this.mitemId = mitemId;
        this.mItemCategory = mItemCategory;
        this.getmItemCategoryDetail = getmItemCategoryDetail;
        this.mMallName = mMallName;
        this.mimageUrl1 = mimageUrl1;
        this.mimageUrl2 = mimageUrl2;
        this.mItemName = mItemName;
        this.isFreeShip = isFreeShip;
        this.isHeart = isHeart;
        this.mDiscount = mDiscount;
        this.mPrice = mPrice;
    }

    private Product(Parcel in) {
        this.mitemId = in.readInt();
        this.mMallName = in.readString();
        this.mimageUrl1 = in.readString();
        this.mimageUrl2 = in.readString();
        this.mItemName = in.readString();
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

    public String getMimageUrl1() {
        return mimageUrl1;
    }

    public String getMimageUrl2() {
        return mimageUrl2;
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

    public String getmItemCategory() {
        return mItemCategory;
    }

    public String getGetmItemCategoryDetail() {
        return getmItemCategoryDetail;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public String getmDiscount() {
        return mDiscount;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mitemId);
        dest.writeString(this.mItemCategory);
        dest.writeString(this.getmItemCategoryDetail);
        dest.writeString(this.mMallName);
        dest.writeString(this.mimageUrl1);
        dest.writeString(this.mimageUrl2);
        dest.writeString(this.mItemName);
        dest.writeString(this.isFreeShip);
        dest.writeString(this.isHeart);
        dest.writeString(this.mDiscount);
        dest.writeString(this.mPrice);



    }
}
