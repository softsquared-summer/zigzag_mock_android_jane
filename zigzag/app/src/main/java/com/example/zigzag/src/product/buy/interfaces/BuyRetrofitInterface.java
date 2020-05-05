package com.example.zigzag.src.product.buy.interfaces;

import com.example.zigzag.src.product.buy.models.BasketBody;
import com.example.zigzag.src.product.buy.models.BasketResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BuyRetrofitInterface {
//    @GET("/test")



    //@POST("houses/{houseNo}/reservations")
    //Call<DefaultResponse> postReserve(@Path("houseNo") int houseNo, @Body RequestReserve requestReserve);

    @POST("/basket")
    Call<BasketResponse> postBasket(@Body BasketBody params);
}
