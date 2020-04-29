package com.example.zigzag.src.bascket.interfaces;


import com.example.zigzag.src.bascket.models.SignInResponse;

public interface BascketActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void signInSuccess(SignInResponse.SignInResult signInResult);

}
