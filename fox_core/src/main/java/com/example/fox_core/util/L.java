package com.example.fox_core.util;

import android.util.Log;

/**
 * @Author Alan
 * Date 2018/4/24 0024
 * Function : Log
 * Issue
 */

public class L {

    private static final String CUT_OFF = "------------------------";
    private static final String CUT_OFF_END = "----------------------" +
            "--------------------------------------------------------";


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
        String startLine = CUT_OFF + getPosition(TAG) + CUT_OFF;

        e(TAG,startLine+"=="+value);
    }

    /**
     * 获取最后调用我们log的StackTraceElement
     * @param tag 目标类的SimpleName
     * @return
     */

    private static StackTraceElement getTargetStack(String tag) {

        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {

            if (element.getClassName().contains(tag)) {
                //返回调用位置的 element
                return element;

            }

        }

        return null;
    }

    private static String getPosition(String tag) {
        StringBuilder sb = new StringBuilder();
        StackTraceElement element = getTargetStack(tag);

        if (null == element) {
            return null;
        }

        //sb.append(".")// 我电脑的AndroidStudio有点问题，必须在这加个点，在logcat中才能定位。Androidstudio升级后，这个问题不存在了。
        sb.append("(")
                .append(element.getFileName())
                .append(":")
                .append(element.getLineNumber())
                .append(")");
        return sb.toString();
    }





}
