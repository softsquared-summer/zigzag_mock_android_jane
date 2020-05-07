package com.example.zigzag.src.order;


import com.example.zigzag.src.order.interfaces.OrderActivityView;
import com.example.zigzag.src.order.interfaces.OrderRetrofitInterface;
import com.example.zigzag.src.order.models.OrderResponse;
import com.example.zigzag.src.order.models.TotalPayResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class OrderService {
    private final OrderActivityView mOrderActivityView;

    OrderService(final OrderActivityView orderActivityView) {
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
                         orderResponse.getOrdersResult().getOrderItems());
            }


            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                mOrderActivityView.validateFailure(null);
                System.out.println("장바구니 에러 통신: "+t);
            }
        });
    }

    void getTotalPay(ArrayList<String> itemIdList) {
        String[] ids=new String[5];

        int i=0;
        for(String item:itemIdList){
            ids[i]=item;
            i++;
        }

        final OrderRetrofitInterface orderRetrofitInterface = getRetrofit().create(OrderRetrofitInterface.class);

        orderRetrofitInterface.getTotalPay(ids[0],ids[1],ids[2],ids[3],ids[4]).enqueue(new Callback<TotalPayResponse>() {
            @Override
            public void onResponse(Call<TotalPayResponse> call, Response<TotalPayResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final TotalPayResponse totalResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (totalResponse == null) {
                    mOrderActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mOrderActivityView.getTotalPaySuccess(totalResponse.isIs_success(), totalResponse.getCode(), totalResponse.getMessage(),
                        totalResponse.getTotalPayResult());
            }


            @Override
            public void onFailure(Call<TotalPayResponse> call, Throwable t) {
                mOrderActivityView.validateFailure(null);
                System.out.println("총액 계산 에러 통신: "+t);
            }
        });
    }
}
