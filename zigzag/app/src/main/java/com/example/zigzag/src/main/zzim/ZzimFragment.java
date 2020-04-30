package com.example.zigzag.src.main.zzim;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BascketActivity;
import com.google.android.material.tabs.TabLayout;


public class ZzimFragment extends Fragment implements View.OnClickListener {


    private ImageButton mBtnBascket;

    public ZzimFragment() {
    }


    public static ZzimFragment newInstance(String param1, String param2) {
        ZzimFragment fragment = new ZzimFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_zzim, container, false);
        initView(view);
        return view;
    }

    void initView(View view){
        mBtnBascket=view.findViewById(R.id.zzim_ib_top2);

        mBtnBascket.setOnClickListener(this);
    }


    @Override
    
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zzim_ib_top2:
                Intent intent = new Intent(getActivity(), BascketActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
}
