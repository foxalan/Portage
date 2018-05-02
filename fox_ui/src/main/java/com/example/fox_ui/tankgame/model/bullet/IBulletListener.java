package com.example.fox_ui.tankgame.model.bullet;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.fox_ui.tankgame.model.Obstacle.ObstacleWood;
import com.example.fox_ui.tankgame.model.tank.EnemyTank;
import com.example.fox_ui.tankgame.model.tank.HeroTank;

import java.util.List;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function
 * Issue
 */

public interface IBulletListener {
    /**
     * 移动
     * @param enemyTankList
     * @param heroTank
     * @param obstacleWoodList
     */
    void move(List<EnemyTank> enemyTankList, HeroTank heroTank, List<ObstacleWood> obstacleWoodList);

    /**
     * 画自己
     * @param canvas
     * @param paint
     */
    void drawBullet(Canvas canvas, Paint paint);
}
