package com.example.zigzag.src.product.buy.models;

import com.google.gson.annotations.SerializedName;

public class BasketBody {
    @SerializedName("item_id")
    private int item_id;

    @SerializedName("color")
    private String color;

    @SerializedName("size")
    private String size;

    @SerializedName("num")
    private int num;

    public BasketBody(int item_id, String color, String size, int num) {
        this.item_id = item_id;
        this.color = color;
        this.size = size;
        this.num = num;
    }
}
