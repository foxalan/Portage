package com.example.fox_ui.tankgame.model;

import android.graphics.Canvas;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public interface ITankListener {

    void drawTank(Canvas canvas);

    void move();
}
