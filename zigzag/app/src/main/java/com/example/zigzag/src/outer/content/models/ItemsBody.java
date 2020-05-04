package com.example.zigzag.src.outer.content.models;

import com.google.gson.annotations.SerializedName;

public class ItemsBody {
    @SerializedName("category")
    private String category;

    @SerializedName("category_detail")
    private String category_detail;

    public ItemsBody(String category, String category_detail) {
        this.category = category;
        this.category_detail = category_detail;
    }
}
