package com.example.zigzag.src.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ProductActivity extends BaseActivity {
    BottomSheetDialog bottomSheetDialog;
    Product mProductVo;
    TextView mTvReviewNum,mTvStoreName,mTvItemName,mTvPrice,mTvDiscount,mTvItemCode;
    ImageView mIvFreeShip,mIvZzim;
    ImageButton mBtnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initView();

        setProductDetail();


        System.out.println("제품상세페이지: "+mProductVo.getmItemName());


    }

    public void buyOnClick(View v){
        bottomSheetDialog.show();

    }

    public void setProductDetail() {
        if(mProductVo!=null){
            mTvReviewNum.setText("("+mProductVo.getmCommentNum()+")");
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
        }else{
            showCustomToast("아이템 상세정보가 없습니다.");
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

        bottomSheetDialog = new BottomSheetDialog(ProductActivity.this);
        bottomSheetDialog.setContentView(R.layout.activity_buy_dialog);

        mIvFreeShip=findViewById(R.id.product_detail_iv_freedelivery);
        mIvZzim=findViewById(R.id.product_detail_iv_zzim);

        mBtnBuy=findViewById(R.id.product_detail_ib_buy);

    }
}
