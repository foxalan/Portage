package com.example.fox_ui.tankgame.model;

import android.graphics.Bitmap;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public class HeroTank extends Tank {

    private int type;

    public HeroTank(Bitmap[] mBitmap, int mPositionX, int mPositionY, int mSpeed, boolean isAlive, int mCount, int mType) {
        super(mBitmap, mPositionX, mPositionY, mSpeed, isAlive, mCount, mType);
    }

    @Override
    public void move() {

    }
}
