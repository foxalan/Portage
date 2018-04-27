package com.example.fox_ui.tankgame.model.bullet;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue
 */

public interface IBulletListener {
    /**
     * 移动
     */
    void move();

    /**
     * 画自己
     * @param canvas
     * @param paint
     */
    void drawBullet(Canvas canvas, Paint paint);
}
