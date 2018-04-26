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



    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    private int mDirection;
    private int mId;

    public Tank(Bitmap[] mBitmap, int mPositionX, int mPositionY, int mSpeed, boolean isAlive, int mCount, int mType, int mDirection) {
        this.mBitmap = mBitmap;
        this.mPositionX = mPositionX;
        this.mPositionY = mPositionY;
        this.mSpeed = mSpeed;
        this.isAlive = isAlive;
        this.mCount = mCount;
        this.mType = mType;
        this.mDirection = mDirection;
    }

    public Tank(){

    }

    public Bitmap[] getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap[] mBitmap) {
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

    public int getmSpeed() {
        return mSpeed;
    }

    public void setmSpeed(int mSpeed) {
        this.mSpeed = mSpeed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getDirection() {
        return mDirection;
    }

    public void setDirection(int mDirection) {
        this.mDirection = mDirection;
    }
}
