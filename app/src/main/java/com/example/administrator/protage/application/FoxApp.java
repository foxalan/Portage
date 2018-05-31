package com.example.administrator.protage.application;

import android.app.Application;
import android.os.Handler;

import com.baidu.mapapi.SDKInitializer;
import com.example.administrator.protage.R;
import com.example.fox_core.app.Latte;
import com.example.fox_core.net.interceptors.DebugInterceptor;
import com.example.fox_ec.icons.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 用于初始化应用启动时的一些数据
 * Issue 1.Loader不是全部理解
 *       2.Fragment没有完善
 *       3.网络请求没有完善
 *       4.用户登入，注册模块
 *       5.webView不是很完善
 *       6.分类点击
 *
 *       
 */

public class FoxApp extends Application {

    private static final Handler HANDLER = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        //百度地图
        //SDKInitializer.initialize(getApplicationContext());

        Latte.init()
                .withApiHost("")
                .withContext(this)
                .withHandler(HANDLER)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

    }
}
