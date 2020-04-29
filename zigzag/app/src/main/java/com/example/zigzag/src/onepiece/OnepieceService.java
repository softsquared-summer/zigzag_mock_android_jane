package com.example.zigzag.src.onepiece;


import com.example.zigzag.src.onepiece.interfaces.OnepieceActivityView;
import com.example.zigzag.src.onepiece.interfaces.OnepieceRetrofitInterface;
import com.example.zigzag.src.onepiece.models.DefaultResponse;
import com.example.zigzag.src.onepiece.models.SignInBody;
import com.example.zigzag.src.onepiece.models.SignInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class OnepieceService {
    private final OnepieceActivityView mOnepieceActivityView;

    OnepieceService(final OnepieceActivityView onepieceActivityView) {
        this.mOnepieceActivityView = onepieceActivityView;
    }

    void getTest() {


        final OnepieceRetrofitInterface onepieceRetrofitInterface = getRetrofit().create(OnepieceRetrofitInterface.class);


        onepieceRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {


            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {

                   mOnepieceActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mOnepieceActivityView.validateSuccess(defaultResponse.getMessage());
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mOnepieceActivityView.validateFailure(null);
            }
        });
    }


    void postSignIn(String id, String pw) {
        final OnepieceRetrofitInterface onepieceRetrofitInterface = getRetrofit().create(OnepieceRetrofitInterface.class);


        onepieceRetrofitInterface.signInTest(new SignInBody(id,pw)).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final SignInResponse signInResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (signInResponse == null) {

                    mOnepieceActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mOnepieceActivityView.signInSuccess(signInResponse.getSignInResult());
            }


            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                mOnepieceActivityView.validateFailure(null);
            }
        });
    }
}
