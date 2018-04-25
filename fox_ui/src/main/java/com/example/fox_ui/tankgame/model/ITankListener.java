package com.example.fox_ui.tankgame.model;

import android.graphics.Canvas;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public interface ITankListener {


    /**
     * 在画布上画出坦克
     * @param canvas
     */
    void drawTank(Canvas canvas);

    /**
     * 坦克的移动
     */
    void move(int direction);
}
