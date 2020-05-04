package com.example.zigzag.src.outer.content.interfaces;

import com.example.zigzag.src.outer.content.models.ItemsResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ContentRetrofitInterface {
//    @GET("/test")


    @POST("/items")
    Call<ItemsResponse> getItemList();
}
