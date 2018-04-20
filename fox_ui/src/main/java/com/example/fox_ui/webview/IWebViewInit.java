package com.example.fox_ui.webview;

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

public interface IWebViewInit {
    /**
     * 初始化WebSetting
     * @param webView
     * @return
     */
    WebSettings initWebSetting(WebView webView);

    /**
     * 设置WebViewClient实现交互
     * @param webView
     * @return
     */
    WebViewClient initWebViewClient(WebView webView);

    /**
     * 设置webChromeClient
     * @param webView
     * @return
     */
    WebChromeClient initWebChromeClient(WebView webView);
}
