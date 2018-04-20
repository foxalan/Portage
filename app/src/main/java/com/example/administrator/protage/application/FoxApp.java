package com.example.administrator.protage.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 用于初始化应用启动时的一些数据
 * Issue
 */

public class FoxApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //百度地图
//        SDKInitializer.initialize(getApplicationContext());
    }
}
