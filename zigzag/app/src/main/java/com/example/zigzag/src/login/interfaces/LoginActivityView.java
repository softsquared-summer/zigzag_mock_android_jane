package com.example.zigzag.src.login.interfaces;


import com.example.zigzag.src.login.models.LoginResponse;

public interface LoginActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void LoginSuccess(boolean isSuccess,int code,String loginResult);

}
