package com.example.fox_core.net.callback;

/**
 * @Author Alan
 * Date 2018/5/8 0008
 * Function
 * Issue
 */

public interface IError {

    /**
     * 请求失败
     * @param code
     * @param message
     */
    void onError(int code, String message);
}
