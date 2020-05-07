package com.example.zigzag.src.order.models;

import com.google.gson.annotations.SerializedName;


public class OrderItem {
    @SerializedName("item_id")
    private int itemID;
    @SerializedName("mall_name")
    private String mallName;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("image")
    private ImageList image;
    @SerializedName("size")
    private String size;
    @SerializedName("color")
    private String color;
    @SerializedName("num")
    private int num;
    @SerializedName("price")
    private String price;
    @SerializedName("ship")
    private int ship;
    @SerializedName("status")
    private int status;

    public int getItemID() {
        return itemID;
    }

    public String getMallName() {
        return mallName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getStatus() {
        return status;
    }

    public ImageList getImage() {
        return image;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public int getNum() {
        return num;
    }

    public String getPrice() {
        return price;
    }

    public int getShip() {
        return ship;
    }

    public class ImageList {
        @SerializedName("image_url1")
        private String image_url1;
        @SerializedName("image_url2")
        private String image_url2;

        public String getImage_url1() {
            return image_url1;
        }

        public String getImage_url2() {
            return image_url2;
        }
    }
}
