package com.example.zigzag.src.order_completed;




import com.example.zigzag.src.order_completed.interfaces.OrderActivityView;
import com.example.zigzag.src.order_completed.interfaces.OrderRetrofitInterface;
import com.example.zigzag.src.order_completed.models.OrderResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class OrderCompletedService {
    private final OrderActivityView mOrderActivityView;

    OrderCompletedService(final OrderActivityView orderActivityView) {
        this.mOrderActivityView = orderActivityView;
    }


    void getOrderList() {
        final OrderRetrofitInterface orderRetrofitInterface = getRetrofit().create(OrderRetrofitInterface.class);


        orderRetrofitInterface.getOrderList().enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final OrderResponse orderResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (orderResponse == null) {
                    mOrderActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mOrderActivityView.getOrderSuccess(orderResponse.isIs_success(), orderResponse.getCode(), orderResponse.getMessage(),
                         orderResponse.getOrdersResult());
            }


            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                mOrderActivityView.validateFailure(null);
                System.out.println("장바구니 에러 통신: "+t);
            }
        });
    }


}
