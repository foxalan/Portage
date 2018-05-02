package com.example.fox_core.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.fox_core.web.chromeclient.WebViewChromeClientImpl;
import com.example.fox_core.web.client.WebViewClientImpl;
import com.example.fox_core.web.rount.RouteKey;
import com.example.fox_core.web.rount.Router;

/**
 * @Author Alan
 * Date 2018/5/2 0002
 * Function
 * Issue
 */

public class WebViewDelegateImpl extends WebDelegate {

    public static WebViewDelegateImpl webViewDelegate;


    public static WebViewDelegateImpl create(String mUrl){
        if (webViewDelegate == null){

            webViewDelegate = new WebViewDelegateImpl();
        }
        Bundle bundle = new Bundle();
        bundle.putString(RouteKey.URL.name(),mUrl);
        webViewDelegate.setArguments(bundle);
        return webViewDelegate;
    }


    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewImpl().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {



        return new WebViewClientImpl(this);
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebViewChromeClientImpl();
    }

    @Override
    public Object getLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(View rootView) throws Exception {
        if( getUrl() !=null){
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public IWebViewInitializer getWebViewInit() {
        return this;
    }
}
