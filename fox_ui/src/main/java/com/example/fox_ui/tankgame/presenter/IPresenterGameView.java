package com.example.fox_ui.tankgame.presenter;

import android.graphics.Paint;

import com.example.fox_ui.tankgame.model.HeroTank;
import com.example.fox_ui.tankgame.model.Tank;

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


}
