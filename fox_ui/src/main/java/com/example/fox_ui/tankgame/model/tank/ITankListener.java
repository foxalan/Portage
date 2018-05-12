package com.example.fox_ui.tankgame.model.tank;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.fox_ui.tankgame.model.Obstacle.Obstacle;
import com.example.fox_ui.tankgame.model.Obstacle.ObstacleWood;
import com.example.fox_ui.tankgame.model.bullet.Bullet;

import java.util.List;

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
     * @param paint
     */
    void drawTank(Canvas canvas, Paint paint);

    /**
     * 坦克的移动
     * @param direction
     */
    void move(int direction, List<EnemyTank> enemyTankList, List<ObstacleWood> obstacleList);

    /**
     * 发射子弹
     * @return
     */
    Bullet shoutBullet();
}
