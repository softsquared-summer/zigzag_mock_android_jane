package com.example.zigzag.src.main.today;


import com.example.zigzag.src.main.interfaces.MainActivityView;
import com.example.zigzag.src.main.interfaces.MainRetrofitInterface;
import com.example.zigzag.src.main.models.DefaultResponse;
import com.example.zigzag.src.main.today.interfaces.TodayActivityView;
import com.example.zigzag.src.main.today.interfaces.TodayRetrofitInterface;
import com.example.zigzag.src.main.today.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class TodayService {
    private final TodayActivityView mTodaynActivityView;

    TodayService(final TodayActivityView todayActivityView) {
        this.mTodaynActivityView = todayActivityView;
    }



    void getItemList() {
        final TodayRetrofitInterface todayRetrofitInterface = getRetrofit().create(TodayRetrofitInterface.class);


        todayRetrofitInterface.getItemsList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final ItemsResponse itemsResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (itemsResponse == null) {

                    mTodaynActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mTodaynActivityView.getItemsSuccess(itemsResponse.isIs_success(),itemsResponse.getCode(),itemsResponse.getMessage(),itemsResponse.getItemsResult());
            }


            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mTodaynActivityView.validateFailure(null);
                System.out.println(t);
            }
        });
    }
}
