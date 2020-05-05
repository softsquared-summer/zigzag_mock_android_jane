package com.example.zigzag.src.bascket;


import com.example.zigzag.src.bascket.interfaces.BascketActivityView;
import com.example.zigzag.src.bascket.interfaces.BascketRetrofitInterface;
import com.example.zigzag.src.bascket.models.BasketResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class BascketService {
    private final BascketActivityView mBascketActivityView;

    BascketService(final BascketActivityView bascketActivityView) {
        this.mBascketActivityView = bascketActivityView;
    }


    void getBasketList() {
        final BascketRetrofitInterface bascketRetrofitInterface = getRetrofit().create(BascketRetrofitInterface.class);


        bascketRetrofitInterface.getBasketList().enqueue(new Callback<BasketResponse>() {
            @Override
            public void onResponse(Call<BasketResponse> call, Response<BasketResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final BasketResponse basketResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (basketResponse == null) {
                    mBascketActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mBascketActivityView.getBasketSuccess(basketResponse.isIs_success(),basketResponse.getCode(),basketResponse.getMessage(),
                        basketResponse.getBasketsResult().get(0).getNum(),basketResponse.getBasketsResult().get(0).getList());
            }


            @Override
            public void onFailure(Call<BasketResponse> call, Throwable t) {
                mBascketActivityView.validateFailure(null);
                System.out.println("장바구니 에러 통신: "+t);
            }
        });
    }
}
