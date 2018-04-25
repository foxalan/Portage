package com.example.fox_core.util;

import android.util.Log;

/**
 * @Author Alan
 * Date 2018/4/24 0024
 * Function : Log
 * Issue
 */

public class L {

    public static final String TAG = "tankgame";

    public static void d(String tag,String value){
        Log.d(tag,value);
    }

    public static void d(String value){
        d(TAG,value);
    }

    public static void e(String tag,String value){
        Log.e(tag,value);
    }

    public static void e(String value){
        e(TAG,value);
    }


}
