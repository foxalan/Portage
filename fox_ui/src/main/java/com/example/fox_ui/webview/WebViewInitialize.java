package com.example.fox_ui.webview;

import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Author Alan
 * Date 2018/4/20 0020
 * Function
 * Issue
 */

public class WebViewInitialize implements IWebViewInit {


    @Override
    public WebSettings initWebSetting(WebView webView) {
        WebSettings webSettings = webView.getSettings();

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            //加载缓存否则网络
        }

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);
            //图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);
            //图片自动缩放 关闭
        }

        //软件解码
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //硬件解码
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 设置支持javascript脚本
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false
        webSettings.setBuiltInZoomControls(true);
        //隐藏缩放工具
        webSettings.setDisplayZoomControls(false);
        // 扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDatabaseEnabled(true);
        //保存密码
        webSettings.setSavePassword(true);
        //是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webSettings.setDomStorageEnabled(true);
        webView.setSaveEnabled(true);
        webView.setKeepScreenOn(true);
        return webSettings;
    }

    @Override
    public WebViewClient initWebViewClient(WebView webView) {
        return null;
    }

    @Override
    public WebChromeClient initWebChromeClient(WebView webView) {
        return null;
    }
}
