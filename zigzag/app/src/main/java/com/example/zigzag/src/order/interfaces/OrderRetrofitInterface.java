package com.example.zigzag.src.order.interfaces;

import com.example.zigzag.src.order.models.OrderResponse;
import com.example.zigzag.src.order.models.TotalPayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderRetrofitInterface {

    @GET("/orders")
    Call<OrderResponse> getOrderList();

    @GET("/totalpay")
    Call<TotalPayResponse> getTotalPay(
            @Query("item_id1") final String itemId1,
            @Query("item_id2") final String itemId2,
            @Query("item_id3") final String itemId3,
            @Query("item_id4") final String itemId4,
            @Query("item_id5") final String itemId5
            );



}
