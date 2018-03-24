package com.example.administrator.protage.util;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/24 0024.
 * @author alan
 * LOG工具类
 */

public class L {

    private static final String TAG="alan";

    public static void e(String message){
        e(TAG,message);
    }

    public static void e(String tag,String message){
        Log.e(tag,message);
    }


}
