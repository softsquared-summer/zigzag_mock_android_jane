package com.example.zigzag.src.product.buy;

import androidx.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.product.buy.interfaces.BuyActivityView;
import com.example.zigzag.src.product.models.ItemResponse;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BuyDialog extends BottomSheetDialogFragment implements BuyActivityView {

    private TextView mTvPrice;
    private ItemResponse.ItemResult mProductVO;
    private ImageButton mBtnBascket;
    private BuyService buyService;

    public static BuyDialog getInstance() {
        BuyDialog bottomSheetDialog = new BuyDialog();
        return bottomSheetDialog;
    }


    public void setmProductVO(ItemResponse.ItemResult productVO){
        mProductVO=productVO;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_buy_dialog, container,false);

        System.out.println("구매 다이얼로그 생성");

        initView(view);

        setDialog();

        mBtnBascket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBascket()
            }
        });
        return view;
    }

    void initView(View view){
        buyService=new BuyService(this);
        mTvPrice=view.findViewById(R.id.buy_tv_price);
        mBtnBascket=view.findViewById(R.id.buy_ib_bascket);
    }

    void setDialog(){
        mTvPrice.setText(mProductVO.getPrice()+"원");
    }
}
