package com.example.zigzag.src.skirt;


import com.example.zigzag.src.pants.interfaces.PantsActivityView;
import com.example.zigzag.src.pants.interfaces.PantsRetrofitInterface;
import com.example.zigzag.src.pants.models.DefaultResponse;
import com.example.zigzag.src.pants.models.SignInBody;
import com.example.zigzag.src.pants.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class SkirtService {
    private final PantsActivityView mPantsActivityView;

    SkirtService(final PantsActivityView pantsActivityView) {
        this.mPantsActivityView = pantsActivityView;
    }

    void getTest() {


        final PantsRetrofitInterface pantsRetrofitInterface = getRetrofit().create(PantsRetrofitInterface.class);


        pantsRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {

                   mPantsActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mPantsActivityView.validateSuccess(defaultResponse.getMessage());
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mPantsActivityView.validateFailure(null);
            }
        });
    }


    void postSignIn(String id, String pw) {
        final PantsRetrofitInterface pantsRetrofitInterface = getRetrofit().create(PantsRetrofitInterface.class);


        pantsRetrofitInterface.signInTest(new SignInBody(id,pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final SignInResponse signInResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (signInResponse == null) {

                    mPantsActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mPantsActivityView.signInSuccess(signInResponse.getSignInResult());
            }


            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mPantsActivityView.validateFailure(null);
            }
        });
    }
}
