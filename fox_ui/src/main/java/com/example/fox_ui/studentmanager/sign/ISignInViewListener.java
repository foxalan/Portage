package com.example.fox_ui.studentmanager.sign;

/**
 * @Author Alan
 * Date 2018/5/23 0023
 * Function
 * Issue
 */

public interface ISignInViewListener {

    /**
     * 登入成功
     */
    void onSignInSuccess();

    /**
     * 登入失败
     */

    void onSignInFail();

    /**
     * 用户名为空
     */
    void onUserNameNull();

    /**
     * 密码为空
     */
    void onPasswordNull();

    /**
     * 密码不规范
     */
    void onPasswordNorm();
}
