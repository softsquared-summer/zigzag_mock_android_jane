package com.example.zigzag.src.product.buy.interfaces;


import com.example.zigzag.src.product.buy.models.BasketResponse;

public interface BuyActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void basketSuccess(boolean isSuccess, int code, String message);

}
