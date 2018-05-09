package com.example.fox_ec.icons.sign.presenter;

import com.example.fox_ec.icons.sign.callback.IViewSignCallBack;

/**
 * @Author Alan
 * Date 2018/5/9 0009
 * Function
 * Issue
 */

public class SignInPresenter {

    private IViewSignCallBack viewSignCallBack;

    public SignInPresenter(IViewSignCallBack viewSignCallBack){
        this.viewSignCallBack = viewSignCallBack;
    }


    public void init(String user,String password){
        if ("".equals(user)){
            viewSignCallBack.onUserNull();
            return;
        }

        if ("".equals(password)){
            viewSignCallBack.onPassWordNull();
            return;
        }


    }

}
