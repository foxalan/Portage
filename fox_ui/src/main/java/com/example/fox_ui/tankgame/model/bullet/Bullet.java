package com.example.fox_ui.tankgame.model.bullet;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.fox_core.util.L;
import com.example.fox_ui.tankgame.constant.Constant;
import com.example.fox_ui.tankgame.model.Obstacle.ObstacleWood;
import com.example.fox_ui.tankgame.model.tank.EnemyTank;
import com.example.fox_ui.tankgame.model.tank.HeroTank;
import com.example.fox_ui.tankgame.model.tank.Tank;

import java.util.List;

import static com.example.fox_ui.tankgame.constant.Constant.RECT_LENGTH;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_DOWN;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_LEFT;
import static com.example.fox_ui.tankgame.constant.Constant.TANK_DIRECTION_RIGHT;
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

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public Bullet() {

    }

    @SuppressWarnings("unused")
    public Bullet(int mDir, boolean isExist, int mPositionX, int mPositionY) {
        this.mDir = mDir;
        this.isExist = isExist;
        this.mPositionX = mPositionX;
        this.mPositionY = mPositionY;
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

    public void setPositionY(int positionY) {
        this.mPositionY = positionY;
    }

    @Override
    public void move(List<EnemyTank> enemyTankList, HeroTank heroTank, List<ObstacleWood> obstacleWoodList) {

        if (!isExist()) {
            return;
        }

        //判断移动
        if (!isTouch(enemyTankList, heroTank, obstacleWoodList)) {
            return;
        }

        if (isExist()) {

            switch (getDir()) {
                case TANK_DIRECTION_UP:
                    setPositionY(getPositionY() - 1);
                    break;
                case TANK_DIRECTION_DOWN:
                    setPositionY(getPositionY() + 1);
                    break;
                case TANK_DIRECTION_LEFT:
                    setPositionX(getPositionX() - 1);
                    break;
                case TANK_DIRECTION_RIGHT:
                    setPositionX(getPositionX() + 1);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean isTouch(List<EnemyTank> enemyTankList, HeroTank heroTank, List<ObstacleWood> obstacleWoodList) {

        if (getPositionX() <= 0 || getPositionX() > Constant.RECT_COUNT_WIDTH) {
            setExist(false);
            return false;
        }
        if (getPositionY() <= 0 || getPositionY() > Constant.RECT_COUNT_HEIGHT) {
            setExist(false);
            return false;
        }
        for (int i = 0; i < obstacleWoodList.size(); i++) {
            if (!obstacleWoodList.get(i).isAlive()) {
                continue;
            }
            if (obstacleWoodList.get(i).getPositionX() == getPositionX()) {
                if (obstacleWoodList.get(i).getPositionY() == getPositionY()) {
                    obstacleWoodList.get(i).setAlive(false);
                    setExist(false);
                }
            }
        }

        if (getType() == Constant.BULLET_TYPE_Enemy) {
            if (!heroTank.isAlive()) {
                return true;
            }
            setTouch(heroTank);

        } else if (getType() == Constant.BULLET_TYPE_HERO) {
            for (int i = 0; i < enemyTankList.size(); i++) {
                if (!enemyTankList.get(i).isAlive()) {
                    continue;
                }
                setTouch(enemyTankList.get(i));
            }
        }
        return true;
    }

    /**
     * 检测碰撞
     *
     * @param heroTank
     */
    int magic = 2;

    private void setTouch(Tank heroTank) {
        switch (getDir()) {
            case TANK_DIRECTION_UP:
                if (Math.abs(getPositionX() - heroTank.getPositionX()) == magic) {
                    if ((getPositionY() - 1) == heroTank.getPositionY()) {
                        L.e(getDir() + "====" + getPositionY() + "=====" + heroTank.getPositionY());
                        setExist(false);
                        heroTank.setAlive(false);
                    }
                }
                break;
            case TANK_DIRECTION_DOWN:
                if (getPositionX() == heroTank.getPositionX()) {
                    if ((getPositionY() + 1) == heroTank.getPositionY()) {
                        L.e(getDir() + "====" + getPositionY() + "=====" + heroTank.getPositionY());
                        setExist(false);
                        heroTank.setAlive(false);
                    }
                }
                break;
            case TANK_DIRECTION_LEFT:
                if (getPositionY() == heroTank.getPositionY()) {
                    if (getDir() == TANK_DIRECTION_LEFT && (getPositionX() - 1) == heroTank.getPositionX()) {
                        setExist(false);
                        L.e(getDir() + "====" + getPositionX() + "=====" + heroTank.getPositionX());
                        heroTank.setAlive(false);
                    }
                }
                break;
            case TANK_DIRECTION_RIGHT:
                if (getPositionY() == heroTank.getPositionY()) {
                    if (getDir() == TANK_DIRECTION_LEFT && (getPositionX() + 1) == heroTank.getPositionX()) {
                        setExist(false);
                        L.e(getDir() + "====" + getPositionX() + "=====" + heroTank.getPositionX());
                        heroTank.setAlive(false);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void drawBullet(Canvas canvas, Paint paint) {
        if (isExist()) {
            int x = getPositionX();
            int y = getPositionY();
            Rect rect = new Rect(x * RECT_LENGTH, y * RECT_LENGTH, (x + 1) * RECT_LENGTH, (y + 1) * RECT_LENGTH);
            canvas.drawRect(rect, paint);
        }
    }
}
