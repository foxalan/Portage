package com.example.fox_ui.studentmanager.sign.out;

/**
 * @Author Alan
 * Date 2018/5/23 0023
 * Function
 * Issue
 */

public interface ISignOutViewListener {

    /**
     * 用户名已经被注册了
     */
    void onFailStuIdRepeat();

    /**
     * 信息不完整
     */
    void onCompleteError();

    /**
     * 两次密码不一样
     */
    void onPasswordDis();

    /**
     * 注册成功
     */

    void onSignOutSuccess();
}
