package com.example.zigzag.src.main.mypage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BascketActivity;
import com.example.zigzag.src.login.LoginActivity;
import com.example.zigzag.src.outer.OuterActivity;

import static com.example.zigzag.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.zigzag.src.ApplicationClass.sSharedPreferences;


public class MypageFragment extends Fragment implements View.OnClickListener {


    private View mBtnBascket;
    private View mVLogin;
    private boolean mFirstLogin;
    private TextView mTvUser,mTvEmail,mVLogout;

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
        mFirstLogin=false;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("마이페이지 진입");
        View view= inflater.inflate(R.layout.fragment_mypage, container, false);
        initView(view);

        isLogin();
        return view;
    }

    private void isLogin() {
         String jwt = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
         if(jwt!=null) {
             System.out.println("로그인 확인 : jwt존재");
             mTvUser.setText("사용자님 안녕하세요!");
             final String email = sSharedPreferences.getString("id", null);
             mTvEmail.setText(email);
             mVLogin.setClickable(false);
             mVLogout.setVisibility(View.VISIBLE);

         }else{
             System.out.println("로그인 확인 : jwt없음");
             mTvUser.setText("지그재그 로그인 및 회원가입 >");
             mTvEmail.setText("지그재그 ID로 한 번에 결제하세요.");
             mVLogout.setText("");

         }

    }



    void initView(View view){
        mBtnBascket=view.findViewById(R.id.mypage_ib_top2);
        mVLogin=view.findViewById(R.id.mypage_v_prfile);
        mTvUser=view.findViewById(R.id.mypage_tv_name);
        mTvEmail=view.findViewById(R.id.mypage_tv_email);
        mVLogout=view.findViewById(R.id.mypage_tv_logout);

        mBtnBascket.setOnClickListener(this);
        mVLogin.setOnClickListener(this);
        mVLogout.setOnClickListener(this);

    }
    private void refresh(){
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
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
                break;
            case R.id.mypage_tv_logout:
                System.out.println("로그아웃 클릭");
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.remove(X_ACCESS_TOKEN).commit();
                refresh();
                break;
            default:
                break;

        }
    }
}
