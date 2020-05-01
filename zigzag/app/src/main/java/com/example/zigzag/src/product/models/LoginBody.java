package com.example.zigzag.src.product.models;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginBody(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
