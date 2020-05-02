package com.example.zigzag.src.product.buy.interfaces;


public interface ProductActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void LoginSuccess(boolean isSuccess, int code, String loginResult);

}
