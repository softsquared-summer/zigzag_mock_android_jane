package com.example.zigzag.src.main.store.ranking;


import com.example.zigzag.src.main.interfaces.MainActivityView;
import com.example.zigzag.src.main.interfaces.MainRetrofitInterface;
import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.main.models.SignInBody;
import com.example.zigzag.src.main.models.SignInResponse;
import com.example.zigzag.src.main.store.ranking.interfaces.RankActivityView;
import com.example.zigzag.src.main.store.ranking.interfaces.RankRetrofitInterface;
import com.example.zigzag.src.main.store.ranking.models.RankResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class RankingService {
    private final RankActivityView mRankActivityView;

    RankingService(final RankActivityView mRankActivityView) {
        this.mRankActivityView = mRankActivityView;
    }

    void getStoreRank() {
        final RankRetrofitInterface rankRetrofitInterface = getRetrofit().create(RankRetrofitInterface.class);


        rankRetrofitInterface.getStoreRank().enqueue(new Callback<RankResponse>() {
            @Override
            public void onResponse(Call<RankResponse> call, Response<RankResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final RankResponse rankResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (rankResponse == null) {

                    mRankActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mRankActivityView.validateRank(rankResponse.isIs_success(),rankResponse.getCode(),rankResponse.getMessage()
                ,rankResponse.getRankResult());
            }


            @Override
            public void onFailure(Call<RankResponse> call, Throwable t) {
                mRankActivityView.validateFailure(null);
                System.out.println("쇼핑몰 랭킹 통신 실패 "+t);
            }
        });
    }
}
