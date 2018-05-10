package com.example.fox_core.net;

import android.content.Context;

import com.example.fox_core.net.callback.IError;
import com.example.fox_core.net.callback.IFailure;
import com.example.fox_core.net.callback.IRequest;
import com.example.fox_core.net.callback.ISuccess;
import com.example.fox_core.ui.loader.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Author Alan
 * Date 2018/5/8 0008
 * Function
 * Issue
 */

public class RestClientBuilder {

    private String mUrl;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private LoaderStyle mLoaderStyle;
    private Context mContext;
    private IRequest mIRequest;
    private RequestBody mBody = null;

    public RestClientBuilder() {

    }

    public RestClientBuilder setUrl(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }


    public RestClient build() {
        return new RestClient(mUrl, PARAMS, mISuccess, mIFailure, mIError, mLoaderStyle, mContext, mIRequest, mBody);
    }


}
