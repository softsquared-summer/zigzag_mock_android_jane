package com.example.zigzag.src.order_completed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.main.MainActivity;
import com.example.zigzag.src.order_completed.interfaces.OrderActivityView;
import com.example.zigzag.src.order_completed.models.OrderResponse;

import java.util.ArrayList;

public class OrderCompleteActivity extends AppCompatActivity implements OrderActivityView {
    private RecyclerView mRvOrderList;
    private ArrayList<OrderResponse.OrderResult> mOrderList=new ArrayList<>();
    private OrderCompletedAdapter mOrderAdapter;
    private OrderCompletedService orderService;
    private TextView mTvListPrice,mTvShipPrice,mTvSumPrice,mTvprice;
    private ImageButton mBtnEnd,mBtnGoSop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);


        initView();

        //주문 리스트 api 호출
        orderService.getOrderList();

        //인텐트로 받아서 가격 설정
        String tmp=getIntent().getStringExtra("listprice");
        mTvListPrice.setText(tmp);
        mTvprice.setText(tmp);
        tmp=getIntent().getStringExtra("shipprice");
        mTvShipPrice.setText(tmp);
        tmp=getIntent().getStringExtra("sumprice");
        mTvSumPrice.setText(tmp);


        mBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBtnGoSop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void initView() {
        orderService=new OrderCompletedService(this);
        mRvOrderList=findViewById(R.id.ordercomp_rv_list);
        mTvListPrice=findViewById(R.id.ordercomp_price_list);
        mTvShipPrice=findViewById(R.id.ordercomp_price_ship);
        mTvSumPrice=findViewById(R.id.ordercomp_price_sum);
        mBtnEnd=findViewById(R.id.ordercomp_iv_end);
        mBtnGoSop=findViewById(R.id.ordercomp_iv_goshop);

        mTvprice=findViewById(R.id.order_tv_price_order_title);

        mRvOrderList.addItemDecoration(new DividerItemDecoration(this, 1));
        mRvOrderList.setLayoutManager(new LinearLayoutManager(this)) ;
        mOrderAdapter=new OrderCompletedAdapter(mOrderList,getApplicationContext());
        mRvOrderList.setAdapter(mOrderAdapter);

    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getOrderSuccess(boolean isSuccess, int code, String message, ArrayList<OrderResponse.OrderResult> ordersResult) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("주문목록 아이템 리스트 조회 성공");
                //activity.showCustomToast(message);
                mOrderList.clear();
                mOrderList.addAll(ordersResult);
                mOrderAdapter.notifyDataSetChanged();

                System.out.println("주문목록 아이템 리스트 조회 결과:"+mOrderList.toString());

                //아이템 총액 계산 시작


            }
        }
    }
}
