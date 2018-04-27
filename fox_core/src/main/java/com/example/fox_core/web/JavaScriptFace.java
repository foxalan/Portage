package com.example.fox_core.web;

import com.example.fox_core.fragment.LatteDelegate;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue
 */

public class JavaScriptFace {

    private LatteDelegate delegate;

    public JavaScriptFace(LatteDelegate delegate){
        this.delegate = delegate;
    }

    static JavaScriptFace create(LatteDelegate delegate){

        return new JavaScriptFace(delegate);
    }

    public String event(String params){
        final String action ;

        return null;
    }



}
