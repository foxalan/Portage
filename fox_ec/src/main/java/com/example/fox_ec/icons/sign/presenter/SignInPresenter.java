package com.example.fox_ec.icons.sign.presenter;

import android.util.Patterns;

import com.example.fox_ec.icons.sign.callback.IViewSignCallBack;
import com.example.fox_ec.icons.sign.model.SignInModelControl;

/**
 * @Author Alan
 * Date 2018/5/9 0009
 * Function
 * Issue
 */

public class SignInPresenter implements SignInPresenterCallBack {

    private IViewSignCallBack viewSignCallBack;
    private SignInModelControl modelControl;

    public SignInPresenter(IViewSignCallBack viewSignCallBack) {
        this.viewSignCallBack = viewSignCallBack;
    }


    private int length = 6;

    public void init(String user, String password) {
        if ("".equals(user)) {
            viewSignCallBack.onUserNull();
            return;
        }

        if ("".equals(password)) {
            viewSignCallBack.onPassWordNull();
            return;
        }

        if (!Patterns.PHONE.matcher(user).matches()) {
            viewSignCallBack.onUserFormatError();
            return;
        }

        if (password.length() < length) {
            viewSignCallBack.onPasswordFormatError();
            return;
        }

        modelControl = new SignInModelControl();
        modelControl.withSignIn(user, password, this);
    }

    @Override
    public void onModelCallSuccess() {

    }

    @Override
    public void onModelCallFail() {
        viewSignCallBack.onError();
    }
}
