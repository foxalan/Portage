package com.example.fox_core.net.callback;

/**
 * @Author Alan
 * Date 2018/5/8 0008
 * Function
 * Issue
 */

public interface IRequest {

    /**
     * 请求开始时的操作
     */
    void onRequestStart();


    /**
     * 请求结束时的操作
     */
    void onRequestEnd();
}
