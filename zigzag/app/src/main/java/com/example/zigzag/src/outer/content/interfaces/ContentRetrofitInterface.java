package com.example.zigzag.src.outer.content.interfaces;

import com.example.zigzag.src.login.models.DefaultResponse;
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

    @GET("/items")
    Call<ItemsResponse> getItemList(
            @Query("category") final String category,
            @Query("category_detail") final String category_detail


    );
    //@GET("/items")
    //Call<ItemsResponse> getItemList();
}
