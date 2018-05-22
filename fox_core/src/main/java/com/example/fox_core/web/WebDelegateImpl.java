package com.example.fox_core.web;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.fox_core.fragment.LatteDelegate;
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

public class WebDelegateImpl extends WebDelegate {

    public static WebDelegateImpl webViewDelegate;

    private LatteDelegate mTopDelegate = null;

    public static WebDelegateImpl create(String mUrl){
        if (webViewDelegate == null){

            webViewDelegate = new WebDelegateImpl();
        }
        Bundle bundle = new Bundle();
        bundle.putString(RouteKey.URL.name(),mUrl);
        webViewDelegate.setArguments(bundle);
        return webViewDelegate;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

    public void setTopDelegate(LatteDelegate delegate) {
        mTopDelegate = delegate;
    }

    public LatteDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }
}
