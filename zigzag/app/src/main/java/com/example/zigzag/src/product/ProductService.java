package com.example.zigzag.src.product;


import com.example.zigzag.src.product.interfaces.ProductActivityView;
import com.example.zigzag.src.product.interfaces.ProductRetrofitInterface;
import com.example.zigzag.src.product.models.DefaultResponse;
import com.example.zigzag.src.product.models.LoginBody;
import com.example.zigzag.src.product.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class ProductService {
    private final ProductActivityView mProductActivityView;

    ProductService(final ProductActivityView productActivityView) {
        this.mProductActivityView = productActivityView;
    }

    void getTest() {


        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);
        productRetrofitInterface.getTest().enqueue(new Callback<DefaultResponse>() {

            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

               //서버에서 api통신으로 반환되는 json형태의 response이다.
                final DefaultResponse defaultResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
               if (defaultResponse == null) {
                   mProductActivityView.validateFailure(null);
                    return;
                }

                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mProductActivityView.validateSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mProductActivityView.validateFailure(null);
            }
        });
    }


    void postLogin(String email, String password) {
        final ProductRetrofitInterface productRetrofitInterface = getRetrofit().create(ProductRetrofitInterface.class);


        productRetrofitInterface.Login(new LoginBody(email,password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final LoginResponse loginResponse = response.body();

                System.out.println("로그인 서비스 메소드 진입");
                //서버에서 주는 값이 없다면, 통신실패
                if (loginResponse == null) {
                    mProductActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mProductActivityView.LoginSuccess(loginResponse.getIs_success(),loginResponse.getCode(),
                        loginResponse.getLoginResult());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mProductActivityView.validateFailure(null);
                System.out.println("실패: "+t);
            }
        });
    }
}
