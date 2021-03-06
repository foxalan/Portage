package com.example.fox_ui.tankgame.presenter;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.fox_ui.tankgame.constant.Constant;
import com.example.fox_ui.tankgame.model.Obstacle.ObstacleCreater;
import com.example.fox_ui.tankgame.model.Obstacle.ObstacleWood;
import com.example.fox_ui.tankgame.model.bullet.Bullet;
import com.example.fox_ui.tankgame.model.tank.EnemyTank;
import com.example.fox_ui.tankgame.model.tank.HeroTank;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.fox_ui.tankgame.constant.Constant.MSG_INVALIDATE;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public class PresenterGameViewImpl implements IPresenterGameView {

    private HeroTank heroTank;
    private List<EnemyTank> enemyTankList;
    private List<ObstacleWood> obstacleWoods;

    public PresenterGameViewImpl() {

    }

    public PresenterGameViewImpl(HeroTank heroTank, List<EnemyTank> enemyTankList, List<ObstacleWood> obstacleWoods) {
        this.heroTank = heroTank;
        this.enemyTankList = enemyTankList;
        this.obstacleWoods = obstacleWoods;
    }

    @Override
    public void initPaints(Paint paint) {

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
    }

    @Override
    public void initHeroTank(HeroTank heroTank) {
        heroTank.setmCount(3);
        heroTank.setAlive(true);
        heroTank.setmSpeed(10);
        heroTank.setPositionX(10);
        heroTank.setPositionY(10);
        heroTank.setDirection(Constant.TANK_DIRECTION_UP);
    }

    @Override
    public void initEnemyTanks(List<EnemyTank> enemyTankList) {
        EnemyTank tank1 = new EnemyTank();
        tank1.setId(0);
        tank1.setAlive(true);
        tank1.setmSpeed(10);
        tank1.setPositionX(5);
        tank1.setPositionY(5);
        tank1.setDirection(Constant.TANK_DIRECTION_UP);

        EnemyTank tank2 = new EnemyTank();
        tank2.setId(1);
        tank2.setAlive(true);
        tank2.setmSpeed(10);
        tank2.setPositionX(1);
        tank2.setPositionY(1);
        tank2.setDirection(Constant.TANK_DIRECTION_UP);

        EnemyTank tank3 = new EnemyTank();
        tank3.setId(2);
        tank3.setAlive(true);
        tank3.setmSpeed(10);
        tank3.setPositionX(15);
        tank3.setPositionY(15);
        tank3.setDirection(Constant.TANK_DIRECTION_UP);

        enemyTankList.add(tank1);
        enemyTankList.add(tank2);
        enemyTankList.add(tank3);
    }

    @Override
    public void initObstacle(List<ObstacleWood> obstacleWoods) {
       obstacleWoods.addAll(ObstacleCreater.create());
    }

    @Override
    public void initEnemyTanksMove(final List<EnemyTank> enemyTankList, final List<Bullet> bulletList, final List<ObstacleWood> obstacleWoodList, final android.os.Handler mHandler) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (EnemyTank enemyTank : enemyTankList) {

                    Random random = new Random();
                    int r = random.nextInt(100);
                    if (r % 2 == 0) {
                        enemyTank.setDirection(Constant.TANK_DIRECTION_UP);
                    } else if (r % 3 == 0) {
                        enemyTank.setDirection(Constant.TANK_DIRECTION_DOWN);
                    } else if (r % 4 == 0) {
                        enemyTank.setDirection(Constant.TANK_DIRECTION_LEFT);
                    } else {
                        enemyTank.setDirection(Constant.TANK_DIRECTION_RIGHT);
                    }
                    enemyTank.move(enemyTank.getDirection(),enemyTankList,obstacleWoodList);
                    bulletList.add(enemyTank.shoutBullet());
                }
                mHandler.sendEmptyMessage(MSG_INVALIDATE);
            }
        }, 0, 1000);
    }

    @Override
    public void initBulletMove(final List<Bullet> bulletList, final android.os.Handler handler) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i =0;i<bulletList.size();i++){
                    bulletList.get(i).move(enemyTankList,heroTank,obstacleWoods);
                }
                handler.sendEmptyMessage(MSG_INVALIDATE);
            }
        }, 0, 500);
    }


}
