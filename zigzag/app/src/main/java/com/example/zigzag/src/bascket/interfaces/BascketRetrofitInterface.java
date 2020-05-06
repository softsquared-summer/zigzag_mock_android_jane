package com.example.zigzag.src.bascket.interfaces;

import com.example.zigzag.src.bascket.models.BasketResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BascketRetrofitInterface {

    @GET("/baskets")
    Call<BasketResponse> getBasketList();
}
