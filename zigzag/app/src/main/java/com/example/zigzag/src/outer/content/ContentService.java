package com.example.zigzag.src.outer.content;


import com.example.zigzag.src.outer.content.interfaces.ContentActivityView;
import com.example.zigzag.src.outer.content.interfaces.ContentRetrofitInterface;
import com.example.zigzag.src.outer.content.models.ItemsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.zigzag.src.ApplicationClass.getRetrofit;


class ContentService {
    private final ContentActivityView mContentActivityView;

    ContentService(final ContentActivityView contentActivityView) {
        this.mContentActivityView = contentActivityView;
    }


    void getItemList() {
        final ContentRetrofitInterface contentRetrofitInterface = getRetrofit().create(ContentRetrofitInterface.class);


        contentRetrofitInterface.getItemList().enqueue(new Callback<ItemsResponse>() {
            @Override
            public void onResponse(Call<ItemsResponse> call, Response<ItemsResponse> response) {

                //서버에서 api통신으로 반환되는 json형태의 response이다.
                final ItemsResponse itemsResponse = response.body();

                //서버에서 주는 값이 없다면, 통신실패
                if (itemsResponse == null) {

                    mContentActivityView.validateFailure(null);
                    return;
                }
                // 통신 성공, api통신으로 반환된 response를 액티비티에 반환해준다.
                mContentActivityView.getItemsSuccess(itemsResponse.isIs_success(),itemsResponse.getCode(),itemsResponse.getMessage(),itemsResponse.getItemsResult());
            }


            @Override
            public void onFailure(Call<ItemsResponse> call, Throwable t) {
                mContentActivityView.validateFailure(null);
                System.out.println(t);
            }
        });
    }
}
