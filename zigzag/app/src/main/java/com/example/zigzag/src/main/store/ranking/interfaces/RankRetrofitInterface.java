package com.example.zigzag.src.main.store.ranking.interfaces;

import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.main.models.SignInBody;
import com.example.zigzag.src.main.models.SignInResponse;
import com.example.zigzag.src.main.store.ranking.models.RankResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RankRetrofitInterface {

    @GET("/malls")
    Call<RankResponse> getStoreRank();

}
