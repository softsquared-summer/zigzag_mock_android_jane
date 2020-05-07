package com.example.zigzag.src.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.bascket.models.BasketResponse;
import com.example.zigzag.src.order.interfaces.OrderActivityView;
import com.example.zigzag.src.order.models.OrderItem;
import com.example.zigzag.src.order.models.TotalPayResponse;

import java.util.ArrayList;

public class OrderActivity extends BaseActivity implements OrderActivityView {
    private Spinner mSpinner;
    ArrayList<String> mSpinnerList;
    ArrayAdapter<String> mSpinnerAdapter;

    private RecyclerView mRvOrderList;
    private ArrayList<OrderItem> mOrderList=new ArrayList<>();
    private OrderAdapter mOrderAdapter;

    private TextView mTvListPrice,mTvShipPrice,mTvSumPrice;

    private OrderService orderService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        initView();

        //주문 리스트 api 호출
        orderService.getOrderList();


    }

    private void initView() {
        orderService=new OrderService(this);

        mRvOrderList=findViewById(R.id.order_rv_list);
        mTvListPrice=findViewById(R.id.order_tv_price_order);
        mTvShipPrice=findViewById(R.id.order_tv_price_delivery);
        mTvSumPrice=findViewById(R.id.order_tv_price_sum);


        mRvOrderList.addItemDecoration(new DividerItemDecoration(this, 1));
        mRvOrderList.setLayoutManager(new LinearLayoutManager(this)) ;
        mOrderAdapter=new OrderAdapter(mOrderList,getApplicationContext());
        mRvOrderList.setAdapter(mOrderAdapter);

        //스피너
        mSpinnerList = new ArrayList<>();
        mSpinnerList.add("배송메모를 선택해주세요.");
        mSpinnerList.add("배송 전에 미리 연락 바랍니다.");
        mSpinnerList.add("집 앞에 놔주세요.");
        mSpinnerList.add("경비실에 맡겨주세요.");
        mSpinnerList.add("택배함에 놔주세요.");
        mSpinnerList.add("부재 시 핸드폰으로 연락주세요.");
        mSpinnerList.add("부재 시 집 앞에 놔주세요.");

        mSpinnerAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                mSpinnerList);
        mSpinner = (Spinner)findViewById(R.id.order_sp_delivery_memo);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });




    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    @Override
    public void getOrderSuccess(boolean isSuccess, int code, String message, ArrayList<OrderItem> ordersResult) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("주문목록 아이템 리스트 조회 성공");
                //activity.showCustomToast(message);
                mOrderList.clear();
                mOrderList.addAll(ordersResult);
                mOrderAdapter.notifyDataSetChanged();

                System.out.println("주문목록 아이템 리스트 조회 결과:"+mOrderList.toString());

                //아이템 총액 계산 시작
                getPrice(ordersResult);


            }
        }
    }
    private void getPrice(ArrayList<OrderItem> ordersResult) {
        ArrayList<String> itemId=new ArrayList<>();

        for(OrderItem item:ordersResult){
            itemId.add(Integer.toString(item.getItemID()));
        }

        //총액 계산 api 호출
        orderService.getTotalPay(itemId);
    }
    @Override
    public void getTotalPaySuccess(boolean isSuccess, int code, String message, TotalPayResponse.TotalPayResult payResult) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("총 가격 조회 성공");
                //activity.showCustomToast(message);

                //아이템 총 가격 정하고 설정
                mTvListPrice.setText(payResult.getPrice()+"원");
                mTvShipPrice.setText(payResult.getShip()+"원");
                mTvSumPrice.setText(payResult.getTotal()+"원");



            }
        }
    }


}
