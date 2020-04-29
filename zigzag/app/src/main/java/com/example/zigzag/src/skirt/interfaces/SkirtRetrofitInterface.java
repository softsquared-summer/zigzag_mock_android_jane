package com.example.zigzag.src.skirt.interfaces;

import com.example.zigzag.src.pants.models.DefaultResponse;
import com.example.zigzag.src.pants.models.SignInBody;
import com.example.zigzag.src.pants.models.SignInResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SkirtRetrofitInterface {
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

    @POST("/user/token")
    Call<SignInResponse> signInTest(@Body SignInBody params);
}
