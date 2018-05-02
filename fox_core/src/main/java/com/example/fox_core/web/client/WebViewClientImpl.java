package com.example.fox_core.web.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.fox_core.web.IPageLoadListener;
import com.example.fox_core.web.WebDelegate;
import com.example.fox_core.web.WebViewDelegateImpl;
import com.example.fox_core.web.rount.Router;

/**
 * @Author Alan
 * Date 2018/5/2 0002
 * Function : webView 中的WebViewClient
 *  1.加入开始和结束的标志
 * Issue
 */

public class WebViewClientImpl extends WebViewClient{

    private IPageLoadListener pageLoadListener;

    private WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate DELEGATE){
        this.DELEGATE = DELEGATE;
    }

    public void setPageLoadListener(IPageLoadListener pageLoadListener) {
        this.pageLoadListener = pageLoadListener;
    }

    /**
     * 对Url进行处理
     * return true 表示当前url即使是重定向url也不会再执行（除了在return true之前使用webview.loadUrl(url)除外，因为这个会重新加载）
       return false  表示由系统执行url，直到不再执行此方法，即加载完重定向的ur（即具体的url，不再有重定向）
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().event(DELEGATE,url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (pageLoadListener!=null){
            pageLoadListener.onLoadStart();
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (pageLoadListener!=null){
            pageLoadListener.onLoadEnd();
        }
    }
}
