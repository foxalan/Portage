package com.example.fox_ui.fragment;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.fox_core.fragment.LatteDelegate;
import com.example.fox_ui.R;

/**
 * @Author Alan
 * Date 2018/4/20 0020
 * Function
 * Issue
 */

public class WebViewFragment extends LatteDelegate {

    private LinearLayout mLinerContainer;
    private WebView mWebView;
//    private WebViewInitialize mWebViewInitalize;
    private String url = "https://www.baidu.com/";


    @Override
    public Object getLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public void onBindView(View rootView) {
        mLinerContainer = rootView.findViewById(R.id.ll_container);
        mWebView = new WebView(getContext());
  //      mWebViewInitalize = new WebViewInitialize();
  //      mWebViewInitalize.initWebSetting(mWebView);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(url);
        mLinerContainer.addView(mWebView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLinerContainer.removeAllViews();
        mWebView.destroy();
    }
}
