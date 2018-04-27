package com.example.fox_ui.tankgame.model.bullet;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.fox_core.util.L;

import static com.example.fox_ui.tankgame.constant.Constant.RECT_LENGTH;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_UP;

/**
 * @Author Alan
 * Date 2018/4/27 0027
 * Function:子弹类
 * Issue
 */

public class Bullet implements IBulletListener {

    private int mDir;
    private boolean isExist;
    private int mPositionX;
    private int mPositionY;
    private int mType;

    public int getmype() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public Bullet() {

    }

    public Bullet(int mDir, boolean isExist, int mPositionX, int mPosetionY) {
        this.mDir = mDir;
        this.isExist = isExist;
        this.mPositionX = mPositionX;
        this.mPositionY = mPosetionY;
    }

    public int getDir() {
        return mDir;
    }

    public void setDir(int mDir) {
        this.mDir = mDir;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
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

    public void setPositionY(int mPosetionY) {
        this.mPositionY = mPosetionY;
    }

    @Override
    public void move() {
        L.e("bullet move");
        setPositionY(getPositionY() + 1);
//        switch (getDir()) {
//            case TANK_DIRECTION_UP:
//                setPositionY(getPositionY() - 1);
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void drawBullet(Canvas canvas, Paint paint) {
        L.e("bullet draw" );
        int x = (getPositionX() - 1) + 1;
        int y = (getPositionY() - 1) + 1;
        Rect rect = new Rect(x * RECT_LENGTH, y * RECT_LENGTH, (x + 1) * RECT_LENGTH, (y + 1) * RECT_LENGTH);
        canvas.drawRect(rect, paint);
    }
}
