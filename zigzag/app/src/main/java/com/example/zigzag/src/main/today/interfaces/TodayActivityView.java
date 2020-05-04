package com.example.zigzag.src.main.today.interfaces;


import com.example.zigzag.src.main.models.SignInResponse;
import com.example.zigzag.src.main.today.models.ItemsResponse;

import java.util.ArrayList;

public interface TodayActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void getItemsSuccess(boolean isSuccess, int code, String message, ArrayList<ItemsResponse.ItemsResult> itemsResult);

}
