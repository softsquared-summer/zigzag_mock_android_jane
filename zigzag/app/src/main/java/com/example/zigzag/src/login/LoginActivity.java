package com.example.zigzag.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.zigzag.R;
import com.example.zigzag.src.BaseActivity;
import com.example.zigzag.src.login.interfaces.LoginActivityView;
import com.example.zigzag.src.login.models.LoginResponse;
import com.example.zigzag.src.main.mypage.MypageFragment;

import static com.example.zigzag.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.zigzag.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginActivityView {

    EditText mEtId,mEtPw;
    ImageButton mBtnLogin;
    private LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //키보드 자동으로 올라오게 하기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        initView();

    }

    void initView(){
        mEtId=findViewById(R.id.login_et_email);
        mEtPw=findViewById(R.id.login_et_pw);
        mBtnLogin=findViewById(R.id.login_ib_login);
        loginService= new LoginService(this);


        mEtId.setOnClickListener(this);
        mEtPw.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);

    }

    private void tryPostLogin() {
        showProgressDialog();

        String email=mEtId.getText().toString();
        String pw=mEtPw.getText().toString();


        //생성자에 this를 넣어준다. : this = MainActivity 자신을 의미한다.
        loginService.postLogin(email,pw);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_et_email:
                break;
            case R.id.login_et_pw:

            case R.id.login_ib_login:
                tryPostLogin();

                break;
            default:
                break;


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //키보드 숨기기
        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

    }

    @Override
    public void validateSuccess(String text) {
        hideProgressDialog();

    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void LoginSuccess(boolean isSuccess, int code, String loginResult) {
        hideProgressDialog();

        if(isSuccess){
            if(code==100){
                SharedPreferences.Editor editor = sSharedPreferences.edit();
                editor.putString(X_ACCESS_TOKEN,loginResult);
                editor.commit();


                finish();
            }
        }

    }


}
