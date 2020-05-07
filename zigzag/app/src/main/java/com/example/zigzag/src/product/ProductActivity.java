package com.example.zigzag.src.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
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

import static com.example.zigzag.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.zigzag.src.ApplicationClass.sSharedPreferences;

public class ProductActivity extends AppCompatActivity  implements ProductActivityView {
    private ItemResponse.ItemResult mProductVo;
    private int mItemNum;
    private TextView mTvReviewNum,mTvStoreName,mTvItemName,mTvPrice,mTvDiscount,mTvItemCode;
    private ImageView mIvFreeShip,mIvZzim,mIvBaketAlarm;
    private ImageView mIvImage;
    private ImageButton mBtnBuy,mBtnEnd;
    private boolean mRunning;

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

        //System.out.println("다이얼로그 종료");

    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("제품 상세 페이지 실행중");

        //장바구니에 담긴 것이 있으면 알람 띄우기
        final String basket = sSharedPreferences.getString("isBasket", null);
        SharedPreferences.Editor editor = sSharedPreferences.edit();

        if(basket!=null){
            System.out.println("장바구니 널 아님");
            new Thread(new Runnable() {
                @Override
                public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(0);

                }

            }).start();

            mIvBaketAlarm.setVisibility(View.INVISIBLE);
            editor.remove("isBasket").commit();

        }


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
        mIvBaketAlarm=findViewById(R.id.product_detail_ib_alarm);

        mIvFreeShip=findViewById(R.id.product_detail_iv_freedelivery);
        mIvZzim=findViewById(R.id.product_detail_iv_zzim);

        mBtnBuy=findViewById(R.id.product_detail_ib_buy);
        mBtnEnd=findViewById(R.id.product_detail_iv_end);



        //다이얼로그


    }

    @Override
    public void validateSuccess(String text) {
    }

    @Override
    public void validateFailure(String message) {

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



    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            mIvBaketAlarm.setVisibility(View.VISIBLE);
        };

    };



}
