package com.example.zigzag.src.main;


import com.example.zigzag.src.main.interfaces.MainActivityView;
import com.example.zigzag.src.main.interfaces.MainRetrofitInterface;
import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.main.models.SignInBody;
import com.example.zigzag.src.main.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class MainService {
    private final MainActivityView mMainActivityView;

    MainService(final MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }

    void getTest() {


        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);


        mainRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {

                   mMainActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mMainActivityView.validateSuccess(defaultResponse.getMessage());
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }


    void postSignIn(String id, String pw) {
        final MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);


        mainRetrofitInterface.signInTest(new SignInBody(id,pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final SignInResponse signInResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (signInResponse == null) {

                    mMainActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mMainActivityView.signInSuccess(signInResponse.getSignInResult());
            }


            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mMainActivityView.validateFailure(null);
            }
        });
    }
}
