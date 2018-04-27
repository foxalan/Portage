package com.example.fox_ui.tankgame.model.tank;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.fox_core.util.L;
import com.example.fox_ui.tankgame.constant.Constant;
import com.example.fox_ui.tankgame.model.bullet.Bullet;

import static com.example.fox_ui.tankgame.constant.Constant.RECT_LENGTH;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_DOWN;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_LEFT;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_RIGHT;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_UP;

/**
 * @Author Alan
 * Date 2018/4/22 0022
 * Function
 * Issue
 */

public class HeroTank extends Tank {

    private int mTanklength = 3;

    public HeroTank() {

    }

    @SuppressWarnings("unused")
    public HeroTank(Bitmap[] mBitmap, int mPositionX, int mPositionY, int mSpeed, boolean isAlive, int mCount, int mType, int mDirection) {
        super(mBitmap, mPositionX, mPositionY, mSpeed, isAlive, mCount, mType, mDirection);

    }

    @Override
    public void drawTank(Canvas canvas,Paint paint) {
        switch (getDirection()) {
            case 0:
                drawTankUp(canvas,paint);
                break;
            case 1:
                drawTankLeft(canvas,paint);
                break;
            case 2:
                drawTankRight(canvas,paint);
                break;
            case 3:
                drawTankDown(canvas,paint);
                break;
            default:
                break;
        }
    }

    @Override
    public void move(int direction) {
        //1.设置方向 2.改变坐标
        switch (direction) {
            case TANK_DIRECTION_UP:
                setDirection(TANK_DIRECTION_UP);
                setPositionY(getPositionY() - 1);
                L.e("move up");
                break;
            case TANK_DIRECTION_DOWN:
                setDirection(TANK_DIRECTION_DOWN);
                setPositionY(getPositionY() + 1);
                L.e("move down");
                break;
            case TANK_DIRECTION_LEFT:
                setDirection(TANK_DIRECTION_LEFT);
                setPositionX(getPositionX() - 1);
                L.e("move left");
                break;
            case TANK_DIRECTION_RIGHT:
                setDirection(TANK_DIRECTION_RIGHT);
                setPositionX(getPositionX() + 1);
                L.e("move right");
                break;
            default:
                break;

        }
    }

    @Override
    public Bullet shoutBullet() {
        L.e("hero tank set bullet");
        Bullet bullet = new Bullet();
        bullet.setDir(getDirection());
        bullet.setExist(true);
        bullet.setPositionY(getPositionY());
        bullet.setPositionX(getPositionX());
        bullet.setType(Constant.BULLET_TYPE_HERO);
        return bullet;
    }

    private void drawTankUp(Canvas canvas,Paint paint) {

        for (int i = 0; i < mTanklength; i++) {
            for (int j = 0; j < mTanklength; j++) {
                int x = (getPositionX() - 1) + j;
                int y = (getPositionY() - 1) + i;

                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0 && j == 2) {
                    continue;
                }
                Rect rect = new Rect(x * RECT_LENGTH, y * RECT_LENGTH, (x + 1) * RECT_LENGTH, (y + 1) * RECT_LENGTH);
                canvas.drawRect(rect, paint);
            }
        }
    }

    private void drawTankDown(Canvas canvas ,Paint paint) {

        for (int i = 0; i < mTanklength; i++) {
            for (int j = 0; j < mTanklength; j++) {
                int x = (getPositionX() - 1) + j;
                int y = (getPositionY() - 1) + i;

                if (i == 2 && j == 0) {
                    continue;
                }
                if (i == 2 && j == 2) {
                    continue;
                }
                Rect rect = new Rect(x * RECT_LENGTH, y * RECT_LENGTH, (x + 1) * RECT_LENGTH, (y + 1) * RECT_LENGTH);
                canvas.drawRect(rect, paint);
            }
        }
    }

    private void drawTankLeft(Canvas canvas,Paint paint) {

        for (int i = 0; i < mTanklength; i++) {
            for (int j = 0; j < mTanklength; j++) {
                int x = (getPositionX() - 1) + j;
                int y = (getPositionY() - 1) + i;

                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 2 && j == 0) {
                    continue;
                }
                Rect rect = new Rect(x * RECT_LENGTH, y * RECT_LENGTH, (x + 1) * RECT_LENGTH, (y + 1) * RECT_LENGTH);
                canvas.drawRect(rect, paint);
            }
        }
    }

    private void drawTankRight(Canvas canvas,Paint paint) {

        for (int i = 0; i < mTanklength; i++) {
            for (int j = 0; j < mTanklength; j++) {
                int x = (getPositionX() - 1) + j;
                int y = (getPositionY() - 1) + i;

                if (i == 2 && j == 2) {
                    continue;
                }
                if (i == 0 && j == 2) {
                    continue;
                }
                Rect rect = new Rect(x * RECT_LENGTH, y * RECT_LENGTH, (x + 1) * RECT_LENGTH, (y + 1) * RECT_LENGTH);
                canvas.drawRect(rect, paint);
            }
        }
    }


}
