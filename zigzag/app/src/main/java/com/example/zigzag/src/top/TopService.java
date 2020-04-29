package com.example.zigzag.src.top;


import com.example.zigzag.src.top.interfaces.TopActivityView;
import com.example.zigzag.src.top.interfaces.TopRetrofitInterface;
import com.example.zigzag.src.top.models.DefaultResponse;
import com.example.zigzag.src.top.models.SignInBody;
import com.example.zigzag.src.top.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class TopService {
    private final TopActivityView mTopActivityView;

    TopService(final TopActivityView topActivityView) {
        this.mTopActivityView = topActivityView;
    }

    void getTest() {


        final TopRetrofitInterface topRetrofitInterface = getRetrofit().create(TopRetrofitInterface.class);


        topRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {

                   mTopActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mTopActivityView.validateSuccess(defaultResponse.getMessage());
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mTopActivityView.validateFailure(null);
            }
        });
    }


    void postSignIn(String id, String pw) {
        final TopRetrofitInterface topRetrofitInterface = getRetrofit().create(TopRetrofitInterface.class);


        topRetrofitInterface.signInTest(new SignInBody(id,pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final SignInResponse signInResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (signInResponse == null) {

                    mTopActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mTopActivityView.signInSuccess(signInResponse.getSignInResult());
            }


            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mTopActivityView.validateFailure(null);
            }
        });
    }
}
