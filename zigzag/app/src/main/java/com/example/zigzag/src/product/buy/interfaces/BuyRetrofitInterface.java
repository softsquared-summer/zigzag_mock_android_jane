package com.example.zigzag.src.product.buy.interfaces;

import com.example.zigzag.src.product.buy.models.DefaultResponse;
import com.example.zigzag.src.product.buy.models.BasketBody;
import com.example.zigzag.src.product.buy.models.BasketResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BuyRetrofitInterface {
//    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();


    //@POST("houses/{houseNo}/reservations")
    //Call<DefaultResponse> postReserve(@Path("houseNo") int houseNo, @Body RequestReserve requestReserve);

    @POST("/basket")
    Call<BasketResponse> Login(@Body BasketBody params);
}
