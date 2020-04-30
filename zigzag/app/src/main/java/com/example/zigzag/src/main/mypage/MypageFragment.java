package com.example.zigzag.src.main.mypage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BascketActivity;
import com.example.zigzag.src.login.LoginActivity;
import com.example.zigzag.src.outer.OuterActivity;


public class MypageFragment extends Fragment implements View.OnClickListener {


    private View mBtnBascket;
    private View mVLogin;

    public MypageFragment() {
        // Required empty public constructor
    }


    public static MypageFragment newInstance(String param1, String param2) {
        MypageFragment fragment = new MypageFragment();
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
        View view= inflater.inflate(R.layout.fragment_mypage, container, false);
        initView(view);
        return view;
    }

    void initView(View view){
        mBtnBascket=view.findViewById(R.id.mypage_ib_top2);
        mVLogin=view.findViewById(R.id.mypage_v_prfile);

        mBtnBascket.setOnClickListener(this);
        mVLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mypage_ib_top2:
                Intent intent = new Intent(getActivity(), BascketActivity.class);
                startActivity(intent);
                break;
            case R.id.mypage_v_prfile:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            default:
                break;

        }
    }
}
