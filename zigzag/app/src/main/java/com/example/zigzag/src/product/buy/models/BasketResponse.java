package com.example.zigzag.src.product.buy.models;

import com.google.gson.annotations.SerializedName;

public class BasketResponse {



    @SerializedName("result")
    private String basketResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean is_success;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    //public LoginResult getLoginResult() {
       // return loginResult;
    //}


    public String getBasketResult() {
        return basketResult;
    }

    public boolean isIs_success() {
        return is_success;
    }
}
