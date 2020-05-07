package com.example.zigzag.src.order.interfaces;


import com.example.zigzag.src.order.models.OrderItem;
import com.example.zigzag.src.order.models.OrderResponse;
import com.example.zigzag.src.order.models.TotalPayResponse;

import java.util.ArrayList;

public interface OrderActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void getOrderSuccess(boolean isSuccess, int code, String message, ArrayList<OrderItem> ordersResult);
    void getTotalPaySuccess(boolean isSuccess, int code, String message, TotalPayResponse.TotalPayResult payResult);

}
