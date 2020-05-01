package com.example.zigzag.src.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.zigzag.R;

public class ProductActivity extends AppCompatActivity {

    Product mProductVo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mProductVo=(Product)getIntent().getParcelableExtra("product");

        System.out.println("제품상세페이지: "+mProductVo.getmItemName());
    }
}
