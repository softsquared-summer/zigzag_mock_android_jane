package com.example.zigzag.src.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.product.buy.BuyDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ProductActivity extends AppCompatActivity  {
    BottomSheetDialog bottomSheetDialog;
    Product mProductVo;
    TextView mTvReviewNum,mTvStoreName,mTvItemName,mTvPrice,mTvDiscount,mTvItemCode;
    ImageView mIvFreeShip,mIvZzim;
    ImageButton mBtnBuy;

    TextView mTvDialogPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initView();

        setProductDetail();


        System.out.println("제품상세페이지: "+mProductVo.getmItemName());


    }

    public void buyOnClick(View v){
        BuyDialog bottomSheetDialog = BuyDialog.getInstance();
        bottomSheetDialog.setmProductVO(mProductVo);
        bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");


    }

    public void setProductDetail() {
        if(mProductVo!=null){
            mTvPrice.setText(mProductVo.getmPrice()+"원");
            mTvItemName.setText(mProductVo.getmItemName());
            mTvStoreName.setText(mProductVo.getmMallName());
            mTvDiscount.setText(mProductVo.getmDiscount()+" 할인");
            mTvItemCode.setText(Integer.toString(mProductVo.getMitemId()));

            if(mProductVo.getIsFreeShip().equals("Y")){
                mIvFreeShip.setVisibility(View.VISIBLE);
            }else{
                mIvFreeShip.setVisibility(View.INVISIBLE);
            }

            if(mProductVo.getIsHeart().equals("Y")){
                mIvZzim.setImageResource(R.drawable.product_zzim_yes);
            }else{
                mIvZzim.setImageResource(R.drawable.product_zzim_no);
            }


            //다이얼로그

        }else{
            //showCustomToast("아이템 상세정보가 없습니다.");
            finish();
        }
    }

    private void initView(){
        mProductVo=(Product)getIntent().getParcelableExtra("product");

        mTvReviewNum=findViewById(R.id.product_detail_tv_review);
        mTvDiscount=findViewById(R.id.product_detail_tv_discount);
        mTvItemCode=findViewById(R.id.product_detail_tv_code);
        mTvStoreName=findViewById(R.id.product_detail_tv_storename);
        mTvItemName=findViewById(R.id.product_detail_name);
        mTvPrice=findViewById(R.id.product_detail_tv_price);

        mIvFreeShip=findViewById(R.id.product_detail_iv_freedelivery);
        mIvZzim=findViewById(R.id.product_detail_iv_zzim);

        mBtnBuy=findViewById(R.id.product_detail_ib_buy);



        //다이얼로그


    }
}
