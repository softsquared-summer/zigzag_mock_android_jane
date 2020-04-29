package com.example.zigzag.src.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.main.interfaces.MainActivityView;
import com.example.zigzag.src.main.moa.MoaFragment;
import com.example.zigzag.src.main.models.SignInResponse;
import com.example.zigzag.src.main.mypage.MypageFragment;
import com.example.zigzag.src.main.store.StoreFragment;
import com.example.zigzag.src.main.today.TodayFragment;
import com.example.zigzag.src.main.zzim.ZzimFragment;
import com.example.zigzag.src.onepiece.OnepieceActivity;
import com.example.zigzag.src.outer.OuterActivity;
import com.example.zigzag.src.pants.PantsActivity;
import com.example.zigzag.src.skirt.SkirtActivity;
import com.example.zigzag.src.top.TopActivity;

import androidx.annotation.Nullable;


public class MainActivity extends BaseActivity implements MainActivityView {

                    //전역변수에는 소문자 m을 붙여주고
    private TextView mTvHelloWorld;
    private MainService mainService;
    MoaFragment mMoaFragment;
    TodayFragment mTodayFragment;
    ZzimFragment mZzimFragment;
    StoreFragment mStoreFragment;
    MypageFragment mMypateFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainService= new MainService(this);

        mMoaFragment=new MoaFragment();
        mMypateFragment=new MypageFragment();
        mZzimFragment=new ZzimFragment();
        mStoreFragment=new StoreFragment();
        mTodayFragment=new TodayFragment();

        moveMoa();
    }

    public void moveMoa(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_page, mMoaFragment).commit();
    }
    private void tryGetTest() {
        showProgressDialog();

        //생성자에 this를 넣어준다. : this = MainActivity 자신을 의미한다.
        mainService.getTest();
    }
    private void tryPostSignIn() {
        showProgressDialog();

        //생성자에 this를 넣어준다. : this = MainActivity 자신을 의미한다.
        mainService.postSignIn("testid","testpw");
    }
    @Override
    public void validateSuccess(String text) {
        //통신이 성공되면 다이얼로그를 종료한 후 메시지 설정
        hideProgressDialog();
    }

    @Override
    public void validateFailure(@Nullable String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void signInSuccess(SignInResponse.SignInResult signInResult) {
        hideProgressDialog();
        mTvHelloWorld.setText(signInResult. getJwt());
    }


    public void fragmentOnClick(View view) {
        switch (view.getId()) {
            case R.id.main_ib_menu_today:
               // tryPostSignIn();
                break;
            case R.id.main_ib_menu_store:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_page, mStoreFragment).commit();
                break;
            case R.id.main_ib_menu_moa:
                moveMoa();
                break;
            case R.id.main_ib_menu_zzim:
                System.out.println("찜 클릭");
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_page, mZzimFragment).commit();
                break;
            case R.id.main_ib_menu_mypage:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_page, mMypateFragment).commit();
                break;
            default:
                break;
        }
    }
    public void categoryOnClick(View view) {
        switch (view.getId()) {
            case R.id.moa_iv_category_outer:
                // tryPostSignIn();
                Intent intent = new Intent(this, OuterActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_iv_category_top:
                intent=new Intent(this, TopActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_iv_category_onepiece:
                intent=new Intent(this, OnepieceActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_iv_category_pants:
                intent=new Intent(this, PantsActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_iv_category_skirt:
                intent=new Intent(this, SkirtActivity.class);
                startActivity(intent);
                break;

            default:
                break;

        }
    }
}
