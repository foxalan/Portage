package com.example.fox_ui.tankgame.model.Obstacle;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function:障碍物
 * Issue
 */

public abstract class Obstacle implements IObstacleListener{

    private boolean isAlive;
    private int mPositionX;
    private int mPositionY;
    private int mType;

    public Obstacle(boolean isAlive, int mPositionX, int mPositionY, int mType) {
        this.isAlive = isAlive;
        this.mPositionX = mPositionX;
        this.mPositionY = mPositionY;
        this.mType = mType;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getmPositionX() {
        return mPositionX;
    }

    public void setmPositionX(int mPositionX) {
        this.mPositionX = mPositionX;
    }

    public int getmPositionY() {
        return mPositionY;
    }

    public void setmPositionY(int mPositionY) {
        this.mPositionY = mPositionY;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
