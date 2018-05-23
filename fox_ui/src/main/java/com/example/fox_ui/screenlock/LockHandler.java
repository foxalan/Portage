package com.example.fox_ui.screenlock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.example.fox_ui.screenlock.model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Alan
 * Date 2018/5/22 0022
 * Function :
 * Issue :
 */

public class LockHandler<T> {

    private List<T> mCurrentLockList = new ArrayList<>();
    private List<T> mPassWordLockList = new ArrayList<>();





    /**
     * 初始化位置
     * @param event
     */
    public void initStartPoint(MotionEvent event, Canvas canvas , Paint paintPoint){

    }

    /**
     * 开始连接
     */
    public void linkLockPoints(MotionEvent event, Canvas canvas,Paint paintLines,Paint paintPoints){

    }

    /**
     * 检查连接
     */
    public void ckeckLockPoints(MotionEvent event){

    }


}
