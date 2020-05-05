package com.example.zigzag.src.bascket;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.order.OrderActivity;
import com.example.zigzag.src.bascket.interfaces.BascketActivityView;
import com.example.zigzag.src.bascket.models.BasketResponse;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BascketActivity extends BaseActivity implements BascketActivityView {

    private RecyclerView mRvBasketList;
    private ArrayList<BasketResponse.BasketResult> mBasketList=new ArrayList<>();
    private BasketAdapter mBasketAdapter;
    private Button mBtnBuy;
    private String mSumPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bascket);

        initView();

        mBtnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("price", mSumPrice);
                startActivity(intent);            }
        });
    }

    void initView(){
        mRvBasketList=findViewById(R.id.bascket_rv_list);
        mBtnBuy=findViewById(R.id.basket_btn_buy);

        mRvBasketList.setLayoutManager(new LinearLayoutManager(this)) ;
        mBasketAdapter=new BasketAdapter(mBasketList);
    }
    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    @Override
    public void getBasketSuccess(boolean isSuccess, int code, String message, ArrayList<BasketResponse.BasketResult> basekesResult) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("아이템 리스트 조회 성공");
                //activity.showCustomToast(message);
                mBasketList.clear();
                mBasketList.addAll(basekesResult);
                mBasketAdapter.notifyDataSetChanged();

                System.out.println("아이템 리스트 조회 결과:"+mBasketList.toString());

                //아이템 총 가격 정하고 설정
                countSumPrice(mBasketList);


            }
        }
    }

    private void countSumPrice(ArrayList<BasketResponse.BasketResult> mBasketList ) {
        int tmpPrice=0;
        for(BasketResponse.BasketResult item:mBasketList){
            String tmp=item.getPrice().replace(",","");
            tmpPrice+=Integer.parseInt(tmp);
        }

        DecimalFormat formatter=new DecimalFormat("###,###");
        mSumPrice=formatter.format(tmpPrice);
        mBtnBuy.setText("총 "+mSumPrice+"원 구매하기");

    }
}

