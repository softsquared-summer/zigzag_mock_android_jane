package com.example.zigzag.src.skirt.interfaces;


import com.example.zigzag.src.pants.models.SignInResponse;

public interface SkirtActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void signInSuccess(SignInResponse.SignInResult signInResult);

}
