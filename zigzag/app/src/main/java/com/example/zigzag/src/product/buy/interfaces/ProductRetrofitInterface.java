package com.example.zigzag.src.product.buy.interfaces;

import com.example.zigzag.src.product.buy.models.DefaultResponse;
import com.example.zigzag.src.product.buy.models.LoginBody;
import com.example.zigzag.src.product.buy.models.LoginResponse;

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

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/test")
    Call<DefaultResponse> postTest(@Body RequestBody params);

    //@POST("houses/{houseNo}/reservations")
    //Call<DefaultResponse> postReserve(@Path("houseNo") int houseNo, @Body RequestReserve requestReserve);

    @POST("/login")
    Call<LoginResponse> Login(@Body LoginBody params);
}
