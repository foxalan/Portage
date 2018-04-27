package com.example.fox_ui.tankgame.model.Obstacle;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue
 */

public interface IObstacleListener {
    /**
     * 画出自己
     * @param canvas
     * @param paint
     */
    void draw(Canvas canvas, Paint paint);
}
