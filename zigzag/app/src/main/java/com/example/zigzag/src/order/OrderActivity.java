package com.example.zigzag.src.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;

import java.util.ArrayList;

public class OrderActivity extends BaseActivity {
    private Spinner mSpinner;
    ArrayList<String> mSpinnerList;
    ArrayAdapter<String> mSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        initView();



    }

    private void initView() {
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
}
