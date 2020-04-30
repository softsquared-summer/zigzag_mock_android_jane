package com.example.zigzag.src.login.models;

import com.google.gson.annotations.SerializedName;

public class SignInBody {
    @SerializedName("id")
    private String id;

    @SerializedName("pw")
    private String pw;

    public SignInBody(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
