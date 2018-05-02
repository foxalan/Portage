package com.example.fox_core.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_core.web.rount.RouteKey;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue 1.ReferenceQueue WeakReference
 */

public abstract class WebDelegate extends LatteDelegate implements IWebViewInitializer {

    private WebView mWebView = null;
    private String mUrl = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private boolean isWebViewAbailable;


    /**
     * 初始化WebView
     * @return
     */
    public abstract IWebViewInitializer getWebViewInit();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString(RouteKey.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView(){
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else {
            final IWebViewInitializer iWebViewInit = getWebViewInit();
            if (iWebViewInit!=null){

                WeakReference<WebView> weakReference = new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = weakReference.get();
                mWebView = getWebViewInit().initWebView(mWebView);
                mWebView.setWebViewClient(getWebViewInit().initWebViewClient());
                mWebView.setWebChromeClient(getWebViewInit().initWebChromeClient());
                mWebView.loadUrl(mUrl);
                mWebView.addJavascriptInterface(JavaScriptFace.create(this),"latte");
                isWebViewAbailable = true;
            }
        }

    }


    public String getUrl() throws Exception {
        if (mUrl == null){
            throw new Exception("url can not be null");
        }
        return mUrl;
    }

    public WebView getWebView() {
        return mWebView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mWebView!=null){
            mWebView.removeAllViews();
            mWebView = null;
        }
    }
}
