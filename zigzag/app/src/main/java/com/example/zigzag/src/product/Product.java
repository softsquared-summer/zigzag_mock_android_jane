package com.example.zigzag.src.product;

public class Product {
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
