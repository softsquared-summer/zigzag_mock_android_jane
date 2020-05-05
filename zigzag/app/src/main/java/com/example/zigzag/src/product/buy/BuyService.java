package com.example.zigzag.src.product.buy;


import com.example.zigzag.src.product.buy.interfaces.BuyActivityView;
import com.example.zigzag.src.product.buy.interfaces.BuyRetrofitInterface;
import com.example.zigzag.src.product.buy.models.BasketBody;
import com.example.zigzag.src.product.buy.models.BasketResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class BuyService {
    private final BuyActivityView mBuyActivityView;

    BuyService(final BuyActivityView buyActivityView) {
        this.mBuyActivityView = buyActivityView;
    }



    void postBasket(int item_id,String color, String size,int num) {
        final BuyRetrofitInterface buyRetrofitInterface = getRetrofit().create(BuyRetrofitInterface.class);


        buyRetrofitInterface.postBasket(new BasketBody(item_id,color,size,num)).enqueue(new Callback<BasketResponse>() {
            @Override
            public void onResponse(Call<BasketResponse> call, Response<BasketResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final BasketResponse basketResponse = response.body();

                System.out.println("장바구니 메소드 진입");
                //서버에서 주는 값이 없다면, 통신실패
                if (basketResponse == null) {
                    mBuyActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mBuyActivityView.basketSuccess(basketResponse.isIs_success(), basketResponse.getCode(),basketResponse.getMessage());
            }

            @Override
            public void onFailure(Call<BasketResponse> call, Throwable t) {
                mBuyActivityView.validateFailure(null);
                System.out.println("장바구니 담기 실패: "+t);
            }
        });
    }
}
