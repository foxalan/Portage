package com.example.fox_ui.tankgame.presenter;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.fox_ui.tankgame.constant.Constant;
import com.example.fox_ui.tankgame.model.HeroTank;

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
}
