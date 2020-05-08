package com.example.zigzag.src.order_completed.interfaces;


import com.example.zigzag.src.order_completed.models.OrderResponse;
import com.example.zigzag.src.order_completed.models.TotalPayResponse;

import java.util.ArrayList;

public interface OrderActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void getOrderSuccess(boolean isSuccess, int code, String message, ArrayList<OrderResponse.OrderResult> ordersResult);

}
