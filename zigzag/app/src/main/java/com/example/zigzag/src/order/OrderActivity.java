package com.example.zigzag.src.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.bascket.models.BasketResponse;
import com.example.zigzag.src.order.interfaces.OrderActivityView;
import com.example.zigzag.src.order.models.OrderItem;
import com.example.zigzag.src.order.models.OrderResponse;
import com.example.zigzag.src.order.models.TotalPayResponse;
import com.example.zigzag.src.order_completed.OrderCompleteActivity;

import java.util.ArrayList;

public class OrderActivity extends BaseActivity implements OrderActivityView {
    private Spinner mSpinner;
    ArrayList<String> mSpinnerList;
    ArrayAdapter<String> mSpinnerAdapter;

    private RecyclerView mRvOrderList;
    private ArrayList<OrderResponse.OrderResult> mOrderList=new ArrayList<>();
    private OrderAdapter mOrderAdapter;

    private ImageButton mBtnAgree1,mBtnAgree2,mBtnAgree3,mBtnPay,mTvIvFindAdress;
    private ImageView mIvSubAgree;
    private boolean mIsAgree1,mIsAgree2,mIsAgree3;
    private TextView mTvListPrice,mTvShipPrice,mTvSumPrice;

    private OrderService orderService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        initView();

        //주문 리스트 api 호출
        orderService.getOrderList();


        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg="";
                if(!mIsAgree2||!mIsAgree3){
                    if(!mIsAgree2){
                        msg="만 14세 이상 결제에 동의해 주세요.";
                    }
                    if(!mIsAgree3){
                        msg="주문 내용 확인 및 결제에 동의해 주세요.";
                    }
                    AgreeDialog bottomSheetDialog = AgreeDialog.getInstance(msg);
                    //bottomSheetDialog .getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                    bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");
                }
                else{
                    //주문 완료시
                    //showCustomToast("주문이 완료되었습니다.");
                    Intent intent=new Intent(getApplicationContext(), OrderCompleteActivity.class);
                    intent.putExtra("listprice",mTvListPrice.getText().toString());
                    intent.putExtra("shipprice",mTvShipPrice.getText().toString());
                    intent.putExtra("sumprice",mTvSumPrice.getText().toString());

                    startActivity(intent);
                }


            }
        });

        //도로명 주소 찾기
        mTvIvFindAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RoadadressActivity.class);
                startActivity(intent);

            }
        });

    }

    private void initView() {
        orderService=new OrderService(this);
        mBtnPay=findViewById(R.id.order_iv_pay);

        mRvOrderList=findViewById(R.id.order_rv_list);
        mTvListPrice=findViewById(R.id.order_tv_price_order);
        mTvShipPrice=findViewById(R.id.order_tv_price_delivery);
        mTvSumPrice=findViewById(R.id.order_tv_price_sum);

        mTvIvFindAdress=findViewById(R.id.order_iv_findaddress);

        mBtnAgree1=findViewById(R.id.order_iv_pay_check);
        mBtnAgree2=findViewById(R.id.order_iv_agree_check1);
        mBtnAgree3=findViewById(R.id.order_iv_agree_check2);
        mIvSubAgree=findViewById(R.id.order_iv_subcheck);
        mIsAgree1=false;mIsAgree2=false;mIsAgree3=false;

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
                getPrice(ordersResult);


            }
        }
    }
    private void getPrice(ArrayList<OrderResponse.OrderResult> ordersResult) {
        ArrayList<String> itemId=new ArrayList<>();

        for(OrderResponse.OrderResult item:ordersResult){
            itemId.add(Integer.toString(item.getOrderItems().getItemID()));
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
                mTvListPrice.setText(payResult.getPrice());
                mTvShipPrice.setText(payResult.getShip());
                mTvSumPrice.setText(payResult.getTotal());



            }
        }
    }


    //약관동의
    public void agreeOnClick(View view) {
        switch (view.getId()){
            case R.id.order_iv_pay_check:
                if(!mIsAgree1){
                    mIsAgree1=true;
                    mBtnAgree1.setImageResource(R.drawable.order_check_yes);
                }else{
                    mIsAgree1=false;
                    mBtnAgree1.setImageResource(R.drawable.order_check_no);
                }

                break;
            case R.id.order_iv_agree_check1:
                if(!mIsAgree2){
                    mIsAgree2=true;
                    mBtnAgree2.setImageResource(R.drawable.order_check_yes);
                }else{
                    mIsAgree2=false;
                    mBtnAgree2.setImageResource(R.drawable.order_check_no);
                }
                break;
            case R.id.order_iv_agree_check2:
                if(!mIsAgree3){
                    mIsAgree3=true;
                    mBtnAgree3.setImageResource(R.drawable.order_check_yes);
                    mIvSubAgree.setImageResource(R.drawable.order_subcheck_yes);

                }else{
                    mIsAgree3=false;
                    mBtnAgree3.setImageResource(R.drawable.order_check_no);
                    mIvSubAgree.setImageResource(R.drawable.order_subcheck_no);
                }
                break;
        }
    }
}
