package com.example.zigzag.src.order_completed.interfaces;

import com.example.zigzag.src.order_completed.models.OrderResponse;
import com.example.zigzag.src.order_completed.models.TotalPayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderRetrofitInterface {

    @GET("/orders")
    Call<OrderResponse> getOrderList();





}
