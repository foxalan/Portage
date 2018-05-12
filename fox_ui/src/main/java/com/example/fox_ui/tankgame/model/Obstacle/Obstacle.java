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

    public Obstacle(){

    }

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

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }
}
