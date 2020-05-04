package com.example.zigzag.src.product.interfaces;

import com.example.zigzag.src.product.models.DefaultResponse;
import com.example.zigzag.src.product.models.ItemResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductRetrofitInterface {
//    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{itemID}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("itemID") int number,
            @Query("content") final String content
    );


    //@POST("houses/{houseNo}/reservations")
    //Call<DefaultResponse> postReserve(@Path("houseNo") int houseNo, @Body RequestReserve requestReserve);

    @GET("/items/{itemID}")
    Call<ItemResponse> getItemDetail(@Path("itemID") final int number);
}
