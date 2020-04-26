package com.example.zigzag.src.splash;


import com.example.zigzag.src.splash.interfaces.SplashActivityView;
import com.example.zigzag.src.splash.interfaces.SplashRetrofitInterface;
import com.example.zigzag.src.splash.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class SplashService {
    private final SplashActivityView mSplashActivityView;

    SplashService(final SplashActivityView mainActivityView) {
        this.mSplashActivityView = mainActivityView;
    }

    void getTest() {


        final SplashRetrofitInterface mainRetrofitInterface = getRetrofit().create(SplashRetrofitInterface.class);


        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {

                   mSplashActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mSplashActivityView.validateSuccess(defaultResponse.getMessage());
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mSplashActivityView.validateFailure(null);
            }
        });
    }



}
