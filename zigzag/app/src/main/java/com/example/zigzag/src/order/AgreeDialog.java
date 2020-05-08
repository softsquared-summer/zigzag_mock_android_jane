package com.example.zigzag.src.order;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zigzag.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.Nullable;

import static com.example.zigzag.src.ApplicationClass.sSharedPreferences;

public class AgreeDialog extends BottomSheetDialogFragment  {


    private static String msg1;
    private ImageButton mBtnCheck;
    private TextView mTvMsg;

    public static AgreeDialog getInstance(String msg) {
        AgreeDialog bottomSheetDialog = new AgreeDialog();
        msg1=msg;

        return bottomSheetDialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_agree_dialog, container,false);



        출처: https://supark7.tistory.com/entry/Custom-Dialog [배우는 즐거움]
        System.out.println("구매 다이얼로그 생성");

        initView(view);

        mTvMsg.setText(msg1);

        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
        return view;
    }

    void initView(View view){

        mTvMsg=view.findViewById(R.id.agree_msg);
        mBtnCheck=view.findViewById(R.id.agree_btn);
    }






}
