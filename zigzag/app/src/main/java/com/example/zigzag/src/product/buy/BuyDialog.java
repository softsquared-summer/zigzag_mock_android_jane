package com.example.zigzag.src.product.buy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.product.Product;
import com.example.zigzag.src.product.ProductActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BuyDialog extends BottomSheetDialogFragment {

    TextView mTvPrice;
    Product mProductVO;

    public static BuyDialog getInstance() {
        BuyDialog bottomSheetDialog = new BuyDialog();
        return bottomSheetDialog;
    }


    public void setmProductVO(Product productVO){
        mProductVO=productVO;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_buy_dialog, container,false);

        System.out.println("구매 다이얼로그 생성");

        initView(view);

        setDialog();
        return view;
    }

    void initView(View view){
        mTvPrice=view.findViewById(R.id.buy_tv_price);
    }

    void setDialog(){
        mTvPrice.setText(mProductVO.getmPrice()+"원");
    }
}
