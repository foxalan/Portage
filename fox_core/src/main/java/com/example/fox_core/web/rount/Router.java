package com.example.fox_core.web.rount;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.example.fox_core.web.WebDelegate;

/**
 * @Author Alan
 * Date 2018/5/2 0002
 * Function
 * Issue
 */

public class Router {
    private static Router router;

    public static Router getInstance(){
        if (router == null){
            router = new Router();
        }

        return router;
    }

    public boolean event(WebDelegate delegate,String url){
        //如果是电话协议
        if (url.contains("tel:")){
            callPhone(delegate.getContext(), url);
            return true;
        }

        //todo

        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null!");
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
