package com.example.administrator.protage.application;

import android.app.Application;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;
import com.example.fox_core.app.Latte;
import com.example.fox_ec.icons.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 用于初始化应用启动时的一些数据
 * Issue
 */

public class FoxApp extends Application {

    private static final Handler HANDLER = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        //百度地图
        //SDKInitializer.initialize(getApplicationContext());

        Latte.init()
                .withApiHost("本地服务器")
                .withContext(this)
                .withHandler(HANDLER)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();

    }
}
