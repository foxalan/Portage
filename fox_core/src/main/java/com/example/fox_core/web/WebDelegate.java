package com.example.fox_core.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

import com.example.fox_core.fragment.LatteDelegate;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue 1.ReferenceQueue WeakReference
 */

public abstract class WebDelegate extends LatteDelegate {

    private WebView mWebView = null;
    private String mUrl = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUENE = new ReferenceQueue<>();
    private boolean isWebViewAbailable;



    public abstract IWebViewInit getWebViewInit();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString("");

    }

    @SuppressLint("JavascriptInterface")
    private void initWebView(){
        if (mWebView!=null){
            mWebView.removeAllViews();
        }else {
            final IWebViewInit iWebViewInit = getWebViewInit();
            if (iWebViewInit!=null){

                WeakReference<WebView> weakReference = new WeakReference<WebView>(new WebView(getContext()),WEB_VIEW_QUENE);
                mWebView = weakReference.get();
                mWebView = getWebViewInit().initWebView(mWebView);
                mWebView.setWebViewClient(getWebViewInit().initWebViewClient());
                mWebView.setWebChromeClient(getWebViewInit().initWebChromeClient());
                mWebView.loadUrl(mUrl);
                mWebView.addJavascriptInterface(JavaScriptFace.create(this),"latte");
            }
        }

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
