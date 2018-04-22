package com.example.fox_ui.tankgame.model;

import android.graphics.Bitmap;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public abstract class Tank  implements ITankListener{


    private Bitmap[] mBitmap;
    private int mPositionX;
    private int mPositionY;
    private int mSpeed;
    private boolean isAlive;
    private int mCount;
    private int mType;
    private int mDirection;

    public Tank(){

    }

    public Tank(Bitmap[] mBitmap, int mPositionX, int mPositionY, int mSpeed, boolean isAlive, int mCount, int mType) {
        this.mBitmap = mBitmap;
        this.mPositionX = mPositionX;
        this.mPositionY = mPositionY;
        this.mSpeed = mSpeed;
        this.isAlive = isAlive;
        this.mCount = mCount;
        this.mType = mType;
    }

    public Bitmap[] getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap[] mBitmap) {
        this.mBitmap = mBitmap;
    }

    public int getPositionX() {
        return mPositionX;
    }

    public void setPositionX(int mPositionX) {
        this.mPositionX = mPositionX;
    }

    public int getPositionY() {
        return mPositionY;
    }

    public void setPositionY(int mPositionY) {
        this.mPositionY = mPositionY;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int mSpeed) {
        this.mSpeed = mSpeed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }
}
