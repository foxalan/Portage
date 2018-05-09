package com.example.fox_ec.icons.sign.callback;

/**
 * @Author Alan
 * Date 2018/5/9 0009
 * Function
 * Issue
 */

public interface IViewSignCallBack {

    /**
     *   用户名为空
     */
    void onUserNull();

    /**
     *   密码为空
     */
    void onPassWordNull();

    /**
     * 用户名格式不正确
     */
    void onUserFormatError();

    /**
     * 密码格式不正确
     */
    void onPasswrodFormatError();


    /**
     * 用户名或密码错误
     */
    void onError();

    /**
     * 登入成功
     */
    void onSuccess();

}
