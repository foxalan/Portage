package com.example.fox_core.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue
 */

public interface IWebViewInit {
    /**
     * 初始化WebView
     * @param webView
     * @return
     */
    WebView initWebView(WebView webView);

    /**
     * 初始化 WebViewClient
     * @return
     */
    WebViewClient initWebViewClient();

    /**
     * 初始化
     * @return
     */
    WebChromeClient initWebChromeClient();
}
