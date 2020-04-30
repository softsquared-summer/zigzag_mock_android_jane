package com.example.zigzag.src.login.models;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {


    public class SignInResult {
        @SerializedName("jwt")
        private int jwt;

        @SerializedName("userNo")
        private String userNo;

        public int getJwt() {
            return jwt;
        }

        public String getUserNo() {
            return userNo;
        }
    }
    @SerializedName("result")
    private SignInResult signInResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public SignInResult getSignInResult() {
        return signInResult;
    }
}
