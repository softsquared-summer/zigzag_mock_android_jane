package com.example.zigzag.src.main.today.interfaces;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.main.models.SignInBody;
import com.example.zigzag.src.main.models.SignInResponse;
import com.example.zigzag.src.main.today.models.ItemsResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TodayRetrofitInterface {
//    @GET("/test")




    @GET("/items")
    Call<ItemsResponse> getItemsList();
}
