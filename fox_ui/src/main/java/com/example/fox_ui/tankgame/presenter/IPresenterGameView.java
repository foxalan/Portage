package com.example.fox_ui.tankgame.presenter;

import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

import com.example.fox_ui.tankgame.model.Obstacle.ObstacleWood;
import com.example.fox_ui.tankgame.model.bullet.Bullet;
import com.example.fox_ui.tankgame.model.tank.EnemyTank;
import com.example.fox_ui.tankgame.model.tank.HeroTank;

import java.util.List;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public interface IPresenterGameView {

    /**
     * 初始化画笔
     * @param paint
     */
    void initPaints(Paint paint);

    /**
     * 初始化我方坦克
     * @param heroTank
     */
    void initHeroTank(HeroTank heroTank);

    /**
     * 初始化对面的坦克
     * @param enemyTankList
     */
    void initEnemyTanks(List<EnemyTank> enemyTankList);

    /**
     * 初始化障碍物
     * @param obstacleWoods
     */
    void initObstacle(List<ObstacleWood> obstacleWoods);

    /**
     * 控制敌方坦克行走,射击
     * @param enemyTankList
     * @param bulletList
     * @param mHandler
     *
     */
    void initEnemyTanksMove(List<EnemyTank> enemyTankList, List<Bullet> bulletList,List<ObstacleWood> obstacleWoodList, Handler mHandler);

    /**
     * 控制子弹的移动
     * @param bulletList
     * @param handler
     */
    void initBulletMove(List<Bullet> bulletList,Handler handler);
}
