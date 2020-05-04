package com.example.zigzag.src.outer.content.interfaces;


import com.example.zigzag.src.outer.content.models.ItemsResponse;

import java.util.ArrayList;

public interface ContentActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void getItemsSuccess(boolean isSuccess, int code, String message, ArrayList<ItemsResponse.ItemsResult> itemsResult);

}
