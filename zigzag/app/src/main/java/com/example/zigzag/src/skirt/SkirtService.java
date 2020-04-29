package com.example.zigzag.src.skirt;


import com.example.zigzag.src.skirt.interfaces.SkirtActivityView;
import com.example.zigzag.src.skirt.interfaces.SkirtRetrofitInterface;
import com.example.zigzag.src.skirt.models.DefaultResponse;
import com.example.zigzag.src.skirt.models.SignInBody;
import com.example.zigzag.src.skirt.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class SkirtService {
    private final SkirtActivityView mSkirtActivityView;

    SkirtService(final SkirtActivityView skirtActivityView) {
        this.mSkirtActivityView = skirtActivityView;
    }

    void getTest() {


        final SkirtRetrofitInterface skirtRetrofitInterface = getRetrofit().create(SkirtRetrofitInterface.class);


        skirtRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {

                   mSkirtActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mSkirtActivityView.validateSuccess(defaultResponse.getMessage());
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mSkirtActivityView.validateFailure(null);
            }
        });
    }


    void postSignIn(String id, String pw) {
        final SkirtRetrofitInterface skirtRetrofitInterface = getRetrofit().create(SkirtRetrofitInterface.class);


        skirtRetrofitInterface.signInTest(new SignInBody(id,pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final SignInResponse signInResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (signInResponse == null) {

                    mSkirtActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mSkirtActivityView.signInSuccess(signInResponse.getSignInResult());
            }


            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mSkirtActivityView.validateFailure(null);
            }
        });
    }
}
