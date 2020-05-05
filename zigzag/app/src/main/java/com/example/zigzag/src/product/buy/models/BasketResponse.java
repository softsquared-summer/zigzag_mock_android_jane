package com.example.zigzag.src.product.buy.models;

import com.google.gson.annotations.SerializedName;

public class BasketResponse {


    public class LoginResult {
        @SerializedName("jwt")
        private String jwt;

        public String getJwt() {
            return jwt;
        }


    }

    @SerializedName("result")
    private String loginResult;

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


    public boolean getIs_success() {
        return is_success;
    }

    public String getLoginResult() {
        return loginResult;
    }
}
