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
import com.example.zigzag.src.product.interfaces.ProductActivityView;
import com.example.zigzag.src.product.models.ItemResponse;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ProductActivity extends BaseActivity  implements ProductActivityView {
    BottomSheetDialog bottomSheetDialog;
    private ItemResponse.ItemResult mProductVo;
    private int mItemNum;
    private TextView mTvReviewNum,mTvStoreName,mTvItemName,mTvPrice,mTvDiscount,mTvItemCode;
    private ImageView mIvFreeShip,mIvZzim;
    private ImageView mIvImage;
    private ImageButton mBtnBuy,mBtnEnd;

    TextView mTvDialogPrice;
    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initView();

        setProductDetail();


        //System.out.println("제품상세페이지: "+mProductVo.getmItemName());

        mBtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void buyOnClick(View v){
        BuyDialog bottomSheetDialog = BuyDialog.getInstance();
        bottomSheetDialog.setmProductVO(mProductVo);
        bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");


    }

    public void getItemDetail(int number){
        productService.getItemDetail(number);
    }

    public void setProductDetail() {
        if(mItemNum!=0){

            //아이템 상세조회 api 통신 시작
            getItemDetail(mItemNum);

            //다이얼로그

        }else{
            //showCustomToast("아이템 상세정보가 없습니다.");
            finish();
        }
    }

    private void initView(){
        mItemNum=getIntent().getIntExtra("item_id",0);
        System.out.println("아이템 아이디: "+mItemNum);
        productService=new ProductService(this);

        mTvReviewNum=findViewById(R.id.product_detail_tv_review);
        mTvDiscount=findViewById(R.id.product_detail_tv_discount);
        mTvItemCode=findViewById(R.id.product_detail_tv_code);
        mTvStoreName=findViewById(R.id.product_detail_tv_storename);
        mTvItemName=findViewById(R.id.product_detail_name);
        mTvPrice=findViewById(R.id.product_detail_tv_price);
        mIvImage=findViewById(R.id.product_detail_iv_image);

        mIvFreeShip=findViewById(R.id.product_detail_iv_freedelivery);
        mIvZzim=findViewById(R.id.product_detail_iv_zzim);

        mBtnBuy=findViewById(R.id.product_detail_ib_buy);
        mBtnEnd=findViewById(R.id.product_detail_iv_end);



        //다이얼로그


    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);

    }

    @Override
    public void getItemDetailSuccess(boolean isSuccess, int code, String message, ItemResponse.ItemResult itemResponse) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("아이템 리스트 조회 성공");


                mTvPrice.setText(itemResponse.getPrice()+"원");
                mTvItemName.setText(itemResponse.getItem_name());
                mTvStoreName.setText(itemResponse.getMall_name());
                mTvDiscount.setText(itemResponse.getDiscount()+" 할인");
                mTvItemCode.setText(Integer.toString(itemResponse.getItem_id()));
                mTvReviewNum.setText(Integer.toString(itemResponse.getComment_num()));

               //이미지
               mIvImage.setImageResource(R.drawable.default_image);

                mProductVo=itemResponse;

                if(itemResponse.getIs_heart().equals("Y")){
                    mIvZzim.setVisibility(View.VISIBLE);
                }else{
                    mIvZzim.setVisibility(View.INVISIBLE);
                }

//                if(mProductVo.getIsHeart().equals("Y")){
//                    mIvZzim.setImageResource(R.drawable.product_zzim_yes);
//                }else{
//                    mIvZzim.setImageResource(R.drawable.product_zzim_no);
//                }
            }
        }
    }
}
