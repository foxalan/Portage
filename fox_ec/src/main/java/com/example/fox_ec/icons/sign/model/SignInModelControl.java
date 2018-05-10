package com.example.fox_ec.icons.sign.model;

import com.example.fox_core.net.RestClient;
import com.example.fox_core.net.callback.IFailure;
import com.example.fox_core.net.callback.ISuccess;
import com.example.fox_ec.icons.sign.presenter.SignInPresenterCallBack;

/**
 * @Author Alan
 * Date 2018/5/10 0010
 * Function
 * Issue
 */

public class SignInModelControl {

    public SignInModelControl(){

    }

    public void withSignIn(String user, String password, final SignInPresenterCallBack presenterCallBack){
        RestClient.builder()
                .setUrl("http://192.168.56.1:8080/RestDataServer/api/user_profile.php")
                .params("email", user)
                .params("password", password)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        presenterCallBack.onModelCallFail();
                    }
                })
                .build()
                .post();

    }
}
