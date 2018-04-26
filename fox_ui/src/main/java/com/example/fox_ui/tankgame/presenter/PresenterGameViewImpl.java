package com.example.fox_ui.tankgame.presenter;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.fox_ui.tankgame.constant.Constant;
import com.example.fox_ui.tankgame.model.EnemyTank;
import com.example.fox_ui.tankgame.model.HeroTank;

import java.util.List;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public class PresenterGameViewImpl implements IPresenterGameView{

    public PresenterGameViewImpl(){

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
        tank1.setAlive(true);
        tank1.setmSpeed(10);
        tank1.setPositionX(5);
        tank1.setPositionY(5);
        tank1.setDirection(Constant.TANK_DIRECTION_UP);

        EnemyTank tank2 = new EnemyTank();
        tank2.setAlive(true);
        tank2.setmSpeed(10);
        tank2.setPositionX(1);
        tank2.setPositionY(1);
        tank2.setDirection(Constant.TANK_DIRECTION_UP);

        EnemyTank tank3 = new EnemyTank();
        tank3.setAlive(true);
        tank3.setmSpeed(10);
        tank3.setPositionX(15);
        tank3.setPositionY(15);
        tank3.setDirection(Constant.TANK_DIRECTION_UP);

        enemyTankList.add(tank1);
        enemyTankList.add(tank2);
        enemyTankList.add(tank3);
    }
}
