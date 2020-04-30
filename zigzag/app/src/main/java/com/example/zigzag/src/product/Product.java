package com.example.zigzag.src.product;

public class Product {
    private int mitemId;
    private String mimageUrl;
    private String isFreeShip; //무료배송 여부
    private String isHeart; //찜하기 여부
    private String mMallName;
    private String mItemName;
    private String mDiscount;
    private String mPrice;

    public Product(int mitemId, String mimageUrl, String isFreeShip, String isHeart, String mMallName, String mItemName, String mDiscount, String mPrice) {
        this.mitemId = mitemId;
        this.mimageUrl = mimageUrl;
        this.isFreeShip = isFreeShip;
        this.isHeart = isHeart;
        this.mMallName = mMallName;
        this.mItemName = mItemName;
        this.mDiscount = mDiscount;
        this.mPrice = mPrice;
    }

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
}
