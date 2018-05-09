package com.example.fox_ec.icons.sign;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_ec.R;
import com.example.fox_ec.icons.sign.callback.IViewSignCallBack;

/**
 * @Author Alan
 * Date 2018/5/9 0009
 * Function
 * Issue
 */

public class SignInDelegate extends LatteDelegate implements IViewSignCallBack{

    TextInputEditText mUser;
    TextInputEditText mPassWord;
    AppCompatButton mBtnSignIn;

    @Override
    public Object getLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        mUser = rootView.findViewById(R.id.edit_sign_in_email);
        mPassWord = rootView.findViewById(R.id.edit_sign_in_password);
        mBtnSignIn = rootView.findViewById(R.id.btn_sign_in);

        initEvent();
    }

    private void initEvent() {
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onUserNull() {

    }

    @Override
    public void onPassWordNull() {

    }

    @Override
    public void onUserFormatError() {

    }

    @Override
    public void onPasswrodFormatError() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }
}
