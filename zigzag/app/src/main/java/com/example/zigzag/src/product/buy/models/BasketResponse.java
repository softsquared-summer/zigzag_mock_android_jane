package com.example.zigzag.src.product.buy.models;

import com.google.gson.annotations.SerializedName;

public class BasketResponse {


    public class BasketResult {
        @SerializedName("jwt")
        private String jwt;

        public String getJwt() {
            return jwt;
        }


    }

    @SerializedName("result")
    private BasketResult basketResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("is_success")
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

    public BasketResult getBasketResult() {
        return basketResult;
    }

    public boolean isIs_success() {
        return is_success;
    }
}
