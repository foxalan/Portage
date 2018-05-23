package com.example.fox_ui.studentmanager.sign;

import com.example.fox_core.net.RestClient;
import com.example.fox_core.net.callback.IError;
import com.example.fox_core.net.callback.IFailure;
import com.example.fox_core.net.callback.ISuccess;

/**
 * @Author Alan
 * Date 2018/5/23 0023
 * Function
 * Issue
 */

public class SignInHandler {

    private ISignInViewListener listener;

    public SignInHandler(ISignInViewListener listener) {
        this.listener = listener;
    }

    public static SignInHandler create(ISignInViewListener listener) {
        return new SignInHandler(listener);
    }

    private int length = 6;

    public void signIn(String stuId, String password) {

        if ("".equals(stuId)) {
            listener.onUserNameNull();
            return;
        }

        if ("".equals(password)) {
            listener.onPasswordNull();
            return;
        }

        if (password.length() <= length) {
            listener.onPasswordNorm();
            return;
        }

        RestClient.builder()
                .setUrl("login")
                .params("username",stuId)
                .params("password",password)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .build()
                .post();
    }

}
