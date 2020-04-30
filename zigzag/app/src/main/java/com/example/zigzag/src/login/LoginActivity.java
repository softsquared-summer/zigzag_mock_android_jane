package com.example.zigzag.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.zigzag.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEtId,mEtPw;

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

        mEtId.setOnClickListener(this);
        mEtPw.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_et_email:
                break;
            case R.id.login_et_pw:

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
}
