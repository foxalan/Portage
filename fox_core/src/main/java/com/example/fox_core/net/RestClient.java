package com.example.fox_core.net;

import android.content.Context;

import com.example.fox_core.net.callback.IError;
import com.example.fox_core.net.callback.IFailure;
import com.example.fox_core.net.callback.IRequest;
import com.example.fox_core.net.callback.ISuccess;
import com.example.fox_core.net.callback.RequestCallbacks;
import com.example.fox_core.ui.loader.LatteLoader;
import com.example.fox_core.ui.loader.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Author Alan
 * Date 2018/5/8 0008
 * Function : 1.创建RequestService
 * Issue
 */

public class RestClient {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    private final RequestBody BODY;



    public RestClient(String url, Map<String, Object> params, ISuccess success, IFailure failure, IError error,LoaderStyle style,Context context, IRequest request,RequestBody body) {
        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = style;
        this.CONTEXT = context;
        this.REQUEST = request;
        this.BODY = body;
    }


    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod httpMethod) {

        //这里进行了一次分离
        RestService service = RestCreator.getRestService();

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        Call<String> call = null;
        switch (httpMethod) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            default:
                break;
        }

        //这里进行了一次分离
        if (call != null) {
           call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }



    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post(){
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

}
