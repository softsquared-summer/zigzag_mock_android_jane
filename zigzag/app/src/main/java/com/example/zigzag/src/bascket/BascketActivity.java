package com.example.zigzag.src.bascket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.bascket.interfaces.BascketActivityView;
import com.example.zigzag.src.bascket.models.BasketResponse;

import java.util.ArrayList;

public class BascketActivity extends BaseActivity implements BascketActivityView {

    private RecyclerView mRvBasketList;
    private ArrayList<BasketResponse.BasketResult> mBasketList=new ArrayList<>();
    private BasketAdapter mBasketAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bascket);

        initView();
    }

    void initView(){
        mRvBasketList=findViewById(R.id.bascket_rv_list);

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
            }
        }
    }
}

