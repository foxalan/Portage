package com.example.fox_ui.tankgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.fox_core.util.L;
import com.example.fox_ui.tankgame.constant.Constant;

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

    public HeroTank(){

    }

    private int type;

    public HeroTank(Bitmap[] mBitmap, int mPositionX, int mPositionY, int mSpeed, boolean isAlive, int mCount, int mType, int mDirection, int type) {
        super(mBitmap, mPositionX, mPositionY, mSpeed, isAlive, mCount, mType, mDirection);
        this.type = type;
    }


    /**
     * 0 up 1 down 2 right 3 left
     * @param canvas
     */
    @Override
    public void drawTank(Canvas canvas) {
        switch (getmDirection()){
            case 0:
                drawTankUp(canvas);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                    break;
        }
    }

    @Override
    public void move(int direction) {
        switch (direction){
            case TANK_DIRECTION_UP:
                L.e("move up");
                break;
            case TANK_DIRECTION_DOWN:
                L.e("move down");
                break;
            case TANK_DIRECTION_LEFT:
                L.e("move left");
                break;
            case TANK_DIRECTION_RIGHT:
                L.e("move right");
                break;
            default:
                break;

        }
    }

    private void drawTankUp(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);

        for(int i = 0;i<mTanklength;i++){
            for(int j = 0;j < mTanklength;j++){
                int x = (getmPositionX()-1)+j;
                int y = (getmPositionY()-1)+i;
                if ((i==0&&j==0)||(i==0&&j==2)){
                    continue;
                }
                Rect rect = new Rect(x* RECT_LENGTH,y*RECT_LENGTH,(x+1)*RECT_LENGTH,(y+1)*RECT_LENGTH);
                canvas.drawRect(rect,paint);
            }
        }
    }


}
