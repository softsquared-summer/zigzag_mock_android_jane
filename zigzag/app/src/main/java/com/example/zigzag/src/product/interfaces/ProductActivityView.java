package com.example.zigzag.src.product.interfaces;


import com.example.zigzag.src.product.models.ItemResponse;

public interface ProductActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void getItemDetailSuccess(boolean isSuccess, int code,String message, ItemResponse.ItemResult itemResponse);

}
