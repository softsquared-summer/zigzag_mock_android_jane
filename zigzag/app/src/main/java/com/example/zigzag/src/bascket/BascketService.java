package com.example.zigzag.src.bascket;


import com.example.zigzag.src.bascket.interfaces.BascketActivityView;
import com.example.zigzag.src.bascket.interfaces.BascketRetrofitInterface;
import com.example.zigzag.src.bascket.models.DefaultResponse;
import com.example.zigzag.src.bascket.models.SignInBody;
import com.example.zigzag.src.bascket.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class BascketService {
    private final BascketActivityView mBascketActivityView;

    BascketService(final BascketActivityView bascketActivityView) {
        this.mBascketActivityView = bascketActivityView;
    }

    void getTest() {


        final BascketRetrofitInterface bascketRetrofitInterface = getRetrofit().create(BascketRetrofitInterface.class);


        bascketRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {

                   mBascketActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mBascketActivityView.validateSuccess(defaultResponse.getMessage());
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mBascketActivityView.validateFailure(null);
            }
        });
    }


    void postSignIn(String id, String pw) {
        final BascketRetrofitInterface bascketRetrofitInterface = getRetrofit().create(BascketRetrofitInterface.class);


        bascketRetrofitInterface.signInTest(new SignInBody(id,pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final SignInResponse signInResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (signInResponse == null) {

                    mBascketActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mBascketActivityView.signInSuccess(signInResponse.getSignInResult());
            }


            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mBascketActivityView.validateFailure(null);
            }
        });
    }
}
